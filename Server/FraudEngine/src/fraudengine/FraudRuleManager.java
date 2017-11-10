package fraudengine;

import java.util.LinkedList;

import catfish.base.Logger;
import catfish.base.business.common.CatfishEnum;
import catfish.base.business.common.FraudRuleResultStatus;
import catfish.base.business.dao.FraudRuleResultDao;
import catfish.base.business.dao.InstallmentApplicationDao;
import catfish.base.business.execution.ExecutionController;
import fraudengine.rules.A003;
import fraudengine.rules.A005;
import fraudengine.rules.A006;
import fraudengine.rules.A007;
import fraudengine.rules.A010;
import fraudengine.rules.A012;
import fraudengine.rules.A013;
import fraudengine.rules.A014;
import fraudengine.rules.A015;
import fraudengine.rules.A016;
import fraudengine.rules.B002;
import fraudengine.rules.B003;
import fraudengine.rules.B004;
import fraudengine.rules.E001;
import fraudengine.rules.E002;
import fraudengine.rules.E003;
import fraudengine.rules.E004;
import fraudengine.rules.FraudRule;

public class FraudRuleManager {
  private LinkedList<FraudRule> fraudRules = new LinkedList<>();

  public FraudRuleManager() {
    fraudRules.add(new A010("A010", "微信头像", 100, "微信无头像"));
    fraudRules.add(new A003("A003", "申请电话相同", 100, "申请人所有电话中有相同电话，不包含单位电话为手机，且联系人为同事、朋友、其他/所有手机前7位相同"));
    fraudRules.add(new A007("A007", "父母电话异地", 60, "父母电话不在户籍所在地和本人手机、单位电话所在地"));

    fraudRules.add(new A005("A005", "同事电话同城", 60, "申请人所有同事电话所对应城市不同"));
    fraudRules.add(new A006("A006", "同事电话同省", 60, "申请人所有同事电话所对应省份不同"));
    fraudRules.add(new A012("A012", "父母在当地", 60, "客户原籍不在申请省 and 客户父母手机来源=申请省市"));
    fraudRules.add(new A013("A013", "工资卡校验", 100, "申请人的工资卡被其他申请人使用过"));
    fraudRules.add(new A014("A014", "联系人一致性校验", 100, "联系人当被其他申请人作为联系人时，姓名完全不同（音同字不同认为相同）"));
    fraudRules.add(new A015("A015", "姓名电话校验", 100, "申请手机曾经被其他申请人作为联系人手机，且当时的联系人姓名和当前申请人不一样（音同字不同认为相同）"));
    fraudRules.add(new A016("A016", "可疑父母动作", 80, "同一身份证之前有申请到一半放弃了，但填了不同的手机或者不同的父母名字，或者父母名字相同，电话填了不同"));

    fraudRules.add(new E001("E001", "S1的QQ冒用", 100, "申请人的QQ等于S1的QQ，但身份证不一致"));
    fraudRules.add(new E002("E002", "S1为联系人", 60, "S1的个人手机或者S3的电话作为了申请人的任何联系方式"));
    fraudRules.add(new E003("E003", "S1的工资卡", 100, "申请人的工资卡等于S1的工资卡，但身份证不一致"));
    fraudRules.add(new E004("E004", "S1自己申请", 100, "S1作为申请人时填写的QQ或者工资卡和预留的不一致"));

    fraudRules.add(new B002("B002", "中介门店操纵", 100, "4天内同一门店，相同单位，相同借款金额，上传文件时间<90s >=2的人数"));
    fraudRules.add(new B003("B003", "中介商圈操纵", 100, "7天内同一商圈，相同借款金额，相同期数，申请时间<700,上传文件时间<90s >=2"));
    fraudRules.add(new B004("B004", "可疑手机集中", 80, "同一门店过去2天中有相同前7位手机号的"));

  }

  public boolean run(String appID) {

	Integer type = InstallmentApplicationDao.getApplicationTypeById(appID);
	
    for (FraudRule fraudRule : fraudRules) {
      try {
    	  //用于通过不同的申请环境对执行的规则进行过滤
    	  try
    	  {
    		  if(! ExecutionController.ifMatchExecution(fraudRule.getId(), type))
        		  continue;
    	  }catch(Exception e)
    	  {
    		  Logger.get().error(String.format("Filter fraud rule fraudRuleId=%s faild !", fraudRule.getId()), e);
    	  }
    	  	  
		  CatfishEnum<Integer> result = fraudRule.run(appID);
	        Logger.get().info("欺诈规则执行: " + fraudRule.getId() + ", AppID: " + appID);

	        // Insert a new record into db
	        FraudRuleResultDao.saveFraudDetailResult(appID, fraudRule.getId(), fraudRule.getName(), 
	        		result.getValue());  
      } catch (Exception e) {
        Logger.get().error("Exception happens: Fraud rule( fraudRuleId=" + fraudRule.getId() + ", " + fraudRule.getName() + ")", e);
      }
    }

    return true;
  }
  
}
