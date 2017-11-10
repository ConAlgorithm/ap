package export;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Common {
  
  public static List<String> getAppIds(String appIdFile)
  {
    BufferedReader reader=null;
        List<String> appIds=new LinkedList<String>();
        try{
           reader=new BufferedReader(new FileReader(appIdFile));
           String appid;
           while(((appid=reader.readLine())!=null)){
               appIds.add(appid);
           }
           reader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return appIds;
  }

}
