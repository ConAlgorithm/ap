package engine.rule.test.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import catfish.base.Logger;

import com.csvreader.CsvReader;

class TagField{
	public String tag;
	public String field;
	public TagField(String tag, String field)
	{
		this.tag = tag;
		this.field = field;
	}
}

public class CSVDataReader {

	private String fileName;
	
	private CsvReader reader;
	
	private List<Map<String, Map<String, Object>>> valuesList = new LinkedList<Map<String, Map<String, Object>>>();
	
	private TagField[] header;
	
	
	public CSVDataReader(String fileName) throws FileNotFoundException
	{
		this.fileName = fileName;
		reader = new CsvReader(fileName);
	}
	
	private void readHeader()
	{
		try {
			String[] rawHeader = reader.getHeaders();
			header = new TagField[rawHeader.length];
			for(int i = 0; i < header.length; i ++)
			{
				String[] item = rawHeader[i].split(".");
				header[i] = new TagField(item[0], item[1]);
			}
		} catch (IOException e) {
			Logger.get().error("Can not read header of " + fileName);
		}
	}
	
	private void readData()
	{
		int count = 0;
		String[] line = null;
		try {
			while(reader.readRecord())
			{
				Map<String, Map<String, Object>> dataLine = new HashMap<String, Map<String, Object>>();
				count ++;
			    line = reader.getValues();
				for(int i = 0; i < line.length; i ++)
				{
					TagField headerItem = header[i];
					Map<String, Object> value = new HashMap<>();
					value.put(headerItem.field, line[i]);
					if(dataLine.containsKey(headerItem.tag))
					{
						dataLine.get(headerItem.tag).putAll(value);
					}else
					{
						dataLine.put(headerItem.tag, value);
					}
				}
				
				valuesList.add(dataLine);
			}
		} catch (IOException e) {
			Logger.get().error(String.format("Parse data %s error, in line %d, data: %s", fileName, count, line.toString()));
		}	
	}
	
	public void readRecords()
	{
		readHeader();
		readData();
	}
	public void close()
	{
		if(reader != null)
		   reader.close();
	}
}
