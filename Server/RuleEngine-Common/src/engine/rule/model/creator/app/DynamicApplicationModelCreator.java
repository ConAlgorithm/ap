package engine.rule.model.creator.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.autosqlquery.AutoSqlQueryInstance;
import catfish.base.business.autosqlquery.AutoSqlQueryManager;
import catfish.base.business.common.SqlExecutionStage;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.app.DynamicApplicationInForm;


public class DynamicApplicationModelCreator extends
		AbstractApplicationModelCreator {

	public DynamicApplicationModelCreator(String appId) {
		super(appId);
	}

	@Override
	public Map<String, Object> createModelForm() {
		DynamicApplicationInForm form = new DynamicApplicationInForm();

		for (AutoSqlQueryInstance query : AutoSqlQueryManager.execute(
				SqlExecutionStage.AntiFraud, appId)) {
			if (query.isSuccessful()) {
				form.setValue(query.getVariableName().substring(3), // remove
																	// prefix
																	// "XA_"
						transform(query.getResult()));
			} else {
				Logger.get().error(
						String.format("SQL execution error: %s - %s",
								query.getVariableName(), query.getSql()),
						query.getException());
			}
		}

		return CollectionUtils.<String, Object> mapOf("in_DynamicApp", // in_DynamicApplication,
																		// there's
																		// length
																		// limit
																		// by
																		// HuaTeng
				form);
	}

	private static Object transform(Object value) {
		if (!(value instanceof Date)) {
			return value;
		}

		// HuaTeng rule engine cannot handle the 'time' part of java.util.Date.
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(formatter.format((Date) value));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String createBusinessNo() {
		return null;
	}
}
