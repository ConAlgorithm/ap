package engine.rule.config;

public class ConfigItemBean {

	private String projectName = null;

	private String baselineVersion = null;

	private String ruleSetName = null;

	private String ruleSetVersion = null;

	private boolean recordHitFlag = true;

	private boolean saveRuleFlowInfoFlag = false;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getBaselineVersion() {
		return baselineVersion;
	}

	public void setBaselineVersion(String baselineVersion) {
		if (baselineVersion.equals(""))
			return;
		this.baselineVersion = baselineVersion;
	}

	public String getRuleSetName() {
		return ruleSetName;
	}

	public void setRuleSetName(String ruleSetName) {
		if (ruleSetName.equals(""))
			return;
		this.ruleSetName = ruleSetName;
	}

	public String getRuleSetVersion() {
		return ruleSetVersion;
	}

	public void setRuleSetVersion(String ruleSetVersion) {
		if (ruleSetVersion.equals(""))
			return;
		this.ruleSetVersion = ruleSetVersion;
	}

	public boolean isRecordHitFlag() {
		return recordHitFlag;
	}

	public void setRecordHitFlag(boolean recordHitFlag) {
		this.recordHitFlag = recordHitFlag;
	}

	public boolean isSaveRuleFlowInfoFlag() {
		return saveRuleFlowInfoFlag;
	}

	public void setSaveRuleFlowInfoFlag(boolean saveRuleFlowInfoFlag) {
		this.saveRuleFlowInfoFlag = saveRuleFlowInfoFlag;
	}

	public static Builder Builder() {
		ConfigItemBean item = new ConfigItemBean();
		return item.new Builder(item);
	}

	public class Builder {

		private ConfigItemBean config;

		public Builder(ConfigItemBean config) {
			this.config = config;
		}

		public Builder projectName(String projectName) {
			config.projectName = projectName;
			return this;
		}

		public Builder baselineVersion(String baseLineVersion) {
			config.baselineVersion = baselineVersion;
			return this;
		}

		public Builder ruleSetName(String ruleSetName) {
			config.ruleSetName = ruleSetName;
			return this;
		}

		public Builder ruleSetVersion(String ruleSetVersion) {
			config.ruleSetVersion = ruleSetVersion;
			return this;
		}

		public Builder recordHitFlag(boolean recordHitFlag) {
			config.recordHitFlag = recordHitFlag;
			return this;
		}

		public Builder saveRuleFlowInfoFlag(boolean saveRuleFlowInfoFlag) {
			config.saveRuleFlowInfoFlag = saveRuleFlowInfoFlag;
			return this;
		}

		public ConfigItemBean build() {
			return this.config;
		}
	}
}
