package jma.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.business.dao.ContactDao;
import catfish.base.business.object.ContactObject;
import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import jma.JobHandler;
import jma.models.jxl.ContactList;

import java.util.Date;
public class CheckContactInJXLCountHandler extends JobHandler{

	@Override
	public void execute(String appId) {
		List<ContactObject> contactPersonList = ContactDao.getContactPersonContacts(appId);	
		AppDerivativeVariables contactListVarables = AppDerivativeVariableManager.getVariables(appId, AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_LIST);
		if(contactListVarables == null){
			return;
		}
		/*当appId为空的时候，contactListVarables 不为null，但是contactListVarables.getAsString(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_LIST)为null
		 * 故此处加上下面的判断逻辑，阻止mongo中产生JXL_REPORTDATA_REG_CONTACTPHONE_IN_JXL_NUM为0的混淆判断的数据
		 */
		String contactListItemJson = contactListVarables.getAsString(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_LIST);
		if(contactListItemJson == null){
			return ;
		}
		//判断逻辑结束
		List<ContactList> contactList = new Gson().fromJson(contactListItemJson, new TypeToken<List<ContactList>>() { }.getType());
		
		List<AppDerivativeVariable> variablesList = new ArrayList<>();
		
		variablesList.add(new AppDerivativeVariable(appId, AppDerivativeVariableNames.JXL_REPORTDATA_REG_CONTACTPHONE_IN_JXL_NUM,
				this.getRegContactInJxlNumber(contactList, contactPersonList)));
		variablesList.add(new AppDerivativeVariable(appId, "X_JXL_Count_Test", new Date()));
		AppDerivativeVariableManager.addVariables(variablesList);
	}
	
	//用户填写联系人电话号码在JXL中出现的个数。
	private int getRegContactInJxlNumber(List<ContactList> contactList, List<ContactObject> regContactPersonList)
	{
		if(null == contactList || null == regContactPersonList)
		{
			return 0;
		}
		
		int regInJXLContactNum = 0;
		Set<String> jxlContactNumberSet = new HashSet<>();
		for(ContactList item : contactList)
		{
			if(item.getPhoneNum() != null)
			{
				jxlContactNumberSet.add(item.getPhoneNum());
			}
		}		

		for(ContactObject contactPersonObj : regContactPersonList)
		{
			if(contactPersonObj.Content != null &&  jxlContactNumberSet.contains(contactPersonObj.Content))
			{
				regInJXLContactNum ++;
			}
		}
		return regInJXLContactNum;
	}
}
