package jma;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import catfish.base.business.util.AppDerivativeVariable;

public class AppDerivativeVariablesBuilder {
    private final String appId;
    private List<AppDerivativeVariable> variables = new ArrayList<>();

    public AppDerivativeVariablesBuilder(String appId) {
        this.appId = appId;
    }

    public AppDerivativeVariablesBuilder add(String variableType, String stringValue) {
        variables.add(new AppDerivativeVariable(appId, variableType, stringValue));
        return this;
    }

    /**
    * <p>〈判断添加的字段value不为空〉</p>
    * 
    * @param variableType
    * @param stringValue
    * @return
    */
    public AppDerivativeVariablesBuilder isNotNullAdd(String variableType, String stringValue) {
        if (stringValue != null && !"".equals(stringValue)) {
            variables.add(new AppDerivativeVariable(appId, variableType, stringValue));
        }
        return this;
    }

    public AppDerivativeVariablesBuilder add(String variableType, int intValue) {
        variables.add(new AppDerivativeVariable(appId, variableType, intValue));
        return this;
    }

    /**
    * <p>〈判断空值不添加〉</p>
    * 
    * @param variableType
    * @param intValue
    * @return
    */
    public AppDerivativeVariablesBuilder isNotNullAdd(String variableType, Integer intValue) {
        if (intValue != null) {
            variables.add(new AppDerivativeVariable(appId, variableType, intValue));
        }
        return this;
    }

    public AppDerivativeVariablesBuilder add(String variableType, boolean boolValue) {
        variables.add(new AppDerivativeVariable(appId, variableType, boolValue));
        return this;
    }

    /**
    * <p>〈判断空值不添加〉</p>
    * 
    * @param variableType
    * @param boolValue
    * @return
    */
    public AppDerivativeVariablesBuilder isNotNullAdd(String variableType, Boolean boolValue) {
        if (boolValue != null) {
            variables.add(new AppDerivativeVariable(appId, variableType, boolValue));
        }
        return this;
    }

    public AppDerivativeVariablesBuilder add(String variableType, Date dateTimeValue) {
        variables.add(new AppDerivativeVariable(appId, variableType, dateTimeValue));
        return this;
    }

    public AppDerivativeVariablesBuilder add(String variableType, BigDecimal decimalValue) {
        variables.add(new AppDerivativeVariable(appId, variableType, decimalValue));
        return this;
    }

    public List<AppDerivativeVariable> build() {
        return variables;
    }
    
    public AppDerivativeVariablesBuilder isNotNullAdd(String variableType, Float value) {
        if (value != null) {
            variables.add(new AppDerivativeVariable(appId, variableType, value));
        }
        return this;
    }
    
    public AppDerivativeVariablesBuilder isNotNullAdd(String variableType, BigDecimal value) {
        if (value != null) {
            variables.add(new AppDerivativeVariable(appId, variableType, value));
        }
        return this;
    }
    
    public AppDerivativeVariablesBuilder isNotNullAdd(String variableType, Double value) {
        if (value != null) {
            variables.add(new AppDerivativeVariable(appId, variableType, value));
        }
        return this;
    }
}
