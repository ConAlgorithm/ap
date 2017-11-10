package util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class InstinctizeUtil
{
	private InstinctizeUtil()
	{
		
	}
	
	public static String string(Object obj)
	{
		if (obj instanceof String)
		{
			String s = (String) obj;
			return ((obj == null) || (obj == "null")) ? "" : s.replaceAll("\r|\n|\\|", " ");
		}
		
		if (obj instanceof Double)
		{
			Double dbv = (Double) obj; 
			BigDecimal db = new BigDecimal(dbv);
			return ((obj == null)) ? "" : db.toPlainString();
		}
		
		if ((obj instanceof Integer))
		{
			Integer intv = (Integer) obj; 
			BigDecimal db = new BigDecimal(intv);
			return ((obj == null)) ? "" : db.toPlainString();
		}			
		
		if ((obj instanceof Float))
		{
			Float fv = (Float) obj; 
			BigDecimal db = new BigDecimal(fv);
			return ((obj == null)) ? "" : db.toPlainString();
		}	
		
		if ((obj instanceof BigDecimal))
        {
            return ((obj == null)) ? "" : ((BigDecimal) obj).toPlainString();
        }
		
		return (obj == null) ? "" : obj.toString();
	}
	
	public static String date(Date date)
	{
		if (date == null) 
		{
			return InstinctizeUtil.string(date);
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String instinctDate = sdf.format(cal.getTime());
		return InstinctizeUtil.string(instinctDate);
	}
	
	public static void main(String[] args)
	{
		System.out.println(InstinctizeUtil.string("a|bc"));
	}
}
