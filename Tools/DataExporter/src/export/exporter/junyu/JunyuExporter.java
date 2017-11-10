package export.exporter.junyu;

import java.util.ArrayList;
import java.util.List;

import jma.thirdpartyservices.junyu.JYChecker;
import catfish.base.business.util.RawDataStorageManager;
import catfish.base.business.util.RawDataVariableNames;
import catfish.base.business.util.RawDatas;
import catfish.base.file.IWritable;
import export.exporter.RawDataExporter;

public class JunyuExporter extends RawDataExporter{

	public JunyuExporter(IWritable writer) {
		super(writer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void export(String appId, RawDatas record) {
		exportJunYu(appId);
	}
	
	private void exportJunYu(String appId)
	{
		System.out.println(appId);
		String check3Photo = RawDataStorageManager.getRawData(appId, RawDataVariableNames.JUNYU_RAW_DATA + "_0");
		writeJunyuCsv(appId, check3Photo, "CheckJunyu3Photo");
		String check2Photo = RawDataStorageManager.getRawData(appId, RawDataVariableNames.JUNYU_RAW_DATA + "_1");
		writeJunyuCsv(appId, check2Photo, "CheckJunyu2Photo");
	}
	
	private void writeJunyuCsv(String appId, String result, String checkPoint)
	{
		List<String> record = new ArrayList<>();
		record.add(appId);
		
		if(result != null)
		{
			List<Integer> list = JYChecker.prepareResponse(result);
			record.add(checkPoint);
			for(Integer item : list)
			{
				record.add(item.toString());
			}
			writer.write(record);
		}		
	}
}
