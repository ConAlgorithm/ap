package network.relationship.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManipulator {
	public static void Write(String filePath, String content) throws IOException{
		FileWriter fileWriter = new FileWriter(filePath, true);
		fileWriter.write(content);
		fileWriter.write("\r\n");
		fileWriter.close();
	}
	
	public static List<String> Read(String filePath) throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
		String line = null;
		List<String> list = new ArrayList<String>(); 
		while((line = bufferedReader.readLine()) != null){
			list.add(line);
		}
		
		return list;
	}
	
	private static int getIndex(String fileName){
		String[] strs = fileName.split("\\.");
		return Integer.parseInt(strs[2]);
	}
	
	public static void MergeFile(String fileFolder, String destinationFilePath) throws IOException{
		File[] files = new File(fileFolder).listFiles();
		String[] fileNames = new String[files.length];
		for (int i = 0; i < files.length; i++) {
			if(!files[i].isFile())
				continue;
			
			fileNames[getIndex(files[i].getName())] = files[i].getAbsolutePath();
		}
		
		//StringBuffer sb = new StringBuffer();
		for (int i = fileNames.length - 1; i >= 0; --i) {
			//sb.append(Read(fileNames[i]));
			List<String> list = Read(fileNames[i]); 
			for (int j = 0; j < list.size(); j++) {
				Write(destinationFilePath, list.get(j));	
			}
		}
	}
}
