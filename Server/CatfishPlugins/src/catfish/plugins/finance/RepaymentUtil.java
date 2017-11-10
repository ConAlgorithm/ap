package catfish.plugins.finance;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import catfish.base.Logger;

public class RepaymentUtil {
	

	private static double msOfDay = 1000*60*60*24;
	
	
	///
	//return   day2 - day1
	///
	public static int compareDiffDay(Date day1, Date day2)
	{

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str1 = df.format(day1);
		String str2 = df.format(day2);
		try {
			Date d1 = df.parse(str1);
			Date d2 = df.parse(str2);
			
			long diff = d2.getTime() - d1.getTime();
			int diffDays = new BigDecimal(diff/msOfDay).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
			
			return diffDays;
		} catch (ParseException e) {
			Logger.get().error("Date parse error" + e.getMessage());
		}
		
		return 0;
	}
      
	public static void throwExecption(String info)  
	{
		throw new RuntimeException(info);
	}
	
	public static BigDecimal double2Decimal(double value, int scale, int mode)
	{
		BigDecimal b = new BigDecimal(value);
		//return b.setScale(2, BigDecimal.ROUND_HALF_UP);
		return b.setScale(scale, mode);
	}
	
	public static String getDateDue(Date firstPaybackDay, int periodNum)
	{
		if (firstPaybackDay == null)
		{
			firstPaybackDay = new Date();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(firstPaybackDay);
		calendar.add(Calendar.MONTH, periodNum - 1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}
	
	
	public static Date getDateDue(Date oldTime, int field, int dt)
    {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(oldTime);
    	calendar.add(field, dt);
    	

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dayStr = df.format(calendar.getTime());
		try {
			return df.parse(dayStr);
		} catch (ParseException e) {
			Logger.get().error("Time parse error when get DateDue! Check the instalments!");
		}
		return calendar.getTime();
    }

}
