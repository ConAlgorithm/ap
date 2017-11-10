package export.exporter.ylzh;

import java.util.List;

import thirdparty.ylzh.response.tradereport.IndexConsumeCity;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;
import catfish.base.httpclient.object.BaseObject;

import com.google.gson.reflect.TypeToken;

import export.exporter.DerivativeVariablesExporter;

public class YLZHReportInfoExporter extends DerivativeVariablesExporter{

	public YLZHReportInfoExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, AppDerivativeVariables record) {
		try{
			String info = record.getAsString(AppDerivativeVariableNames.YLZH_CONSUMEREGION_LIST);
			if(info == null) return;
			List<IndexConsumeCity> dataList = BaseObject.fromString(info,  new TypeToken<List<IndexConsumeCity>>(){}.getType());
			for(IndexConsumeCity item : dataList)
			{
				writer.write(getRecord(appId, item));
			}
		}catch(Exception e)
		{
			Logger.get().error(String.format("writeContactListInfo of AppId: %s error!", appId));
		}
	}

}
