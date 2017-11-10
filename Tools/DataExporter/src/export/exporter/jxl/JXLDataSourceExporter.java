package export.exporter.jxl;

import java.util.List;

import thirdparty.jxl.response.reportdata.BindDataSourceItem;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;
import catfish.base.httpclient.object.BaseObject;

import com.google.gson.reflect.TypeToken;

import export.exporter.DerivativeVariablesExporter;

public class JXLDataSourceExporter extends DerivativeVariablesExporter{

	public JXLDataSourceExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, AppDerivativeVariables record) {
		String dataSource = record.getAsString(AppDerivativeVariableNames.JXL_REPORTDATA_DATA_SOURCE);
		if(dataSource != null)
		{
			writeDataSourceInfo(appId, dataSource);
		}
	}

	private void writeDataSourceInfo(String appId, String info)
	{
		try{
			List<BindDataSourceItem> dataList = BaseObject.fromString(info,  new TypeToken<List<BindDataSourceItem>>(){}.getType());
			for(BindDataSourceItem item : dataList)
			{
				writer.write(getRecord(appId, item));
			}
		}catch(Exception  e)
		{
			Logger.get().error(String.format("writeDataSourceInfo of AppId: %s error!", appId));
		}
		
	}
}
