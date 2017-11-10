package export.exporter.jxl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.business.util.RawDatas;
import catfish.base.file.IWritable;
import catfish.flowcontroller.models.Application;
import catfish.flowcontroller.storage.MongoDBStorage;

import com.google.gson.Gson;

import export.exporter.RawDataExporter;

public class JXLSegmentationExporter extends RawDataExporter{

	private static final String Key = RawDataVariableNames.JXL_REPORTDATA_RAW_DATA + "_DateAdded";
	private static MongoDBStorage storage = new MongoDBStorage();
	public JXLSegmentationExporter(IWritable writer) {
		super(writer);
		writeHeader();
	}

	public void writeHeader()
	{
		String[] header = new String[]{"AppId", "JXLAddedTime", "SegmentationTime"};
		writer.write(Arrays.asList(header));
	}
	@Override
	public void export(String appId, RawDatas record) {
		List<String> result = new ArrayList<String>();
		result.add(appId);
		String jxlTime = this.getJxlAddedTime(appId);
		if(jxlTime != null)
		{
			result.add(jxlTime);
		}else
		{
			result.add("NULL");
		}
			
		String segTime = this.getSegmentationTime(appId);
		if(segTime != null)
		{
			result.add(segTime);
		}else{
			result.add("NULL");
		}	    
		writer.write(result);
	}
	private String getJxlAddedTime(String appId)
	{
		String time = null;
//		RawDatas data = RawDataStorageManager.getRawDatas(appId, Key);
//		Object jxl = data.getData().get(Key);
		String jxl=RawDataStorageManager.getRawData(appId, Key);
		if(jxl != null)
		{
	        DateTime jxlTime = new DateTime(jxl); 
	        time = jxlTime.toString("yyyy-MM-dd HH:mm:ss");
		}
		return time;
	}
	
	private String getSegmentationTime(String appId)
	{
		String time = null;
		Application app = storage.load(appId);
		if(app != null){
			String state = app.finishedActivities.get("Segmentation");
			if(state != null)
			{
				Map<String, Object> stateMap = new Gson().fromJson(state, Map.class);
				DateTime segTime = new DateTime(Long.parseLong(stateMap.get("startTime").toString()));
				time = segTime.toString("yyyy-MM-dd HH:mm:ss");
			}
	    }
		return time;
	}
}
