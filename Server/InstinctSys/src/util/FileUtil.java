package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileWriter;
//import java.io.Writer;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.output.FileWriterWithEncoding;

public final class FileUtil 
{
	private FileUtil()
	{
		
	}
	
	public static void readFile(Path infile)
	{
		Charset charset = Charset.forName("GB2312");
		try (BufferedReader reader = Files.newBufferedReader(infile, charset)) 
		{
		    String line = null;
		    while ((line = reader.readLine()) != null) 
		    {
		        System.out.println(line);
		    }
		} 
		catch (IOException x) 
		{
		    System.err.format("IOException: %s%n", x);
		}
	}
	
	public static void writeFile(String outString, String outfile)
	{
		try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriterWithEncoding(outfile, Charset.forName("GB2312"), true)))) 
		{
			writer.println(outString);
//			 System.out.println(Charset.availableCharsets());
		} 
		catch (IOException x) 
		{
			System.err.format("IOException: %s%n", x);
		}
	}
	
	public static Boolean deleteFile(String delfile)
	{
		String info = null;
		boolean del = false;
		try
		{
    		File file = new File(delfile);
    		if (file.delete())
    		{
    			info = file.getName() + " is deleted!";
    			System.out.println(info);
    			del = true;
    		}
    		else
    		{
    			info = "Delete " + file.getName() + " failed.";
    			System.out.println(info);
    			del = false;
    		}
    	}
		catch (Exception e)
		{
    		e.printStackTrace();
    		return false;
    	}
		return del;
	}
}
