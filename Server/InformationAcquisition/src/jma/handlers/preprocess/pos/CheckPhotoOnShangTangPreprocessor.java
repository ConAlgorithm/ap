package jma.handlers.preprocess.pos;

import catfish.base.Logger;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.framework.storage.IStorageService;
import jma.ServiceFactory;
import jma.handlers.preprocess.IPreprocessor;
import jma.handlers.preprocess.model.CheckPhotoOnShangTangPreModel;
import jma.util.GrasscarpClient;

public class CheckPhotoOnShangTangPreprocessor implements IPreprocessor<CheckPhotoOnShangTangPreModel>{

	@Override
	public CheckPhotoOnShangTangPreModel getPreModel(String appId) {
		IStorageService storageService = ServiceFactory.getServiceProvider().getService(IStorageService.class);
		CheckPhotoOnShangTangPreModel model = new CheckPhotoOnShangTangPreModel();
		EndUserExtensionObject user = new EndUserExtentionDao(appId).getSingle();
		model.setIdCardNumber(user.IdNumber);
		model.setIdCardName(user.IdName);
	    String idPhoto = GrasscarpClient.getIdPhotoKey(appId);
        String headPhoto = GrasscarpClient.getUserHeadPhotoKey(appId);
        if(idPhoto == null || headPhoto == null){
            Logger.get().error(String.format("photo is null.idPhoto=%s, headPhoto=%s", idPhoto, headPhoto));
            return null;
        }
        model.setIdcardPhotoUrl(storageService.getUrl(idPhoto, 5));
        model.setHeadPhotoUrl(storageService.getUrl(headPhoto, 5));
        return model;
	}
}
