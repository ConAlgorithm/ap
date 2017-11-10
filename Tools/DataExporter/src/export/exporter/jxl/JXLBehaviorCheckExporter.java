package export.exporter.jxl;

import java.util.List;

import thirdparty.jxl.response.reportdata.CheckPointItem;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;
import catfish.base.httpclient.object.BaseObject;

import com.google.gson.reflect.TypeToken;

import export.exporter.DerivativeVariablesExporter;

public class JXLBehaviorCheckExporter extends DerivativeVariablesExporter{

	public JXLBehaviorCheckExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, AppDerivativeVariables record) {
		String behaviorCheck = record.getAsString(AppDerivativeVariableNames.JXL_REPORTDATA_BEHAVIOR_CHECK);
		if(behaviorCheck != null)
		{
			writeBehaviorCheckInfo(appId, behaviorCheck);
		}
	}
	
	private void writeBehaviorCheckInfo(String appId, String info)
	{
		try{
			List<CheckPointItem> dataList = BaseObject.fromString(info,  new TypeToken<List<CheckPointItem>>(){}.getType());
			for(CheckPointItem item : dataList)
			{
				writer.write(getRecord(appId, item));
			}
		}catch(Exception e)
		{
			Logger.get().error(String.format("writeBehaviorCheckInfo of AppId: %s error!", appId));
		}
		
	}
}
