package engine.rule.config;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import catfish.base.Logger;
import catfish.base.business.common.InstalmentChannel;
import engine.exception.CannotFindConfigItemException;
import engine.exception.JavaBeanParseException;
import engine.util.JavaBeanUtil;

public final class RuleConfig {

	private String FILE_NAME = "";

	private SAXReader reader = new SAXReader();

	private Document document = null;

	private ConcurrentHashMap<String, ConfigItemBean> ruleConfigMap = new ConcurrentHashMap<String, ConfigItemBean>();

	private Map<String, String> ruleIdentityMap = new HashMap<String, String>();

	private Element rootElm;

	private int retryRuleCount;

	private void init()
	{
		try {
			document = reader.read(new File(FILE_NAME));
			rootElm = document.getRootElement();
			setDefaultDateFormat();
			initialIdentity();
			initialRetryRuleCount();
		} catch (Exception e) {
			Logger.get().error(
					String.format("Cannot read %s : %s", FILE_NAME,
							e.getMessage()));
			System.exit(0);
		}
	}

	public RuleConfig(InstalmentChannel channel){
		if(channel == InstalmentChannel.App)
			FILE_NAME = "rulesforapp.xml";
		else if(channel == InstalmentChannel.PayDayLoanApp)
			FILE_NAME = "rulesforpdl.xml";
		else if (channel == InstalmentChannel.CashLoan)
		    FILE_NAME = "rulesforcashloan.xml";
		else
			FILE_NAME = "rules.xml";
		init();
	}

	public String getNameByIdentity(String identity) {
		return ruleIdentityMap.get(identity);
	}

	@SuppressWarnings("unchecked")
	private void initialIdentity() {
		Iterator<Element> elmIter = rootElm.elementIterator();
		while (elmIter.hasNext()) {
			Element item = elmIter.next();
			String name = item.getName();
			String identity = item.attributeValue("identity");
			if (identity != null)
				ruleIdentityMap.put(identity, name);
		}
	}

	private void initialRetryRuleCount() {
		String retryCount = rootElm.elementText("RetryRuleCount");
		setRetryRuleCount(Integer.parseInt(retryCount));
	}

	private void setDefaultDateFormat() {
		String dateFormat = rootElm.elementText("DateFormat");
		setSystemDateFormat(dateFormat);
	}

	public void setSystemDateFormat(String dateFormat) {
		System.setProperty("drools.dateformat", dateFormat);
	}

	public ConfigItemBean getRuleConfigByName(String jobName,
			Class<? extends ConfigItemBean> beanType)
			throws CannotFindConfigItemException {
		if (!ruleConfigMap.containsKey(jobName)) {
			Element memberElm = rootElm.element(jobName);
			if (memberElm == null) {
				throw new CannotFindConfigItemException(jobName, null);
			}
			ConfigItemBean config = _getConfigItemField(memberElm, beanType);
			if (config != null)
				ruleConfigMap.putIfAbsent(jobName, config);
		}
		return ruleConfigMap.get(jobName);
	}

	private ConfigItemBean _getConfigItemField(Element item,
			Class<? extends ConfigItemBean> itemType) {
		ConfigItemBean config;
		try {
			config = itemType.newInstance();
			Field[] fields = itemType.getDeclaredFields();
			String value = null;
			for (Field field : fields) {
				value = item.elementText(field.getName());
				JavaBeanUtil.fill(config, field, value);
			}
		} catch (JavaBeanParseException e) {
			Logger.get().error(
					String.format("Parse XML node into %s error ",
							itemType.getName()), e);
			config = null;
		} catch (Exception e) {
			Logger.get().error(
					String.format("Parse XML node into %s error ",
							itemType.getName()), e);
			config = null;
		}
		return config;
	}

	public int getRetryRuleCount() {
		return retryRuleCount;
	}

	public void setRetryRuleCount(int retryRuleCount) {
		this.retryRuleCount = retryRuleCount;
	}
}
