package omni.model.type;

public class MongoDate 
{
	private String $date;
	
	public final String getDate()
	{
		return this.$date;
	}
	
	@Override
	public final String toString()
	{
		return this.$date;
	}
//	 { "$date" : "2015-09-25T06:58:51.000Z"}
}
