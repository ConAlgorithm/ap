package export.exporter.jxl;

import java.util.List;

import thirdparty.jxl.response.reportdata.ContactListItem;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;
import catfish.base.httpclient.object.BaseObject;

import com.google.gson.reflect.TypeToken;

import export.exporter.DerivativeVariablesExporter;

public class JXLContactListExporter extends DerivativeVariablesExporter{

	public JXLContactListExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, AppDerivativeVariables record) {
		String contactList = record.getAsString(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_LIST);
		
		if(contactList != null)
		{
			writeContactListInfo(appId, contactList);
		}
	}

	private void writeContactListInfo(String appId, String info)
	{
		try{
			List<ContactListItem> dataList = BaseObject.fromString(info,  new TypeToken<List<ContactListItem>>(){}.getType());
			for(ContactListItem item : dataList)
			{
				writer.write(getRecord(appId, item));
			}
		}catch(Exception e)
		{
			Logger.get().error(String.format("writeContactListInfo of AppId: %s error!", appId));
		}		
	}
}
