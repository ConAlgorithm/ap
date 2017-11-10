package engine.rule.model.in.cashloan;

import java.util.Date;

import catfish.base.business.util.AppDerivativeVariableNames;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;

import engine.databaseapi.DerivativeVariableApi;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "征信信息")
public class CreditReferenceInForm extends BaseForm {

	/************** 前海征信 **********************/
	@DBField(fieldName = DerivativeVariableApi.DateTimeValue, variableType = AppDerivativeVariableNames.QHZX_DATA_LATEST_BUILDTIME)
	@ModelField(name = "前海征信中最近的黑名单创建时间")
	private Date latestDataBuildTime;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HASBEENEXECUTED)
	@ModelField(name = "前海征信中是否是被执行人")
	private boolean hasBeenExecuted;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_BREAKPROMISE)
	@ModelField(name = "前海征信中是否有违约记录")
	private boolean breakPromise;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_BREAKPROMISEBEENEXECUTED)
	@ModelField(name = "前海征信中是否是失信被执行人")
	private boolean breakPromiseBeenExecuted;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.QHZX_MAXIMIZEOVERDUEDAY)
	@ModelField(name = "前海征信中最严重逾期时间", bindDomain = "engine.rule.domain.CheckUserRiskRevealResult")
	private int maximizeOverdueDay = -99;

	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableNames.QHZX_MAXIMIZEMONEYBOUND)
	@ModelField(name = "前海征信中最高涉足金额范围")
	private int maximizeMoneyBound = -99;

	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableNames.QHZX_HASBLACKLIST)
	@ModelField(name = "前海征信中是否有征信记录")
	private boolean hasBlackList;

	/*******************************************/

	public Date getLatestDataBuildTime() {
		return latestDataBuildTime;
	}

	public void setLatestDataBuildTime(Date latestDataBuildTime) {
		this.latestDataBuildTime = latestDataBuildTime;
	}

	public boolean isHasBeenExecuted() {
		return hasBeenExecuted;
	}

	public void setHasBeenExecuted(boolean hasBeenExecuted) {
		this.hasBeenExecuted = hasBeenExecuted;
	}

	public boolean isBreakPromise() {
		return breakPromise;
	}

	public void setBreakPromise(boolean breakPromise) {
		this.breakPromise = breakPromise;
	}

	public boolean isBreakPromiseBeenExecuted() {
		return breakPromiseBeenExecuted;
	}

	public void setBreakPromiseBeenExecuted(boolean breakPromiseBeenExecuted) {
		this.breakPromiseBeenExecuted = breakPromiseBeenExecuted;
	}

	public int getMaximizeOverdueDay() {
		return maximizeOverdueDay;
	}

	public void setMaximizeOverdueDay(int maximizeOverdueDay) {
		this.maximizeOverdueDay = maximizeOverdueDay;
	}

	public int getMaximizeMoneyBound() {
		return maximizeMoneyBound;
	}

	public void setMaximizeMoneyBound(int maximizeMoneyBound) {
		this.maximizeMoneyBound = maximizeMoneyBound;
	}

	public boolean isHasBlackList() {
		return hasBlackList;
	}

	public void setHasBlackList(boolean hasBlackList) {
		this.hasBlackList = hasBlackList;
	}
}
