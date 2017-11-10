package jma.thirdpartyservices;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import jma.Configuration;
import jma.RetryRequiredException;
import jma.thirdpartyservices.zcxy.QueryThread;
import jma.thirdpartyservices.zcxy.QueryValidatorServicesImplServiceStub;
import jma.thirdpartyservices.zcxy.QueryValidatorServicesImplServiceStub.QuerySingle;
import jma.thirdpartyservices.zcxy.QueryValidatorServicesImplServiceStub.QuerySingleE;
import jma.thirdpartyservices.zcxy.QueryValidatorServicesImplServiceStub.QuerySingleResponseE;
import jma.thirdpartyservices.zcxy.ZCXYResult;
import jma.thirdpartyservices.zcxy.ZCXYResult.PoliceCheckInfo;

import org.apache.axiom.om.util.Base64;
import org.apache.commons.codec.binary.StringUtils;

import catfish.base.Logger;

public class ZCXYIdCardChecker extends IdCardPoliceChecker {
  /**
   * constant param for zcxy web service
   */
  public static final String DATA_SOURCE = "1A020202";

  @Override
  protected IdCardPoliceChecker.Result doCheck(String name, String number)
      throws RetryRequiredException {
    try {
      String key = Configuration.getZcxyEncryptKey();
      String param = name + "," + number;

      // call ZCXY WebService
      QueryValidatorServicesImplServiceStub stub =
          new QueryValidatorServicesImplServiceStub(Configuration.getZcxyUrl());
      stub._getServiceClient()
          .getOptions()
          .setProperty(org.apache.axis2.Constants.Configuration.DISABLE_SOAP_ACTION, true);
      QuerySingleE reqe = new QuerySingleE();
      QuerySingle req = new QuerySingle();
      reqe.setQuerySingle(req);
      // username
      req.setArg0(Base64.encode(QueryThread.encode(key, Configuration.getZcxyUsername().getBytes())));
      // password
      req.setArg1(Base64.encode(QueryThread.encode(key, Configuration.getZcxyPassword().getBytes())));
      // datasource
      req.setArg2(Base64.encode(QueryThread.encode(key, DATA_SOURCE.getBytes())));
      // param
      req.setArg3(Base64.encode(QueryThread.encode(key, param.getBytes("GB2312"))));
      // do the call
      QuerySingleResponseE resp = stub.querySingle(reqe);
      // result is encrypted and encoded using base64
      String encodedResult = resp.getQuerySingleResponse().get_return();
      String decoded = StringUtils.newString(QueryThread.decode(key, Base64.decode(encodedResult)), "GB2312");
      // decoded is an XML string
      ZCXYResult result = (ZCXYResult)JAXBContext.newInstance(ZCXYResult.class)
                                                 .createUnmarshaller()
                                                 .unmarshal(new StringReader(decoded));
      Logger.get().info(String.format(
          "ZCXYIdCardChecker.doCheck(%s, %s), Result(%d, %s)",
          name,
          number,
          result.getMessage().getStatus(),
          result.getMessage().getValue()));
      if (result.getMessage().getStatus() != 0) {
        throw new RetryRequiredException();
      }
      PoliceCheckInfo info = result.getPoliceCheckInfos().getPoliceCheckInfo().get(0);
      if (info.getMessage().getStatus() != 0) {
        throw new RetryRequiredException();
      }
      byte[] photoContent;
      if (info.getCheckPhoto() != null) {
        photoContent = Base64.decode(info.getCheckPhoto());
      } else {
        photoContent = EMPTY_BYTE_ARRAY;
      }
      return new Result(name, number, info.getCompResult(), photoContent);
    } catch (NullPointerException | UnsupportedEncodingException | RemoteException | JAXBException re) {
    	Logger.get().warn(String.format("Check name and number exception occurred ! name=%s,number=%s",name,number));
      throw new RetryRequiredException();
    }
  }

  public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

@Override
protected Result doCheck(String name, String number, String appId) throws RetryRequiredException {
	return doCheck(name,number);
}
}
