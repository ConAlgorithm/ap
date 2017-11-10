package engine.rule.test.creator.app;

import java.util.Map;

import catfish.base.business.common.jxl.RequestStatus;
import engine.rule.model.BaseForm;
import engine.rule.model.in.app.ThirdpartyDataInForm;
import engine.rule.test.creator.TestModelCreator;

public class ThirdpartyDataModelCreator extends TestModelCreator{

	public ThirdpartyDataModelCreator(Map<String, String> columnNameValueMappings){
		super(ThirdpartyDataInForm.class, "in_Thirdparty", columnNameValueMappings);
	}

	@Override
	protected boolean setSpecificFormValue(BaseForm form) {
		boolean result = false;
		
        ThirdpartyDataInForm thirdPartyForm = (ThirdpartyDataInForm)form;
	    
		if(this.columnNameValueMappings.containsKey("isJXLReportExist"))
		{
			if(new Boolean(columnNameValueMappings.get("isJXLReportExist"))){
				thirdPartyForm.setJXLReportStatus(RequestStatus.Success.getValue());
			    thirdPartyForm.setJxlReportDataSuccess(true);
			}
			
			result = true;
		}		    
		return result;
	}

}
