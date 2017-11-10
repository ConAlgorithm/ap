package engine.rule.execute;

import java.util.HashMap;
import java.util.Map;

import catfish.base.Logger;
import catfish.base.ThreadUtils;
import catfish.base.business.common.InstalmentChannel;

import com.google.gson.Gson;
import com.huateng.toprules.api.TopRulesSession;
import com.huateng.toprules.api.common.TopRulesConstants;
import com.huateng.toprules.api.session.TopRulesRequest;
import com.huateng.toprules.api.session.TopRulesResponse;
import com.huateng.toprules.api.session.TopRulesServiceProvider;
import com.huateng.toprules.api.session.TopRulesServiceProviderManager;

import engine.exception.CannotFindConfigItemException;
import engine.exception.RuleLinkException;
import engine.main.Configuration;
import engine.rule.config.ConfigItemBean;
import engine.rule.config.RuleConfigManager;
import engine.rule.model.creator.AbstractModelCreator;

public class RuleExecutor {

	private String ruleName;
	
	private InstalmentChannel channel;

	private RuleExecutor nextRuleExecutor;

	private RuleExecutor previousRuleExecutor;

	private TopRulesServiceProvider provider = TopRulesServiceProviderManager
			.register(TopRulesConstants.API_RULEEXECUTE_PROVIDER);

	private TopRulesSession topRulesSession = provider.createRuleSession();

	private TopRulesRequest topRulesRequest = new TopRulesRequest();

	public RuleExecutor(InstalmentChannel channel, String ruleName) throws CannotFindConfigItemException {
		this.ruleName = ruleName;
		this.channel = channel;

		ConfigItemBean config = RuleConfigManager.getConfigByChannel(channel).getRuleConfigByName(ruleName,
				ConfigItemBean.class);

		topRulesRequest.setProjectName(config.getProjectName());
		topRulesRequest.setVersion(config.getBaselineVersion());
		topRulesRequest.setRuleSetName(config.getRuleSetName()
				+ Configuration.getRulePostFix());
		topRulesRequest.setRuleSetVersion(config.getRuleSetVersion());
		topRulesRequest.setFlag(config.isRecordHitFlag());
		topRulesRequest
				.setSaveRuleFlowInfoFlag(config.isSaveRuleFlowInfoFlag());
	}

	public RuleExecutor nextRule(RuleExecutor nextRuleExecutor)
			throws RuleLinkException {
		if (nextRuleExecutor != null) {
			if (nextRuleExecutor == this)
				throw RuleLinkException.LinkSelf(ruleName);

			if (!_checkEndlessLoop(nextRuleExecutor))
				throw RuleLinkException.EndLessLoop(ruleName);

			this.nextRuleExecutor = nextRuleExecutor;
			nextRuleExecutor.previousRuleExecutor = this;
		}

		return nextRuleExecutor;
	}

	private boolean _checkEndlessLoop(RuleExecutor nextRuleExecutor) {
		RuleExecutor previousRule = this.previousRuleExecutor;
		RuleExecutor nextRule = nextRuleExecutor.nextRuleExecutor;
		while (previousRule != null) {
			if (previousRule == nextRuleExecutor)
				return false;
			previousRule = previousRule.previousRuleExecutor;
		}
		while (nextRule != null) {
			if (nextRule == this)
				return false;
			nextRule = nextRule.nextRuleExecutor;
		}
		return true;
	}

	public RuleExecutor getNextRule() {
		return this.nextRuleExecutor;
	}

	public RuleExecutor getPreviousRule() {
		return this.previousRuleExecutor;
	}

	public final Map<String, Object> ExecuteRuleAndGetResponse(
			AbstractModelCreator inModelCreator,
			AbstractModelCreator outModelCreator) throws Exception {
		return _ExecuteRuleAndGetResponse(inModelCreator, outModelCreator,
		        RuleConfigManager.getConfigByChannel(channel).getRetryRuleCount());
	}

	private final Map<String, Object> _ExecuteRuleAndGetResponse(
			AbstractModelCreator inModelCreator,
			AbstractModelCreator outModelCreator, int tryCount)
			throws Exception {
		try {
			Map<String, Object> inParam = new HashMap<String, Object>();
			inParam.putAll(inModelCreator.createModelForm());
			// 加入输出
			inParam.putAll(outModelCreator.createModelForm());
			
			try{
				Logger.get().debug("inParam is " + new Gson().toJson(inParam));
			}catch(Exception e){
				Logger.get().debug("inParam can not to json :", e);
			}
			
			topRulesRequest.setInputParameters(inParam);
			String bussinessNo = inModelCreator.createBusinessNo();
			if (bussinessNo != null) {
				topRulesRequest.setBusinessNo(bussinessNo);
			}
			// 执行规则集
			TopRulesResponse topRulesResponse = topRulesSession
					.executeRules(topRulesRequest);
			// 处理响应参数
			final Map<String, Object> outParams = topRulesResponse
					.getOutputParameters();
			// if (this.nextRuleExecutor != null)
			// return this.nextRuleExecutor
			// .ExecuteRuleAndGetResponse(new AbstractApplicationModelCreator()
			// {
			//
			// @Override
			// public Map<String, Object> createModelForm() {
			// // TODO Auto-generated method stub
			// return outParams;
			// }
			//
			// @Override
			// public String createBusinessNo() {
			// // TODO Auto-generated method stub
			// return null;
			// }
			//
			// @Override
			// public void addCreator(AbstractApplicationModelCreator creator) {
			// // TODO Auto-generated method stub
			//
			// }
			//
			// @Override
			// public void removeCreator(AbstractApplicationModelCreator
			// creator) {
			// // TODO Auto-generated method stub
			//
			// }
			//
			// });
			return outParams;
		} catch (Exception e) {
			Logger.get().warn(
					String.format("Excecute rule error of retry: %d times",
							tryCount));
			if (tryCount != 1) {
				ThreadUtils.sleepInSeconds(Configuration.getRetrySleepySeconds());
				return _ExecuteRuleAndGetResponse(inModelCreator,
						outModelCreator, tryCount - 1);
			} else
				throw e;
		}
	}
}
