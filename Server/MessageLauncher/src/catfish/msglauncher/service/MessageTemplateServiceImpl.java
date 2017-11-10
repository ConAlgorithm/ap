package catfish.msglauncher.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import catfish.msglauncher.model.MessageTemplate;
import catfish.msglauncher.model.RestResult;
import catfish.msglauncher.util.MongoDBApi;

public class MessageTemplateServiceImpl implements IMessageTemplateService {

	@Override
	public List<MessageTemplate> list(String templateCode) {
		// TODO Auto-generated method stub
		if(null != templateCode && !"".equals(templateCode)){
			MessageTemplate mt = MongoDBApi.findMessageTemplateByTemplateCode(templateCode);
			List<MessageTemplate> mtList = new ArrayList<MessageTemplate>();
			if(null != mt){
				mtList.add(mt);
			}
			return mtList;
		}
		return MongoDBApi.findAllMessageTemplates();

	}

	@Override
	public RestResult add(MessageTemplate messageTemplate) {
		RestResult result = new RestResult();
		
		if(null == messageTemplate){
			result.setSuccess(false);
			result.setMessage("messageTemplate is null!");
			return result;
		}
		
		MongoDBApi.insertMessageTemplate(messageTemplate);
		result.setSuccess(true);
		result.setMessage("insert success");
		return result;
	}

	@Override
	public RestResult update(MessageTemplate messageTemplate) {
		RestResult result = new RestResult();
		
		if(null == messageTemplate){
			result.setSuccess(false);
			result.setMessage("messageTemplate is null!");
			return result;
		}
		
		MongoDBApi.updateMessageTemplateContentByTemplateCode(messageTemplate);
		result.setSuccess(true);
		result.setMessage("update success");
		return result;
	}

}
