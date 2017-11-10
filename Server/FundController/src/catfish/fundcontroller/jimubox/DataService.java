package catfish.fundcontroller.jimubox;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.apache.http.util.EntityUtils;

import catfish.base.business.common.CatfishEnum;

import com.google.gson.Gson;

import catfish.base.DescriptionParser;
import catfish.base.Logger;
import catfish.base.business.common.*;
import catfish.base.business.util.EnumUtils;

class DataService {
    public ApplicationObject getApplication(String appId){
        ApplicationModelDao amd = new ApplicationModelDao(appId);
        ApplicationObject appObj = amd.getSingle();
        return appObj;
    }
    
    public ApplicationModel convert(ApplicationObject appObj){
        if(appObj == null){
            return null;
        }
        ApplicationModel appModel=new ApplicationModel();
        Class<? extends ApplicationObject> objClass=appObj.getClass();
		Field[] objectFields=objClass.getDeclaredFields();
		
		for(Field field:objectFields){
			try{
				Field targetField=appModel.getClass().getDeclaredField(field.getName());
				if(targetField!=null){
					targetField.set(appModel, field.get(appObj));
				}
					
			}
			catch(NoSuchFieldException e){
				//Logger.get().debug(e);;
				continue;
			} catch (IllegalArgumentException e) {
				Logger.get().error(e);
			} catch (IllegalAccessException e) {
				Logger.get().error(e);
			}
		}

		appModel.ProjectNo=appObj.ProjectNo.replace("-", "");
		//ApplicationModelDao amd = new ApplicationModelDao(appObj.ProjectNo);
		appModel.Sex=getSexByIdNumber(appObj.IdentityNumber);
		appModel.CompanyNature="其他";		
		appModel.HasBadRecord="无";
		appModel.ProductType="3C产品";
		appModel.UserName=appObj.ChineseName;
		appModel.LenderAmount=appObj.FinancingAmount;
		//appModel.RepaymentEndDate=appObj.RepaymentStartDate;
		MarriageStatus marriageStatus=EnumUtils.parse(MarriageStatus.class,Integer.parseInt(appObj.MaritalStatus));	
		appModel.MaritalStatus=DescriptionParser.getDescription(marriageStatus);
		RelationshipType relationshiType=EnumUtils.parse(RelationshipType.class,Integer.parseInt(appObj.Relationship));
		appModel.Relationship=DescriptionParser.getDescription(relationshiType);
		appModel.HJXD_LastMonthAmount=appObj.HJXD_RepaymentByMonth;
		appModel.ApplyCity=appObj.MerchantCity;
		appModel.DownPaymentAmount=appObj.SalePrice.subtract(appObj.FinancingAmount);
		appModel.AltName="侯翔宇";
		appModel.AltID="310104198706266813";
		appModel.AltRelation="代收款人";
		//Logger.get().info(appModel.MaritalStatus);
        
        return appModel;
    }
    
    public String getFundTagByAppId(String AppId){
    	ApplicationModelDao amd = new ApplicationModelDao(AppId);
        return amd.getFundTagByAppId();
    }
    
    public void saveJMBoxResponse(String result,String AppId,int RetryTime){
    	Gson gson = new Gson();  
		try {
			JMBoxResponseModel response=gson.fromJson(result, JMBoxResponseModel.class);
			response.AppId=AppId;
			response.RetryTime=RetryTime;
			if(response!=null){
				ApplicationModelDao amd = new ApplicationModelDao(AppId);
		        int appObj = amd.InsertResponseRecord(response);
			}
		} catch (Exception e) {			
			Logger.get().error("结果保存错误,result:"+result,e);
		}
    }
    
    private String getSexByIdNumber(String IdNumber){
    	int key=Integer.parseInt(IdNumber.substring(IdNumber.length()-2,IdNumber.length()-1))%2;  
    	if(key==0) return "女";
		return "男";
    	
    }
}
