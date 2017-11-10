package network.relationship.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CsvFileReader {

	private Map<String, Integer> headerIndexes = new HashMap<>();
	private Map<String, ArrayList<String>> records = new HashMap<>();

	public CsvFileReader(String filePath) throws IOException {
		parse(filePath);
	}

	public String getValue(String appId, String columnName) {
		if (!records.containsKey(appId)
				|| !headerIndexes.containsKey(columnName))
			return "";

		return records.get(appId).get(headerIndexes.get(columnName));
	}

	public Set<String> getAllAppIDs() {
		return records.keySet();
	}

	private void parse(String filePath) throws IOException {
		FileReader fr = new FileReader(filePath);
		BufferedReader br = new BufferedReader(fr);

		// header
		String s = br.readLine();
		String[] columns = s.split(",");
		for (int i = 0; i < columns.length; ++i) {
			headerIndexes.put(columns[i], i);
		}

		// values
		while ((s = br.readLine()) != null) {
			ArrayList<String> line = new ArrayList<>();
			columns = s.split(",");
			for (int i = 0; i < columns.length; i++) {
				line.add(columns[i]);
			}
			records.put(columns[0], line);
		}

		fr.close();
	}
}
