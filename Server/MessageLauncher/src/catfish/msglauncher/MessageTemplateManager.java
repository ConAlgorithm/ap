package catfish.msglauncher;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import catfish.msglauncher.model.MessageTemplate;
import catfish.msglauncher.util.MongoDBApi;
import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class MessageTemplateManager {
	private static final Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
	private static final StringTemplateLoader stringLoader = new StringTemplateLoader();
	private static final Map<String,MessageTemplate> messageTemplateMap = new HashMap<String,MessageTemplate>();
	public static void init(){
		cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        //load template info from database
        List<MessageTemplate> messageTemplateList = MongoDBApi.findAllMessageTemplates();
        for(MessageTemplate messageTemplate: messageTemplateList){
        	stringLoader.putTemplate(messageTemplate.getTemplateCode(),messageTemplate.getContent());
        	messageTemplateMap.put(messageTemplate.getTemplateCode(), messageTemplate);
        }
        
        cfg.setTemplateLoader(stringLoader);
	}
	
	public static String generateContent(String templateCode, Map<String, String> contentMap) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		Template template = cfg.getTemplate(templateCode);
		StringWriter out = new StringWriter();
        template.process(contentMap, out);
        out.close();
		return out.toString();
	}
	
	public static String generateContent(String templateCode, String templateJson) throws TemplateNotFoundException, JsonSyntaxException, MalformedTemplateNameException, ParseException, IOException, TemplateException{
		return generateContent(templateCode, new Gson().fromJson(templateJson, Map.class));
		
	}
	
	public static String generateTemplateId(String templateCode){
		MessageTemplate mt = messageTemplateMap.get(templateCode);
		return mt.getTemplateId();
	}
}
