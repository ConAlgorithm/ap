package catfish.plugins.pdfgenerator.merchantloan;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import catfish.base.Logger;
import catfish.base.business.common.MimeType;
import catfish.base.business.dao.AttachmentDao;
import catfish.plugins.pdfgenerator.Base64ImageProvider;
import catfish.plugins.pdfgenerator.OssApi;

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

public class MsPdfService {

  private static final String CSS = "./agreementTemplate/mobile.css";
  private static final String IMG_PATH = "./agreementTemplate";

  private String appId;

  public MsPdfService(String appId) {
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

}
