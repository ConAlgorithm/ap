package engine.databaseapi;

import static catfish.base.business.common.AppDerivativeVariableConsts.CheckAdditionalContactPrefix;

import java.util.Map;

import catfish.base.Logger;
import catfish.base.business.util.AppDerivativeVariableManager;
import catfish.base.business.util.AppDerivativeVariables;
import engine.exception.JavaBeanParseException;
import engine.rule.model.BaseForm;
import engine.rule.model.ModelFieldOfDB;
import engine.rule.model.annotation.AnnotationManager;
import engine.util.JavaBeanUtil;

public class DerivativeVariableApi {

	public static final String VariableType = "VariableType";
	public static final String IntValue = "IntValue";
	public static final String StringValue = "StringValue";
	public static final String BoolValue = "BoolValue";
	public static final String DecimalValue = "DecimalValue";
	public static final String DateTimeValue = "DateTimeValue";

	public static <T extends BaseForm> void buildForm(T form, String appId) {
		AppDerivativeVariables variables = AppDerivativeVariableManager
				.getVariables(appId);
		Map<String, ModelFieldOfDB> map = AnnotationManager
				.getModelDBFieldsMap(form.getClass());

		if (variables != null) {
			for (String variable : variables.getKeys()) {
				if (map.containsKey(variable)) {
					if (variables.get(variable) != null) {
						try {
							JavaBeanUtil.fill(form, map.get(variable),
									variables);
						} catch (JavaBeanParseException e) {
							Logger.get().warn(
									String.format(
											"Parse VariableType %s error in DerivativeVariable",
											variable), e);
						}
					}
				} else if (variable.contains(CheckAdditionalContactPrefix)
						&& map.containsKey(CheckAdditionalContactPrefix)) {
					int value = variables.getAsInt(variable);
					int varLen = variable.length();
					int index = Integer.parseInt(variable.substring(varLen - 1,
							varLen));
					try {
						JavaBeanUtil
								.tryFillAdditionalContactVariable(form, index,
										variable.substring(0, varLen - 3),
										value);
					} catch (JavaBeanParseException e) {
						Logger.get().error(
								String.format("Parse VariableType %s error in DerivativeVariable",
										variable), e);
					}
				}
			}
		}

	}
}
