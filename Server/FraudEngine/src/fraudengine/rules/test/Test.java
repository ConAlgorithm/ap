package fraudengine.rules.test;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.execution.ExecutionController;
import catfish.base.database.DatabaseConfig;
import fraudengine.Configuration;
import fraudengine.rules.A010;
import fraudengine.rules.A012;

public class Test {

	public static void main(String[] args)
	{
		StartupConfig.initialize();
		Logger.initialize();
		DatabaseConfig.initialize();
		Configuration.initialize();
	
		//System.out.println(ExecutionController.ifMatchExecution("A013", type));
		A010 b2 = new A010("","",100,"");
		CatfishEnum<Integer> a1 = b2.run("64072662-7def-e311-b348-e0db5516d568");
		System.out.println("B002:" + a1.getValue());
		
		CatfishEnum<Integer> a2 = b2.run("5d01aa1b-03ee-e311-b348-e0db5516d568");
		System.out.println("B002:" + a2.getValue());
		
		CatfishEnum<Integer> a3 = b2.run("c6963ecc-83a7-4ebc-ae04-fc849ed3a46a");
		System.out.println("B002:" + a3.getValue());
	}
}
