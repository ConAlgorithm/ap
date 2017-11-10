package engine.rule.test.service;

import com.google.gson.Gson;
import engine.rule.test.service.object.BasicResponse;
import engine.rule.test.service.object.UserAccount;
import static engine.rule.test.service.object.BasicResponse.Success;
import static engine.rule.test.service.object.BasicResponse.Fail;

public class UserAccountService {

	//登录验证
	public BasicResponse login(final byte[] body)
	{
		String request = new String(body);
		UserAccount account = new Gson().fromJson(request, UserAccount.class);
		if(account.userName.equals("") && account.password.equals(""))
			return Success;
		return Fail;
	}
}
