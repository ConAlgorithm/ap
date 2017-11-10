package export.exporter.jxl;

import java.util.List;

import thirdparty.jxl.response.reportdata.BehaviorItem;
import thirdparty.jxl.response.reportdata.CellPhoneBehaviorItem;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;
import catfish.base.httpclient.object.BaseObject;

import com.google.gson.reflect.TypeToken;

import export.exporter.DerivativeVariablesExporter;

public class JXLCellBehaviorExporter extends DerivativeVariablesExporter{

	public JXLCellBehaviorExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, AppDerivativeVariables record) {
		String cellBehavior = record.getAsString(AppDerivativeVariableNames.JXL_REPORTDATA_CELL_BEHAVIOR);
		if(cellBehavior != null)
		{
			writeCellBehaviorInfo(appId, cellBehavior);
		}
	}

	private void writeCellBehaviorInfo(String appId, String info)
	{
		try{
			List<CellPhoneBehaviorItem> dataList = BaseObject.fromString(info,  new TypeToken<List<CellPhoneBehaviorItem>>(){}.getType());
			for(CellPhoneBehaviorItem item : dataList)
			{
				for(BehaviorItem behavior : item.getBehaviorList())
				{
					List<String> behaviorList = getRecord(appId, behavior);
					writer.write(behaviorList);
				}			
			}
		}catch(Exception e)
		{
			Logger.get().error(String.format("writeCellBehaviorInfo of AppId: %s error!", appId));
		} 	
	}
}
