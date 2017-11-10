package catfish.plugins.pdfgenerator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import catfish.base.business.dao.ActivityFactorDao;
import catfish.base.business.object.ActivityFactorObject;
import org.junit.Before;
import org.junit.Test;

import catfish.base.StartupConfig;
import catfish.base.Logger;
import catfish.base.business.common.AttachmentType;
import catfish.base.business.dao.ApplicationAttachmentDao;
import catfish.base.database.DatabaseConfig;

import com.itextpdf.text.DocumentException;

public class PdfGeneratorTest {
	private PDFGenerator pdfGenerator;
	private AgreementService service;
	private String appId;
	private static String HTML = "C:/Users/zhaosq/workspace/TurnToPDF/src/a.html";
	private static String DEST = "C:/Users/zhaosq/workspace/TurnToPDF/src/a.pdf";
	private static final String CSS = "C:/Users/zhaosq/workspace/TurnToPDF/src/a.css";
	private static final String IMG_PATH = "C:/Users/zhaosq/workspace/TurnToPDF/src/";
	
	@Before
	public void setUp() {
		StartupConfig.initialize();
		Logger.initialize();
		DatabaseConfig.initialize();
//		pdfGenerator = new PDFGenerator("87813016-DEE8-E411-87D5-80DB2F14945F");
		service = new AgreementService();
		appId = "06B00E19-C11A-4735-A780-C5596404EA3A";
	}
	
	@Test
	public void testPDFGenerate() {
//		String out = service.turnPDFtoJPG(appId, AttachmentType.ApplicationAgreementPDF);
//		try {
//			OutputStream os = new FileOutputStream(new File("f:/pdf.html"));
//			os.write(out.getBytes());
//			os.flush();
//			os.close();
//
//		} catch (IOException e) {
//
//		}
//		try {
//			ByteArrayOutputStream os = pdfGenerator.html2pdf(pdfGenerator.constructHtmlStream(), CSS, IMG_PATH);
//			FileOutputStream fileOutputStream = new FileOutputStream("D:\\"+pdfGenerator.getOutFileName());
//			os.writeTo(fileOutputStream);
//			os.close();
//			fileOutputStream.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DocumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		boolean r = ApplicationAttachmentDao.insert("1", "2", AttachmentType.ApplicationAgreementPDF);
//		System.out.println(r);
		
	}

}
