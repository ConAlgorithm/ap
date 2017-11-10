package catfish.flowcontroller.util;

import catfish.base.DynamicConfig;
import catfish.base.StartupConfig;
import catfish.flowcontroller.DomainConsts;

public class JobUtils {
	
	public static int getRecheckDelay(){
        return DynamicConfig.readAsInt(
                DomainConsts.RECHECK_CALL_DELAY, DomainConsts.DEFAULT_RECHECK_CALL_DELAY);
    }
	
    public static int getMaxCheck(){
        return DynamicConfig.readAsInt(
                DomainConsts.MAX_CALL_CHECKS, DomainConsts.DEFAULT_MAX_CALL_CHECKS);
    }
    
    public static boolean readIsD1CheckNessesaryConfiguration()
    {
    	 return StartupConfig.getAsBoolean("IsD1CheckNessesary");
    }
}
