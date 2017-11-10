package engine.rule.model.inout.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import catfish.base.StringUtils;
import catfish.base.business.common.AppDerivativeVariableConsts;
import catfish.base.business.common.RuleEngineDecisionResult;
import catfish.base.business.util.CommonUtil;

import com.huateng.toprules.core.annotation.ModelField;
import com.huateng.toprules.core.annotation.ModelInstance;
import com.huateng.toprules.core.annotation.ModelMethod;

import engine.databaseapi.DerivativeVariableApi;
import engine.main.Configuration;
import engine.rule.config.ModelConfig;
import engine.rule.model.BaseForm;
import engine.rule.model.annotation.DBField;

@ModelInstance(description = "决策结果")
public class DecisionInOutForm extends BaseForm {

	@ModelField(name = "设置(this)的首付比为")
	private BigDecimal downPaymentRate = new BigDecimal(-1);

	@ModelField(name = "决策结果(默认为通过)")
	private Integer decisionResult = RuleEngineDecisionResult.Approved.getValue();

	@ModelField(name = "分流标签")
	private String distributeTag = StringUtils.EMPTY_STRING;

	private Set<String> decisionRejectReasonSet = new HashSet<String>();

	private List<Integer> randomNumList = new ArrayList<>();

	private List<Integer> randomNumResultList = new ArrayList<>();

	// 重传原因
	private Set<String> needReUploadReasonSet = new HashSet<String>();

	// 分层策略代号，默认为F1
	@DBField(fieldName = DerivativeVariableApi.StringValue, variableType = AppDerivativeVariableConsts.SegmentationCode)
	@ModelField(name = "分层策略代号(默认为最全流程)")
	// private String segmentationCode = Configuration.getDefaultSegmentation();
	private String segmentationCode = "F1";

	@ModelField(name = "策略代号")
	private String strategyCode = "";

	@ModelField(name = "业务申请评分")
	private Double applicationScore = 0.0;

	@ModelField(name = "行为评分")
	private Double behaviorScore = 0.0;

	@ModelField(name = "一致性检查评分")
	private Double consistencyCheckScore = 0.0;

	@ModelField(name = "征信评分")
	private Double creditReferenceScore = 0.0;

	@ModelField(name = "欺诈信息评分")
	private Double fraudCheckScore = 0.0;

	@ModelField(name = "调查评分")
	private Double investigationScore = 0.0;

	@ModelField(name = "特征评分")
	private Double personalInfoScore = 0.0;

	@ModelField(name = "社会关系评分")
	private Double socialRelationScore = 0.0;

	@ModelField(name = "重传次数")
	private Integer reuploadCount = 0;

	@ModelField(name = "是否需要交易监控")
	private boolean transactionMonitorRequired = false;

	@ModelField(name = "是否需要Instinct交易监控")
	private boolean instinctMonitorRequired = false;

	@ModelField(name = "是否强签(默认否)")
	@DBField(fieldName = DerivativeVariableApi.BoolValue, variableType = AppDerivativeVariableConsts.IsCompulsorySigning)
	private boolean compulsorySigning = false;

	private List<String> fraudFlagList = new ArrayList<String>();

	private Set<Integer> needReUploadFlagSet = new HashSet<>();

	// 推荐人的角色
	@DBField(fieldName = DerivativeVariableApi.IntValue, variableType = AppDerivativeVariableConsts.CheckAffiliateRecommendResult)
	private int affiliateRecommendRole = -1;

	// 是否被人推荐
	@ModelMethod(name = "(this)的申请是否有人推荐")
	public boolean someOneRecommend() {
		return this.affiliateRecommendRole != -1;
	}

	@ModelMethod(name = "(this)的推荐角色包含(#1,<推荐人角色>)", paramDomains = "engine.rule.domain.RecommendUserRole")
	public boolean containsRecommendRole(int role) {
		Set<Integer> roleSet = CommonUtil.decomposeBinarySum(this.affiliateRecommendRole);
		return roleSet.contains(role);
	}

