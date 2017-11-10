package engine.rule.test.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.util.encoders.Base64;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import catfish.base.exception.CatfishException;
import engine.rule.test.TestingToolException;
import engine.rule.test.util.PathUtil;

public class TestCaseData {

	public static final Map<String, InstalmentChannel> ProductChannelMap = new HashMap<>();
	public static final Map<String, String> ProductPackageMap = new HashMap<>();
	public static final Map<String, String> HandlerDecisionOutMap = new HashMap<>();
	public static final String DecionOutName = "inout_Decision";
	public static final String LoanMoneyDecisionOutName = "out_LoanMoney";

	static{
		ProductChannelMap.put("POS", InstalmentChannel.App);
		ProductChannelMap.put("PDL", InstalmentChannel.PayDayLoanApp);
		ProductChannelMap.put("CASHLOAN", InstalmentChannel.CashLoan);

		ProductPackageMap.put("POS", "app");
		ProductPackageMap.put("PDL", "pdl");
		ProductPackageMap.put("CASHLOAN", "cashloan");
		
		HandlerDecisionOutMap.put("AntiFraudCheck", LoanMoneyDecisionOutName);
	}

	public String product;

	public InstalmentChannel channel;

	public String pkgName;
	
	public String decisionPoint;

	public String data;

	public String fileName;

	public String filePath;

	public TestCaseData(String product, String decisionPoint, String data)
	{
		this.product = product;
		this.decisionPoint = decisionPoint;
		this.data = data;
	}
	
	private void convertData()
	{
		byte[] result = Base64.decode(data);
		this.data = new String(result);
		System.out.println(data);
	}
	public void init()
	{
		channel = ProductChannelMap.get(product);
		pkgName = ProductPackageMap.get(product);
		if(fileName == null)
		{
			long time = new Date().getTime();
		    fileName = String.format("%s_%s_%d.csv", product, decisionPoint, time);
		}
		filePath = PathUtil.TestingDataPath + fileName;
	}

	public void storeData()
	{
		init();
		FileOutputStream out = null;
		try {
			//convertData();
			out = new FileOutputStream(new File(filePath));
			out.write(data.getBytes());
		} catch (Exception e) {
			Logger.get().error("Can not store Data file: " + filePath, e);
			throw new CatfishException(TestingToolException.STORE_TESTCASE_ERROR);
		}finally{
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					Logger.get().warn("Cannot Close OutStream of : " + filePath, e);
				}
		}
	}
}
