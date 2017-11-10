package export.analyzer;

public class JunYuRecord {

	public String appId;
	public int type;
	public int returnValue;
	public int idSimilarity;
	public int captureSimilarity;
	
	public JunYuRecord(String[] record)
	{
		this.appId = record[0];
		this.type = record[1].equals("CheckJunyu3Photo") ? 3 : 2;
		this.returnValue = Integer.parseInt(record[2]);
		this.idSimilarity = Integer.parseInt(record[3]);
		this.captureSimilarity = Integer.parseInt(record[4]);
	}
}
