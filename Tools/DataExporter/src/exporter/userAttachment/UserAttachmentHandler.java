package exporter.userAttachment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;

import catfish.base.Logger;
import catfish.base.StringUtils;
import catfish.base.business.common.ApplicationStatus;
import catfish.base.business.common.AttachmentType;
import catfish.base.business.util.StorageServiceUtil;
import export.HandlerConfiguration;

public class UserAttachmentHandler {
  
  private AttachmentDao dao = new AttachmentDao();
  
  private StorageServiceUtil storageUtils = new StorageServiceUtil();

  public void handle() {
    
    if (HandlerConfiguration.getUserAttachmentSwitch()) {
      
      saveData(ApplicationStatus.Closed.getValue(), Utils.getClosedFileName());
      saveData(ApplicationStatus.ClosedInAdvanced.getValue(), Utils.getClosedInAdvanceFileName());
      saveData(ApplicationStatus.Delayed.getValue(), Utils.getDelayedFileName());
      saveData(ApplicationStatus.Completed.getValue(), Utils.getCompletedFileName());    
    }
  }
  
  private void saveData(Integer status, String fileName) {
    
    makeFile(fileName);
    
    List<Model> datas = dao.getAllAppsByTagAndBankNames(
        Utils.getFundTag(), Utils.BankList, status, 
        Utils.getStartDate(), Utils.getEndDate());
    
    Logger.get().info("get AppIds of number: " + datas.size() + " with status: " + status);
    
    for(Model data : datas) {
      
      System.out.println("save attachment for appId:" + data.getAppId());
      
      String folderFileName = fileName + File.separator + data.getBankName() 
      + Utils.FILE_NAME_SEPARATOR + data.getBankAccount() + 
      Utils.FILE_NAME_SEPARATOR + data.getBankAccountName();
      
      makeFile(folderFileName);      
      
      saveImage(folderFileName, data.getFilePath(), Utils.getBankPhotoFileName());
      
      saveImage(folderFileName, 
          dao.getUserPhoto(data.getUserId(), AttachmentType.BuckleAgreement.getValue()), 
          Utils.getBuclePhotoFileName());
      
      saveImage(folderFileName, 
          dao.getUserPhoto(data.getUserId(), AttachmentType.UserIdPhoto.getValue()), 
          Utils.getIdPhotoFileName());
    }
    Logger.get().info("begin tar files-----------------------------");
    tarFiles(fileName, fileName + Utils.COMPRESSED_FILE_TAG);
  }
 
  private void saveImage(String folderName, String ossPath, String fileName) {
    
    if (StringUtils.isNullOrWhiteSpaces(ossPath)) {
      Logger.get().warn(folderName + "save " + fileName + " with empty ossPath");
      return;
    }  
    try {
      
      String image = storageUtils.loadImage(ossPath, HandlerConfiguration.getStorageService());
      
      OutputStream stream = new FileOutputStream(new File(folderName + File.separator + fileName));
      stream.write(Base64.decodeBase64(image.getBytes("UTF-8")));
      stream.close();
      
    } catch (Exception e) {
      Logger.get().warn("save image error:" + e);
    }
    
  }
  
  private void makeFile(String fileName) {
    
    File file = new File(fileName);
    
    if(file.exists() && file.isDirectory()) {
      System.out.println("file name:" + fileName + " exist!");
    }  
    file.mkdirs();
  }

  private void tarFiles(String baseDir, String fileName) {
    
    try {
      List<File> fileList = getSubFiles(new File(baseDir));  
      
      ZipOutputStream stream = new ZipOutputStream(new FileOutputStream(fileName));  
      
      byte[] buf = new byte[Utils.BUFFER_SIZE];  
         
      for(int i = 0; i <fileList.size(); i++) {  
        
          File f = (File)fileList.get(i);   
          ZipEntry ze = new ZipEntry(getAbsFileName(baseDir, f));  
          ze.setSize(f.length());  
          ze.setTime(f.lastModified());          
          stream.putNextEntry(ze);  
          
          InputStream is = new BufferedInputStream(new FileInputStream(f));  
          int readLen = 0;  
          while ((readLen = is.read(buf, 0, Utils.BUFFER_SIZE)) != -1) {  
            stream.write(buf, 0, readLen);  
          }  
          is.close();  
      }  
      stream.close();  
      
    } catch(Exception e) {
      
      Logger.get().warn("compress file error:" + e);
    }
    
  }
  
  private String getAbsFileName(String baseDir, File realFileName){  
    File real = realFileName;  
    File base = new File(baseDir);  
    String ret = real.getName();  
    while (true) {  
      
        real = real.getParentFile();  
        if(real==null || real.equals(base))   break;  
        
        ret = real.getName() + File.separator + ret;  
    }  
    return ret;  
}  
  
  private List<File> getSubFiles(File baseDir){  
    
    List<File> ret = new ArrayList<File>(); 
    
    File[] tmp = baseDir.listFiles();  
    
    for (int i = 0; i <tmp.length; i++) {  
      
        if(tmp[i].isFile()) {
          
          ret.add(tmp[i]);  
        }
            
        if(tmp[i].isDirectory()) {
          
          ret.addAll(getSubFiles(tmp[i]));  
        }        
    }  
    return ret;  
}  
}
