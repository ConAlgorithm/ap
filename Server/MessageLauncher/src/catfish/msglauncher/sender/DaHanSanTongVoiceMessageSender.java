package catfish.msglauncher.sender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.http.protocol.RequestDate;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import catfish.base.CollectionUtils;
import catfish.base.DynamicConfig;
import catfish.base.Logger;
import catfish.base.httpclient.HttpClientApi;
import catfish.msglauncher.Configuration;
import catfish.msglauncher.Message;
import catfish.msglauncher.message.ShortMessage;
import catfish.msglauncher.model.ResultData;

import com.dahantc.api.voice.json.HttpJSONClient;
import com.dahantc.api.voice.json.VoiceReqData;
import com.google.gson.Gson;

public class DaHanSanTongVoiceMessageSender implements MessageSender {

  private static final String URL = "http://voice.3tong.net";
  private static final String EXPECTED_CODE = "DH:0000";

  @Override
  public void send(Message baseMessage) {
    if(!isAvailable())
      throw new RuntimeException("DaHanSanTong is SwitchOff.");
    
    
    ShortMessage message = (ShortMessage) baseMessage;

	String contentJson = message.getContentJson();
    Gson gson = new Gson();	
	HashMap<String,String> hm = (HashMap<String,String>)gson.fromJson(contentJson, (new HashMap<String,String>()).getClass());
    
	try {
		//初始化客户端
		HttpJSONClient client = new HttpJSONClient(URL);
		
		List<VoiceReqData> list = new ArrayList<VoiceReqData>();
		String msgid = UUID.randomUUID().toString();
		list.add(new VoiceReqData(message.getMobile(), hm.get("code"), "maidanxia", msgid, 1, 0));
		//list.add(new VoiceReqData("13812345678", "211457", "dh9876tishiyin", "", 1, 0)); //平台默认提示音 验证码短信,自定义提示音 验证码短信
		String submitResp = client.sendAuthCodeVoiceSms(Configuration.getDaHanSanTongAccount(), Configuration.getDaHanSanTongPassword(), list);
    	Logger.get().info("get client   "+ submitResp + " , msgid :" + msgid); 		
		ResultData  resultData = (ResultData) gson.fromJson(submitResp, ResultData.class);
		String result = resultData.getResult();
    	Logger.get().info("get result of DaHanSanTongVoiceMessage  "+ result); 
	    if (!result.equals(EXPECTED_CODE)) {
	          throw new RuntimeException("Got DaHanSanTong error " + submitResp + "resultcode  " + result);
	    }		

	} catch (Exception e) {
		e.printStackTrace();
	}    
    
  }
  
  private boolean isAvailable(){
    return DynamicConfig.readAsBool("daHanSanTongSwitchOn");
  }
}
