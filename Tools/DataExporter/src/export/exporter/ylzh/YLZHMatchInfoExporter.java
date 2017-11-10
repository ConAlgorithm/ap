package export.exporter.ylzh;

import java.util.ArrayList;
import java.util.List;

import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;
import export.exporter.DerivativeVariablesExporter;

public class YLZHMatchInfoExporter extends DerivativeVariablesExporter{

	public YLZHMatchInfoExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, AppDerivativeVariables record) {
		try{
			String info = record.getAsString(AppDerivativeVariableNames.YLZH_BANKCARD_MOBILE_MATCH);
			if(info == null) return;
			List<String> list = new ArrayList<>();
			list.add(appId);
			list.add(info);
			writer.write(list);
		}catch(Exception e)
		{
			Logger.get().error(String.format("writeContactListInfo of AppId: %s error!", appId));
		}
	}

}
