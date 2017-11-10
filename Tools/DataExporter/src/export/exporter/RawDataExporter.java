package export.exporter;

import catfish.base.business.util.RawDatas;
import catfish.base.file.IWritable;

public abstract class RawDataExporter implements IExportable<RawDatas>{

    protected IWritable writer;
	
	public RawDataExporter(IWritable writer) {
		this.writer = writer;
	}
	
	public void writeHeader(){}
}
