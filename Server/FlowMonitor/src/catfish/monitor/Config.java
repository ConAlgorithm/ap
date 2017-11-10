package catfish.monitor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class Config {
    public List<String> ignored;
    public int maxMinutes;
    public Map<String, Integer> activities;
    
    public static Config load(String name) throws FileNotFoundException{
        InputStream stream = new FileInputStream("config/"+name+".js");
        Config config = new Gson().fromJson(new InputStreamReader(stream), Config.class);
        return config;
    }
}
