package jma.test;

import jma.Configuration;
import jma.RetryRequiredException;
import thirdparty.config.TdConfiguration;
import thirdparty.td.TdApi;
import thirdparty.td.Rule;
import thirdparty.td.Response;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientConfig;
import catfish.base.persistence.queue.PersistenceConfig;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class TestTdBlacklist {

  public static void main(String[] args) throws RetryRequiredException {
    StartupConfig.initialize();
    Logger.initialize();
    Configuration.readConfiguration();
    HttpClientConfig.initialize();
    TdConfiguration.initialize();
    PersistenceConfig.initialize();
    Map<String,Integer> rules=new HashMap<String,Integer>();
    rules.put("33638",0);
    rules.put("33640",1);
    rules.put("33642",2);
    rules.put("33652",3);
    rules.put("33654",4);
    rules.put("33674",5);
    rules.put("33676",6);
    Integer lineNum=0;
    try {
        //Whatever the file path is.
        File statText = new File("D:/test_task/52/out.csv");
        FileOutputStream is = new FileOutputStream(statText);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer w = new BufferedWriter(osw);
  
   // File file=new File("D:/test_task/52/data.csv");
        try{
        	BufferedReader in = new BufferedReader(new FileReader("D:/test_task/52/data.csv"));
        	String s;
        	while((s = in.readLine()) != null){
        		String[] var = s.split(",");
        		Response result=TdApi.query(var[0], var[1], var[2]);
        	//	System.out.print(var[0]+"\t"+var[1]+"\t"+var[2]);
        		w.write(var[0]+"\t"+var[1]+"\t"+var[2]);
        		boolean[] arrList={false,false,false,false,false,false,false};
        		for(Rule temp: result.getHit_rules()){
        			arrList[rules.get(temp.getId())]=true;
        		}
        		for(boolean bl:arrList){
        		//	System.out.println("\t"+bl);
        			w.write(("\t"+bl));
        		}
        		lineNum++;
            	System.out.println(lineNum);
        	//	System.out.println("\n");
        		w.write("\n");
        	}
        	in.close();

        }
        catch(Exception e){
        	e.printStackTrace();
        }
        w.close();
    } catch (IOException e) {
        System.err.println("Problem writing to the file statsTest.txt");
    }
    /*
   Response result=TdApi.query("舒洋", "362301199512014535", "15501317937");
   System.out.print("舒洋"+"\t"+"362301199512014535"+"\t"+"15501317937");
   boolean[] arrList={false,false,false,false,false,false,false};
  for(Rule temp: result.getHit_rules()){
   	arrList[rules.get(temp.getId())]=true;
  }
  for(boolean bl:arrList){
	  System.out.println("\t"+bl);
  }
 
  */
//    new CheckUserCreditOnTdHandler().execute("0D0AA0A5-D0D9-E411-B71F-005056C00008");
   }
}

