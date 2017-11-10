package export.migration.machinelearn;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import catfish.base.file.CSVReader;
import catfish.base.file.CSVWriter;
import engine.rule.model.annotation.DBField;
import engine.rule.model.in.app.InvestigationInForm;
import export.migration.IMigratable;

public class MLMigration implements IMigratable{

	private static final String FOLDER = "machinelearn/";
	@Override
	public void migrate() {
		Field[] fields = engine.rule.model.in.app.InvestigationInForm.class.getDeclaredFields();
		Field[] pdlFields = engine.rule.model.in.pdl.InvestigationInForm.class.getDeclaredFields();
		Set<String> derivableNames = this.getNamesSet(fields);
		derivableNames.addAll(this.getNamesSet(pdlFields));
		
		
		CSVReader reader = new CSVReader(FOLDER + "input.csv");
		CSVWriter errorWriter = new CSVWriter(FOLDER + "error.csv");
		CSVWriter outWriter = new CSVWriter(FOLDER + "out.csv");
		
		while(reader.next())
		{
			String[] record = reader.getRecord();
			String name = record[0];
			if(derivableNames.contains(name))
			{
				errorWriter.write(new String[]{name});
			}else{
				outWriter.write(new String[]{name});
			}
		}
	}

	private boolean checkIsAfterSegmentation(String derivableName)
	{
		return true;
	}
	
	private Set<String> getNamesSet(Field[] fields)
	{
		Set<String> derivableNames = new HashSet<>();
		for(Field item : fields)
		{
			DBField an = item.getAnnotation(DBField.class);
			if(an != null && checkIsAfterSegmentation(an.variableType()))
			{
				derivableNames.add(an.variableType());
			}
		}
		return derivableNames;
	}
}
