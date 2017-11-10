package catfish.plugins.pdfgenerator;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import catfish.base.StartupConfig;
import catfish.base.Logger;
import catfish.base.database.DatabaseConfig;

import catfish.framework.httprequest.HttpRequest;
import catfish.services.httprequest.HttpRequestService;
import catfish.framework.IPlugin;
import catfish.servers.Server;
import catfish.servers.ServerConfig;

import com.itextpdf.text.DocumentException;

public class PDFHttpRequestTest {
	
	private PDFGenerator pdfGenerator;
	private static String HTML = "C:/Users/zhaosq/workspace/TurnToPDF/src/a.html";
	private static String DEST = "C:/Users/zhaosq/workspace/TurnToPDF/src/a.pdf";
	private static final String CSS = "C:/Users/zhaosq/workspace/TurnToPDF/src/a.css";
	private static final String IMG_PATH = "C:/Users/zhaosq/workspace/TurnToPDF/src/";
	
	@Before
	public void setUp() {
		StartupConfig.initialize();
		Logger.initialize();
		DatabaseConfig.initialize();
		Server server = Server.Create();
		
		ServerConfig serverConfig = new ServerConfig();
		
		//serverConfig.enableCluster = true;
		//serverConfig.zookeeperConnection = "localhost:2181,localhost:2182,localhost:2183";
		
		IPlugin[] plugins = new IPlugin[] {
			    new PDFGeneratorHttpPlugin()
			    };
		
		server.run(serverConfig, plugins);
	}
	
	@Test
	public void testPDFGenerate() {
	StartupConfig.initialize();
		
		Map<String, String> body = new HashMap<String, String>();
		String appId = "87813016-DEE8-E411-87D5-80DB2F14945F";
		body.put("AppId", appId);
		String host = StartupConfig.get("catfish.bonuspoints.rest.host");
		String port = StartupConfig.get("catfish.bonuspoints.rest.port");
		HttpRequest request = new HttpRequest("POST", host, port, new String[]{"GeneratePDF"},new Gson().toJson(body));
		
		new HttpRequestService().sendHttpRequest(request);
		
	}

}
