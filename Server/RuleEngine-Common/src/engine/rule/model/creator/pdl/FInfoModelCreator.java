package engine.rule.model.creator.pdl;

import java.util.Map;
import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.business.dao.CNAreaDao;
import catfish.base.business.dao.FApplicationRelationDao;
import catfish.base.business.dao.FactoryDao;
import catfish.base.business.object.CNAreaObject;
import catfish.base.business.object.FApplicationRelationObject;
import catfish.base.business.object.FactoryObject;
import engine.rule.model.ModelBuilder;
import engine.rule.model.creator.AbstractApplicationModelCreator;
import engine.rule.model.in.pdl.FInfoForm;

public class FInfoModelCreator extends AbstractApplicationModelCreator {

    public FInfoModelCreator(String appId) {
        super(appId);
    }

    @Override
    public Map<String, Object> createModelForm() {
        form = new ModelBuilder<FInfoForm>(new FInfoForm())
                .buidDerivativeVariables(appId)
                .getForm();
        
        FInfoForm fForm = (FInfoForm)form;
        
        fForm = _fillForm(fForm);
        
        Map<String, Object> inputParameterMap = CollectionUtils.mapOf("in_FInfo",
                (Object) fForm);
        
        return inputParameterMap;
    }
    
    private FInfoForm _fillForm(FInfoForm form) {
        
        FApplicationRelationObject far = new FApplicationRelationDao(appId).getSingle();
        if (far == null) {
            Logger.get().debug(
                    "Can not get FApplicationRelationObject from AppId :" + appId);
            return form;
        }
        
        form.setF1Id(far.FUserId);
        
        FactoryObject factory = new FactoryDao(far.FactoryId).getSingle();
        if (factory == null) {
            Logger.get().debug(
                    "Can not get FactoryObject from Id :" + far.FactoryId);
            return form;
        }
        
        CNAreaObject area = new CNAreaDao(factory.CNAreaId).getSingle();
        if (area == null) {
            Logger.get().debug(
                    "Can not get CNAreaObject from Id :" + factory.CNAreaId);
            return form;
        }
        
        CNAreaObject city = CNAreaDao.getCNAreaObjByAreaCode(area.parentCode);
        CNAreaObject province = CNAreaDao.getCNAreaObjByAreaCode(city.parentCode);
        
        form.setCity(city.Name);
        form.setProvince(province.Name);
        
        return form;
    }

    @Override
    public String createBusinessNo() {
        // TODO Auto-generated method stub
        return null;
    }

}
