/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package engine.rule.model.creator.cashloan;

import java.util.Map;

import com.google.gson.Gson;

import catfish.base.CollectionUtils;
import catfish.base.Logger;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.cashloan.SHZXDataInForm;

/**
 * 〈上海资信Form构造器〉
 *
 * @author zhujx
 * @version SHZXDataModelCreator.java, V1.0 2017年3月16日 下午4:10:57
 */
public class SHZXDataModelCreator extends AbstractApplicationModelCreator{

    public SHZXDataModelCreator(String appId) {
        super(appId);
    }

    @Override
    public Map<String, Object> createModelForm() {
        form = new ModelBuilder<SHZXDataInForm>(new SHZXDataInForm())
                .buidDerivativeVariables(appId).getForm();
        Logger.get().info("LTVSHZXDataModel, data: " + new Gson().toJson(form));
        return CollectionUtils.mapOf("in_SHZX", (Object) form);
    }

  
    @Override
    public String createBusinessNo() {
        return null;
    }

}
