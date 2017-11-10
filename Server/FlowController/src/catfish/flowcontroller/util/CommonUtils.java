package catfish.flowcontroller.util;

import java.util.HashMap;
import java.util.Map;

import catfish.base.business.common.UploadFileStatus;
import catfish.base.business.common.app.UploadFileFlag;

public class CommonUtils {

	public static final String APP = "app";
	//pdl开卡申请
	public static final String PDL = "paydayloan";
	//pdl借款申请
	public static final String PDLM = "paydayloanmoney";

	public static final String CL = "cashloan";
	
	public static final String BATCHJOBS = "batchjobs";

    public static int getIntValue(Object value)
    {
    	Double doubleValue = Double.parseDouble(value.toString());
    	return doubleValue.intValue();
    }
    
    public static Map<Integer, String> photoValueResourceNameMap = new HashMap<Integer, String>();
    
    public static Map<Integer, String> appValueResourceNameMap = new HashMap<>();
    
    static {
    	initAPPValueResourceNameMap();
    	initPDLValueResourceNameMap();
    }
    private static void initPDLValueResourceNameMap(){
    	photoValueResourceNameMap.put(UploadFileStatus.HeadPhotoUploaded.getValue(), "HeadPhoto");
        photoValueResourceNameMap.put(UploadFileStatus.IdPhotoUploaded.getValue(), "IDCardPhoto");
        photoValueResourceNameMap.put(UploadFileStatus.BankPhotoUploaded.getValue(), "BankCard");
        photoValueResourceNameMap.put(UploadFileStatus.IOUUploaded.getValue(), "IOU");
        photoValueResourceNameMap.put(UploadFileStatus.BuckleUploaded.getValue(), "Buckle");
        photoValueResourceNameMap.put(UploadFileStatus.GroupPhotoUploaded.getValue(), "GroupPhoto");
        photoValueResourceNameMap.put(UploadFileStatus.ENoticeFormPhotoUploaded.getValue(), "ENoticeFormPhoto");
        
        photoValueResourceNameMap.put(UploadFileStatus.WorkPermitUploaded.getValue(), "WorkPermit");
        photoValueResourceNameMap.put(UploadFileStatus.ChestCardPhotoUploaded.getValue(), "ChestCardPhoto");
        photoValueResourceNameMap.put(UploadFileStatus.UniformPhotoUploaded.getValue(), "UniformPhoto");
        photoValueResourceNameMap.put(UploadFileStatus.HealthCertificatePhotoUploaded.getValue(), "HealthCertificatePhoto");
        photoValueResourceNameMap.put(UploadFileStatus.TimeCardPhotoUploaded.getValue(), "TimeCardPhoto");
        photoValueResourceNameMap.put(UploadFileStatus.SocialSecurityCardPhotoUploaded.getValue(), "SocialSecurityCardPhoto");
        photoValueResourceNameMap.put(UploadFileStatus.WageCeritificatePhotoUploaded.getValue(), "WageCeritificatePhoto");
    }
    private static void initAPPValueResourceNameMap(){
    	appValueResourceNameMap.put(UploadFileFlag.HeadPhoto.getValue(), "HeadPhoto");
    	appValueResourceNameMap.put(UploadFileFlag.IdPhoto.getValue(), "IDCardPhoto");
    	appValueResourceNameMap.put(UploadFileFlag.BankPhoto.getValue(), "BankCard");
    	appValueResourceNameMap.put(UploadFileFlag.D1GroupPhoto.getValue(), "DGroupPhoto");
    	appValueResourceNameMap.put(UploadFileFlag.IOU.getValue(), "IOU");
    	appValueResourceNameMap.put(UploadFileFlag.Buckle.getValue(), "Buckle");
    }
}