	public DecisionInOutForm() {
		for (int i = 0; i < ModelConfig.randomNumCount; i++) {
			this.randomNumList.add(new Random().nextInt(1000));
			this.randomNumResultList.add(null);
		}
	}

	@ModelMethod(name = "设置(this)的决策为通过申请")
	public void approveDecision() {
		this.decisionResult = RuleEngineDecisionResult.Approved.getValue();
	}

	@ModelMethod(name = "设置(this)的决策为拒绝申请")
	public void rejectDecision() {
		this.decisionResult = RuleEngineDecisionResult.Rejected.getValue();
	}

	@ModelMethod(name = "设置(this)的决策为取消申请")
	public void cancelDecision() {
		this.decisionResult = RuleEngineDecisionResult.Canceled.getValue();
	}

	@ModelMethod(name = "设置(this)的决策为重新检查")
	public void recheckingRequiredDecision() {
		this.decisionResult = RuleEngineDecisionResult.RecheckingRequired.getValue();
	}

	@ModelMethod(name = "向(this)的决策拒绝原因中加入(#1,<拒绝原因代号>)")
	public void addDecisionRejectReson(String reson) {
		this.decisionRejectReasonSet.add(reson);
	}

	@ModelMethod(name = "向(this)输出代号为(#1,<策略代号>)的分层策略")
	public void gotoSegementationBranch(String segmentationCode) {
		this.segmentationCode = segmentationCode;
	}

	@ModelMethod(name = "向(this)的触发欺诈检测标识中加入(#1,<标识>)")
	public void addFraudCheckFlag(String flag) {
		fraudFlagList.add(flag);
	}

	@ModelMethod(name = "让(this)的申请人重新上传(#1,<文件类型>)", paramDomains = "engine.rule.domain.app.UploadFileFlag")
	public void addFlagToNeedReUpload(int flag) {
		recheckingRequiredDecision();
		needReUploadFlagSet.add(flag);
	}

	// 重传原因
	@ModelMethod(name = "给(this)的申请人添加重传原因(#1,<上传原因>)", paramDomains = {
			"engine.rule.domain.app.PicReuploadReasonForApp" })
	public void addReasonToNeedReUpload(String reason) {
		recheckingRequiredDecision();
		this.needReUploadReasonSet.add(reason);
	}

	public Double getFraudCheckScore() {
		return fraudCheckScore;
	}

	@ModelMethod(name = "(this)的第(#1,<位置>)个决策随机数,范围0-999", paramDomains = "engine.rule.domain.RandomNumber")
	public int getRandomNumber(int index) {
		return this.randomNumList.get(index);
	}

	@ModelMethod(name = "向(this)的决策结果中加入第(#1,<位置>)个随机数", paramDomains = "engine.rule.domain.RandomNumber")
	public void addRandomNumberToResult(int index) {
		this.randomNumResultList.set(index, this.randomNumList.get(index));
	}

	@ModelMethod(name = "设置(this)的决策为重填")
	public void rewrite() {
		this.decisionResult = RuleEngineDecisionResult.Rewrite.getValue();
	}

	@ModelMethod(name = "设置(this)的决策为不重填")
	public void noRewrite() {
		this.decisionResult = RuleEngineDecisionResult.NORewrite.getValue();
	}

	public BigDecimal getDownPaymentRate() {
		return downPaymentRate;
	}

	public void setDownPaymentRate(BigDecimal downPaymentRate) {
		this.downPaymentRate = downPaymentRate;
	}

	public void setFraudCheckScore(Double fraudCheckScore) {
		this.fraudCheckScore = fraudCheckScore;
	}

	public Integer getDecisionResult() {
		return decisionResult;
	}

	public void setDecisionResult(Integer decisionResult) {
		this.decisionResult = decisionResult;
	}

	public Double getApplicationScore() {
		return applicationScore;
	}

	public void setApplicationScore(Double applicationScore) {
		this.applicationScore = applicationScore;
	}

	public Double getBehaviorScore() {
		return behaviorScore;
	}

	public void setBehaviorScore(Double behaviorScore) {
		this.behaviorScore = behaviorScore;
	}

