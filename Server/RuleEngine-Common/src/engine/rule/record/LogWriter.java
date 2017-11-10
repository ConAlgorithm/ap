package engine.rule.record;

import catfish.base.Logger;

public class LogWriter implements RecordWriter {

	@Override
	public void writeRecord(String[] record) {
		StringBuilder builder = new StringBuilder();
		for (String item : record) {
			builder.append(item);
			builder.append(",");
		}
	}

}
