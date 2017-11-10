package jma.thirdpartyservices;

import java.rmi.RemoteException;

import org.apache.axiom.om.util.Base64;
import org.apache.commons.logging.Log;

import catfish.base.Logger;
import catfish.base.business.common.CheckNameIDCardResult;
import jma.Configuration;
import jma.RetryRequiredException;
import jma.thirdpartyservices.IdentifierServiceStub.CheckRequest;
import jma.thirdpartyservices.IdentifierServiceStub.CheckResponse;
import jma.thirdpartyservices.IdentifierServiceStub.Credential;
import jma.thirdpartyservices.IdentifierServiceStub.SimpleCheck;

public class IdentifierChecker extends IdCardPoliceChecker {

  @Override
  protected Result doCheck(String name, String number)
      throws RetryRequiredException {
	Logger.get().info(String.format("IdCardPoliceChecker -- (name:%s,number:%s)", name, number));
    Credential credential = new Credential();
    credential.setUserName(Configuration.getIdentifierUsername());
    credential.setPassword(Configuration.getIdentifierPassword());

    CheckRequest request = new CheckRequest();
    request.setName(name);
    request.setIDNumber(number);

    SimpleCheck check = new SimpleCheck();
    check.setCred(credential);
    check.setRequest(request);

    CheckResponse response = null;
    try {
      response = new IdentifierServiceStub().simpleCheck(check).getSimpleCheckResult();
    } catch (RemoteException e) {
      Logger.get().warn(String.format("can't get remote response ! name=%s,number=%s",name,number),e);
      throw new RetryRequiredException();
    }
    
    if (response.getResponseText().equals("成功") == false) {
    	Logger.get().warn(String.format("error responseText :%s,name=%s,number=%s", response.getResponseText(),name,number));
    	return new Result(name, number, CheckNameIDCardResult.ServiceNotAvailable.getValue(), Base64.decode(""));
    }
    
    String msg = response.getIdentifier().getResult();
    String photoContent = msg.equals("一致") ? response.getIdentifier().getPhoto() : "";
    return new Result(name, number, msg, Base64.decode(photoContent));
  }

@Override
protected Result doCheck(String name, String number, String appId) throws RetryRequiredException {
	// TODO Auto-generated method stub
	return doCheck(name,number);
}

}
