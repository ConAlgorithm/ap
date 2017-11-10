package catfish.flowcontroller.rest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import catfish.base.Logger;
import catfish.framework.IListener;
import catfish.framework.http.HttpData;

public class WorkFlowService implements IListener<HttpData>{
    public void onMessage(String representation, HttpData data) {
        String[] paths = data.getPaths();
        if(paths != null && paths.length>1){
            String workflowName= paths[1];
            data.setResponseData(this.getWorkflow(workflowName));
        } else {
            data.setResponseData("");
        }
    }
    
    private String getWorkflow(String workflowName){
        BufferedReader reader = null;
        try {
            InputStream stream = new FileInputStream("config/"+workflowName+".js");
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder sb =new StringBuilder();
            String str = reader.readLine();
            while(str !=null){
                sb.append(str);
                str = reader.readLine();
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            Logger.get().warn("Can not find config file for "+ workflowName, e);
            return "";
        } catch (IOException e) {
            Logger.get().warn("Read config file exception for "+ workflowName, e);
            return "";
        } finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    Logger.get().warn("Close BufferedReader exception for "+ workflowName, e);
                }
            }
        }
    }
}
