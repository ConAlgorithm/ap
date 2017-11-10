package export.migration.qhzx;

import jma.handlers.preprocess.model.CheckUserCreditOn3rdPartyPreModel;
import thirdparty.config.QhzxConfiguration;
import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableNames;
import catfish.base.file.CSVReader;
import catfish.base.file.CSVWriter;
import export.migration.IMigratable;

/*http://jira.fenqi.im:8080/browse/POS-455
由于12月1日至2月4期间，
前海征信1.0开关是属于关闭状态，
所以需要针对这段时间的申请成功的用户进行重新跑批以便前海征信，
看看期间是否会有中过黑名单的申请。
*/
public class QHZXMigration implements IMigratable{

	private String selectSql = "SELECT Id FROM InstallmentApplicationObjects where DateAdded > '2015-12-01 00:00:00.000' and DateAdded < '2016-02-05 00:00:00.000' order by DateAdded asc";
	private CheckUserOnQHHandler handler = new CheckUserOnQHHandler();
	
	private static final String PATH = "qhzx/";
	@Override
	public void migrate() {
		QhzxConfiguration.initialize();
		CSVReader reader = new CSVReader(PATH + "input.csv");
		CSVWriter errorWriter = new CSVWriter(PATH + "error.csv");
		CSVWriter successWriter = new CSVWriter(PATH + "success.csv");
		CSVWriter recordWriter = new CSVWriter(PATH + "record.csv");
		recordWriter.write(new String[]{
				"AppId",
				AppDerivativeVariableNames.QHZX_HASBLACKLIST,
				AppDerivativeVariableNames.QHZX_HASBEENEXECUTED,
				AppDerivativeVariableNames.QHZX_BREAKPROMISE,
				AppDerivativeVariableNames.QHZX_BREAKPROMISEBEENEXECUTED,
				AppDerivativeVariableNames.QHZX_DATA_LATEST_BUILDTIME,
				AppDerivativeVariableNames.QHZX_MAXIMIZEOVERDUEDAY,
				AppDerivativeVariableNames.QHZX_MAXIMIZEMONEYBOUND
				});

		String [] input = null;
		String appId = null;
		while(reader.next())
		{
			input = reader.getRecord();
			appId = input[0];
			handler.setPreModel(new CheckUserCreditOn3rdPartyPreModel(input[2], input[1]));
			handler.setId(appId);
			//handler.preprocess();
			if(handler.checkValid())
			{
				try {
					String[] line = handler.getResult();
					recordWriter.write(line);
					successWriter.write(new String[]{appId});
					Logger.get().info("Succeed: " + appId);
				} catch (Exception e) {
					Logger.get().error(String.format("Migration For AppId: %s failed!", appId));
					errorWriter.write(new String[]{appId});
				}
			}
			try {
				Thread.sleep(500);
			} catch (Exception e) {			
			}
		}
	}
}
