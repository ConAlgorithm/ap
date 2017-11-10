package jma.handlers.preprocess.cashloan;

import catfish.cowfish.application.model.ApplicationModel;
import grasscarp.user.model.User;
import jma.handlers.preprocess.IPreprocessor;
import jma.handlers.preprocess.model.CheckUserCreditOn3rdPartyByDspPreModel;
import jma.resource.CLApplicationResourceFactory;
import jma.resource.UserResourceFactory;

/**
 * 
 * 〈前海征信DSP数据源请求参数处理器〉
 *
 * @author wuwj
 * @version CheckUserCreditOn3rdPartyByDspPreprocessor.java, V1.0 2017年5月8日 下午12:01:21
 */
public class CheckUserCreditOn3rdPartyByDspPreprocessor implements IPreprocessor<CheckUserCreditOn3rdPartyByDspPreModel>{

    @Override
    public CheckUserCreditOn3rdPartyByDspPreModel getPreModel(String appId) {
        ApplicationModel applicationModel = CLApplicationResourceFactory.getApplication(appId); // 查询申请信息
        User user = UserResourceFactory.getUser(applicationModel.getUserId()); // 查询用户信息
        return new CheckUserCreditOn3rdPartyByDspPreModel(user.getIdName(), user.getIdNumber());
//        return new CheckUserCreditOn3rdPartyByDspPreModel("米么联调", "440102198301114447");
    }
    
}
