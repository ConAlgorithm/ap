package catfish.plugins.pdfgenerator.cashloan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.MimeType;
import catfish.base.business.dao.AttachmentDao;
import catfish.base.httpclient.HttpClientApi;
import catfish.plugins.pdfgenerator.Base64ImageProvider;
import catfish.plugins.pdfgenerator.BoolModel;
import catfish.plugins.pdfgenerator.OssApi;
import net.sf.json.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class CLPdfService {

  private static final String CSS = "./agreementTemplate/mobile.css";
  private static final String IMG_PATH = "./agreementTemplate";
  private static final String COWFISH_HOST = StartupConfig.get("cowfish.rest.host");

  private String appId;

  public CLPdfService() {
	super();
  }

  public CLPdfService(String appId) {
    super();
    this.appId = appId;
  }

  public String generatePDFandSave(String html) {
    try {
      InputStream is = new ByteArrayInputStream(html.getBytes(Charset.forName("UTF-8")));
      ByteArrayOutputStream baos = html2pdf(is, CSS, IMG_PATH);
      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
      String pdfName = "agreement" + appId + ".pdf";
      return saveAttachment(bais, pdfName);
    } catch (IOException e) {
      Logger.get().error(e);
    } catch (DocumentException e) {
      Logger.get().error(e);
    }

    return null;
  }

  public String generatePDFandSave(String html, String type) {
	    try {
	      InputStream is = new ByteArrayInputStream(html.getBytes(Charset.forName("UTF-8")));
	      ByteArrayOutputStream baos = html2pdf(is, CSS, IMG_PATH);
	      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	      String pdfName = "agreement" + type + appId + ".pdf";
	      return saveAttachment(bais, pdfName, type);
	    } catch (IOException e) {
	      Logger.get().error(e);
	    } catch (DocumentException e) {
	      Logger.get().error(e);
	    }

	    return null;
	  }
  
  public InputStream generatePDF(String html) {
    try {
      InputStream is = new ByteArrayInputStream(html.getBytes(Charset.forName("UTF-8")));
      ByteArrayOutputStream baos = html2pdf(is, CSS, IMG_PATH);
      return new ByteArrayInputStream(baos.toByteArray());
    } catch (IOException e) {
      Logger.get().error(e);
    } catch (DocumentException e) {
      Logger.get().error(e);
    }
    return null;
  }

  /**
   * function: batch update agreement file path by appIds.<p>
   * date: 2017-02-27
   * @author jiaoh
   * @param filePath, key:AppId; value: filePath
   * @return BoolModel
   */
  public BoolModel updateAgreementFilePath(Map<String, String> filePath) {
	  if (filePath == null || filePath.isEmpty()) {
		  Logger.get().warn("file path is null or empty. filePath" + filePath);
		  return new BoolModel(false);
	  }

	  for (Map.Entry<String, String> entry: filePath.entrySet()) {
		  this.updateAgreementFilePathByAppId(entry);
	  }

	  return new BoolModel(true);
  }

  /**
   * function:  update agreement file path by appId.<p>
   * date: 2017-02-27
   * @author jiaoh
   * @param entry, key:AppId; value: filePath
   * @return void
   */
  private void updateAgreementFilePathByAppId(Map.Entry<String, String> entry) {
	  String appId = entry.getKey();

	  String url = String.format("%s/application/%s/agreement", COWFISH_HOST, appId);
      String response = null;
      try{
          response = HttpClientApi.get(url);
          Logger.get().info("Get agreement response:" + response + ", appId:" + appId);

          if (response == null) {
        	  return;
          }

          JSONObject json = JSONObject.fromObject(response);
          String attachmentId = json.getString("id");
          boolean result = AttachmentDao.updateAttachment(attachmentId, entry.getValue());

          if (result == false) {
        	  Logger.get().error("update agreement file path failed. appId:" + appId);
          }
      } catch(Exception e) {
          Logger.get().error("Get agreement response error, appId:" + appId, e);
      }

	  return;
  }

  public ByteArrayOutputStream html2pdf(InputStream htmlStream, String cssPath, String imgPath)
      throws IOException, DocumentException {
    // step 1
    Document document = new Document();
    // step 2
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    PdfWriter writer = PdfWriter.getInstance(document, outStream);
    // step 3
    document.open();
    // step 4
    // CSS
    CSSResolver cssResolver = new StyleAttrCSSResolver();
    CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(cssPath));
    cssResolver.addCss(cssFile);

    // HTML
    HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
    htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
    htmlContext.setImageProvider(new Base64ImageProvider() {
        @Override
        public String getImageRootPath() {
            return imgPath;
        }
    });

    // Pipelines
    PdfWriterPipeline pdf = new PdfWriterPipeline(document, writer);
    HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
    CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

    // XML Worker
    XMLWorker worker = new XMLWorker(css, true);
    XMLParser p = new XMLParser(worker);
    p.parse(htmlStream, Charset.forName("UTF-8"));
    // step 5
    document.close();

    return outStream;
  }

  public String saveAttachment(InputStream inputStream, String fileName) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String key = String.format("%s/%s/%s", "agreement", appId, sdf.format(new Date()));
    if (OssApi.savePDF(key, inputStream) == null) {
        Logger.get().info("failure : save PDF failed, attachment will not be generated, appId=" + appId);
        return null;
    }

    String attachId = AttachmentDao.addAttachment(fileName, key, MimeType.Pdf);
    if (attachId == null) {
        Logger.get().info("failure : insert attachment record failed, appId=" + appId);
        return null;
    }

    return attachId;
  }

  public String saveAttachment(InputStream inputStream, String fileName, String type) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	    String key = String.format("%s/%s/%s%s", "agreement", type, appId, sdf.format(new Date()));
	    if (OssApi.savePDF(key, inputStream) == null) {
	        Logger.get().info("failure : save PDF failed, attachment will not be generated, appId=" + appId);
	        return null;
	    }

	    String attachId = AttachmentDao.addAttachment(fileName, key, MimeType.Pdf);
	    if (attachId == null) {
	        Logger.get().info("failure : insert attachment record failed, appId=" + appId);
	        return null;
	    }

	    return attachId;
	  }
  
  public Boolean generatePDFandSaveForAssetMDQ(String html) {
	    try {
	      InputStream is = new ByteArrayInputStream(html.getBytes(Charset.forName("UTF-8")));
	      ByteArrayOutputStream baos = html2pdf(is, CSS, IMG_PATH);
	      ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	      String pdfName = "agreement" + appId + ".pdf";
	      return saveForAssetMDQ(bais, pdfName);
	    } catch (IOException e) {
	      Logger.get().error(e);
	    } catch (DocumentException e) {
	      Logger.get().error(e);
	    }

	    return false;
	  }
  
  public Boolean saveForAssetMDQ(InputStream inputStream, String fileName) {
	    String key = String.format("%s/%s/%s", "fund", "asset", appId);
	    if (OssApi.savePDF(key, inputStream) == null) {
	        Logger.get().info("failure : save PDF failed, attachment will not be generated, appId=" + appId);
	        return false;
	    }
	    return true;
	  }

}