	public Double getConsistencyCheckScore() {
		return consistencyCheckScore;
	}

	public void setConsistencyCheckScore(Double consistencyCheckScore) {
		this.consistencyCheckScore = consistencyCheckScore;
	}

	public Double getCreditReferenceScore() {
		return creditReferenceScore;
	}

	public void setCreditReferenceScore(Double creditReferenceScore) {
		this.creditReferenceScore = creditReferenceScore;
	}

	public Double getInvestigationScore() {
		return investigationScore;
	}

	public void setInvestigationScore(Double investigationScore) {
		this.investigationScore = investigationScore;
	}

	public Double getPersonalInfoScore() {
		return personalInfoScore;
	}

	public void setPersonalInfoScore(Double personalInfoScore) {
		this.personalInfoScore = personalInfoScore;
	}

	public Double getSocialRelationScore() {
		return socialRelationScore;
	}

	public void setSocialRelationScore(Double socialRelationScore) {
		this.socialRelationScore = socialRelationScore;
	}

	public List<String> getFraudFlagList() {
		return fraudFlagList;
	}

	public void setFraudFlagList(List<String> fraudFlagList) {
		this.fraudFlagList = fraudFlagList;
	}

	public Set<Integer> getNeedReUploadFlagSet() {
		return needReUploadFlagSet;
	}

	public void setNeedReUploadFlagSet(Set<Integer> needReUploadFlagSet) {
		this.needReUploadFlagSet = needReUploadFlagSet;
	}

	public Set<String> getDecisionRejectReasonSet() {
		return decisionRejectReasonSet;
	}

	public void setDecisionRejectReasonSet(Set<String> decisionRejectReasonSet) {
		this.decisionRejectReasonSet = decisionRejectReasonSet;
	}

	public int getAffiliateRecommendRole() {
		return affiliateRecommendRole;
	}

	public void setAffiliateRecommendRole(int affiliateRecommendRole) {
		this.affiliateRecommendRole = affiliateRecommendRole;
	}

	public List<Integer> getRandomNumList() {
		return randomNumList;
	}

	public void setRandomNumList(List<Integer> randomNumList) {
		this.randomNumList = randomNumList;
	}

	public List<Integer> getRandomNumResultList() {
		return randomNumResultList;
	}

	public void setRandomNumResultList(List<Integer> randomNumResultList) {
		this.randomNumResultList = randomNumResultList;
	}

	public Integer getReuploadCount() {
		return reuploadCount;
	}

	public void setReuploadCount(Integer reuploadCount) {
		this.reuploadCount = reuploadCount;
	}

	public String getSegmentationCode() {
		return segmentationCode;
	}

	public void setSegmentationCode(String segmentationCode) {
		this.segmentationCode = segmentationCode;
	}

	public boolean isTransactionMonitorRequired() {
		return transactionMonitorRequired;
	}

	public void setTransactionMonitorRequired(boolean transactionMonitorRequired) {
		this.transactionMonitorRequired = transactionMonitorRequired;
	}

	public String getStrategyCode() {
		return strategyCode;
	}

	public void setStrategyCode(String strategyCode) {
		this.strategyCode = strategyCode;
	}

	public boolean isCompulsorySigning() {
		return compulsorySigning;
	}

	public void setCompulsorySigning(boolean compulsorySigning) {
		this.compulsorySigning = compulsorySigning;
	}

	public String getDistributeTag() {
		return distributeTag;
	}

	public void setDistributeTag(String distributeTag) {
		this.distributeTag = distributeTag;
	}

	public boolean isInstinctMonitorRequired() {
		return instinctMonitorRequired;
	}

	public void setInstinctMonitorRequired(boolean instinctMonitorRequired) {
		this.instinctMonitorRequired = instinctMonitorRequired;
	}

	public Set<String> getNeedReUploadReasonSet() {
		return needReUploadReasonSet;
	}

	public void setNeedReUploadReasonSet(Set<String> needReUploadReasonSet) {
		this.needReUploadReasonSet = needReUploadReasonSet;
	}
	
}
