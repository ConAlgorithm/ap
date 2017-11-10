package engine.rule.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBField {

	public String fieldName() default "";

	public String bindAdapter() default "";

	public String variableType() default "";

	// 标记是否为模糊匹配，例如X_AdditionalContact_1模糊匹配为X_AdditionalContact
	// public boolean isFuzzyMatch() default false;
}
