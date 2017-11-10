package export.exporter.timer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;

import catfish.base.file.CSVReader;
import catfish.base.file.CSVWriter;
import export.exporter.IExportable;

public class CountDownTimerExporter implements IExportable{

	private CSVReader reader = new CSVReader("Timer/CountDownTimer.csv");
	@Override
	public void export(String appId, Object record) {
		File out = new File("Timer/CountDownTimerOut.csv");
		Map<String, Double> map = getOldTimers();
		Map<String, Double> newTimer = new HashMap<>();
		try (FileWriter writer = new FileWriter(out)){
			reader.next();
			String[] result = null;
			String jobName;
			Double timer;
			while(reader.next())
			{
				result = reader.getRecord();
				jobName = result[1];
				timer = Double.parseDouble(result[4]);
				newTimer.put(jobName, timer);
			}
			map.putAll(newTimer);
			for(Entry<String, Double> entry : map.entrySet())
			{
				String item = String.format("'%s': %d,\n", entry.getKey(), entry.getValue().intValue());
				System.out.println(item);
				writer.write(item);
				writer.flush();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	private Map<String, Double> getOldTimers()
	{
		File old = new File("Timer/oldTimer.json");
		try (FileReader oldReader = new FileReader(old)){
			char[] temp = new char[(int)old.length()];
			//oldReader.read(temp);
			return new Gson().fromJson(oldReader, Map.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
