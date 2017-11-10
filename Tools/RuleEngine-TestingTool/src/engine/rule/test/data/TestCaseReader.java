package engine.rule.test.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import catfish.base.exception.CatfishException;
import catfish.base.file.CSVReader;
import engine.rule.test.TestingToolException;

public class TestCaseReader {
	private String filePath = null;
	private String[] header = null;
	private List<String[]> lines = null;
	
	public TestCaseReader(String filePath) throws IOException{
		this.filePath = filePath;
		readFile();
	}
	
	public String[] getHeader() {
		return header;
	}

	public List<Map<String, String>> getLines() {		
		List<Map<String, String>> list = new LinkedList<Map<String,String>>();
		for(String[] line : lines)
		{
			Map<String, String> map = new HashMap<String, String>();			
			if(line.length != header.length){
				//TODO record Error
				continue;
			}		
			for (int j = 0; j < header.length; j++) {
				map.put(header[j], line[j]);
			}
			list.add(map);
		}	
		return list;
	}

	private void readFile() throws IOException{
		CSVReader reader = new CSVReader(filePath);
		this.lines = new LinkedList<String[]>();	
		try{
			//escape the first line which is the column name
			reader.next();
			header = reader.getRecord();
			if(header == null){
				throw new CatfishException(TestingToolException.NO_HEADER_ERROR);
			}
			trim(header);
			while(reader.next())
			{
				String[] line = reader.getRecord();
				trim(line);
				lines.add(line);
			}			
		}
		finally{
			reader.close();
		}
	}
	
	private static void trim(String[] strs){
		for (int i = 0; i < strs.length; i++) {
			strs[i] = strs[i].trim(); 
		}
	}
}
