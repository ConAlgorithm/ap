package export.exporter.jxl;

import thirdparty.jxl.response.reportdata.Person;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.business.util.AppDerivativeVariables;
import catfish.base.file.IWritable;
import export.exporter.DerivativeVariablesExporter;

public class JXLPersonExporter extends DerivativeVariablesExporter{

	public JXLPersonExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, AppDerivativeVariables record) {
		String person = record.getAsString(AppDerivativeVariableNames.JXL_REPORTDATA_PERSON);
		if(person != null)
		{
			writePersonInfo(appId, person);
		}
	}

	private void writePersonInfo(String appId, String info)
	{
		try{
			Person person = Person.fromString(info, Person.class);				
			writer.write(getRecord(appId, person));
		}catch(Exception e)
		{
			Logger.get().error(String.format("WritePersonInfo of AppId: %s error!", appId));
		}
	}
}
