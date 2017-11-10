package export.exporter.jxl;

import java.util.List;

import thirdparty.jxl.response.reportdata.ContactRegionItem;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;
import catfish.base.httpclient.object.BaseObject;

import com.google.gson.reflect.TypeToken;

import export.exporter.DerivativeVariablesExporter;

public class JXLContactRegionExporter extends DerivativeVariablesExporter{

	public JXLContactRegionExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, AppDerivativeVariables record) {
		String contactRegion = record.getAsString(AppDerivativeVariableNames.JXL_REPORTDATA_CONTACT_REGION);
		if(contactRegion != null)
		{
			writeContactRegionInfo(appId, contactRegion);
		}
	}

	private void writeContactRegionInfo(String appId, String info)
	{
		try{
			List<ContactRegionItem> dataList = BaseObject.fromString(info,  new TypeToken<List<ContactRegionItem>>(){}.getType());
			for(ContactRegionItem item : dataList)
			{		
				writer.write(getRecord(appId, item));
			}
		}catch(Exception e)
		{
			Logger.get().error(String.format("writeContactRegionInfo of AppId: %s error!", appId));
		}
		
	}
}
