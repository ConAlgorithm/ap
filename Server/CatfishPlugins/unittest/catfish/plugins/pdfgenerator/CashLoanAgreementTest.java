package catfish.plugins.pdfgenerator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.database.DatabaseConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.plugins.pdfgenerator.cashloan.CLAgreementService;
import catfish.plugins.pdfgenerator.cashloan.CLPdfService;

public class CashLoanAgreementTest {

  private CLAgreementService agreementService;
  private CLPdfService pdfService;
  private String appId;

  @Before
  public void setUp() {
    StartupConfig.initialize();
    Logger.initialize();
    DatabaseConfig.initialize();
    HttpClientConfig.initialize();
    appId = "233D39C9-A3E2-4AFC-B365-C513F8AACAAA";
    agreementService = new CLAgreementService(appId);
    pdfService = new CLPdfService(appId);
  }

  @Test
  public void testCLAgreementService() {
    String html = agreementService.getFullHtml();
    System.out.println(html);
    String attachmentId = pdfService.generatePDFandSave(html);
    Assert.assertNotNull(html);
    Assert.assertNotNull(attachmentId);
  }

}
