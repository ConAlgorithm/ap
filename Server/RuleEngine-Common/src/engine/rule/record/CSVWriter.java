package engine.rule.record;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

import catfish.base.Logger;

import com.csvreader.CsvWriter;
import com.huateng.toprules.core.annotation.ModelField;

import engine.exception.JavaBeanParseException;
import engine.main.Configuration;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.util.JavaBeanUtil;

public class CSVWriter implements RecordWriter {

	private static final String csvPath = Configuration.getReportLogPath();

	private CsvWriter writer;

	private Class<?> formClass;

	public CSVWriter(Class<?> formClass) {
		this.formClass = formClass;
		String fileName =  formClass.getSimpleName();
		writer = getWriter(fileName + "_"
				+ DateTime.now().toString("yyyy-MM-dd-HH"));
	}

	public CSVWriter(String fileName) {
		writer = getWriter(fileName);
	}

	@Override
	public void writeRecord(String[] record) {
		try {
			writer.writeRecord(record);
			writer.flush();
		} catch (IOException e) {
			Logger.get().warn("Write record error", e);
		}
	}

	public void close() {
		writer.close();
	}

	public void flush() {
		writer.flush();
	}

	private CsvWriter getWriter(String fileName) {
		String name = csvPath + fileName + ".csv";
		CsvWriter wr = new CsvWriter(name, ',', Charset.forName("UTF-8"));
		return wr;
	}

	public void writeFormCSVHeader() {
		writeFormCSVHeader(formClass);
	}

	public void writeFormCSVHeader(Class<?> formClass) {
		Field[] fields = formClass.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		list.add("AppId");
		try {
			for (Field item : fields) {
				list.add(item.getName());
			}
			writer.writeRecord(list.toArray(new String[0]));
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeFormCSV(BaseForm form, String appId) {
		Field[] fields = form.getClass().getDeclaredFields();
		String[] values = new String[3];
		for (Field item : fields) {
			try {
				Object temp = JavaBeanUtil.get(form, item);
				String value = temp == null ? "" : temp.toString();
				DBField db = item.getAnnotation(DBField.class);
				ModelField md = item.getAnnotation(ModelField.class);
				if(db != null && db.variableType() != null)
				{
					String variableName = db.variableType();
					values[0] = (md == null ? "null" : md.name()); 
				    values[1] = (variableName == null ? "null" : variableName);
				    values[2] = value;
					writer.writeRecord(values);
					writer.flush();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void writeFormCSV(AbstractApplicationModelCreator creator) {
		String appId = creator.getAppId();
		Map<String, Object> map = creator.createModelForm();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			BaseForm form = (BaseForm) map.get(key);
			writeFormCSV(form, appId);
		}
	}
}
