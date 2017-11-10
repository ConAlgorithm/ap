package jma.thirdpartyservices;

import org.apache.axiom.om.util.Base64;

import catfish.base.business.common.CheckNameIDCardResult;
import jma.Configuration;
import jma.RetryRequiredException;

public abstract class IdCardPoliceChecker {

  /**
   * create and initialize chain of checkers configured in *.properties file
   * @return head checker of the chain; null if nothing configured
   * @throws ClassNotFoundException
   * @throws IllegalAccessException
   * @throws InstantiationException
   */
  public static IdCardPoliceChecker createCheckerChain()
      throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    String[] checkers = Configuration.getIdCardCheckers().split(",");
    IdCardPoliceChecker checkerHeader = null;
    for (int i = checkers.length - 1; i >= 0; --i) {
      Class<?> clazz = Class.forName(checkers[i]);
      IdCardPoliceChecker checker = (IdCardPoliceChecker)clazz.newInstance();
      checker.initialize(checkerHeader);
      checkerHeader = checker;
    }
    return checkerHeader;
  }

  public static class Result {
    protected String name;
    protected String number;
    protected String msg;
    protected byte[] photo;

    public Result() {}
    public Result(String name, String number, String msg, byte[] photo) {
      this.name = name;
      this.number = number;
      this.msg = msg;
      this.photo = photo;
    }
    public String getName() {
      return name;
    }
    public void setName(String name) {
      this.name = name;
    }
    public String getNumber() {
      return number;
    }
    public void setNumber(String number) {
      this.number = number;
    }
    public String getMsg() {
      return msg;
    }
    public void setMsg(String msg) {
      this.msg = msg;
    }
    public byte[] getPhoto() {
      return photo;
    }
    public void setPhoto(byte[] photo) {
      this.photo = photo;
    }
    public boolean numberExists()
    {
      return !this.msg.contains("库中无此号");
    }
    
    public String derivationVariableValue(){
    	if(this.msg.equalsIgnoreCase(CheckNameIDCardResult.Match.getValue())){
    		return CheckNameIDCardResult.Match.getValue();
    	}
    	else if(this.msg.contains("库中无此号")){
    		return CheckNameIDCardResult.NotExist.getValue();
    	}
    	else if(this.msg.equalsIgnoreCase(CheckNameIDCardResult.ServiceNotAvailable.getValue())){
    		return CheckNameIDCardResult.ServiceNotAvailable.getValue();
    	}
    	else {
			return CheckNameIDCardResult.NotMatch.getValue();
		}
    }
  }

  protected IdCardPoliceChecker nextChecker;

  public void initialize(IdCardPoliceChecker nextChecker) {
    this.nextChecker = nextChecker;
  }

  /**
   * check info from police db, shall be thread safe
   * @param name name of user
   * @param number id card number of user
   * @return
   * @throws RetryRequiredException current check is temporarily
   *         unavailable, needs to call next checker
   */
  protected abstract Result doCheck(String name, String number, String appId) throws RetryRequiredException;
  protected abstract Result doCheck(String name, String number) throws RetryRequiredException;
  /**
   * check info from police, call next check if current check is unavailable
   * @param name name of user
   * @param number id card number of user
   * @return [msg, photo path]
   */
  public Result check(String name, String number, String appId) throws RetryRequiredException {
    Result result = null;
    boolean exceptionHappens = false;
    try {
      result = doCheck(name, number,appId);
    } catch (RetryRequiredException ex) {
      if(nextChecker == null)
      {
        return new Result(name, number, CheckNameIDCardResult.ServiceNotAvailable.getValue(), Base64.decode(""));
      }
      exceptionHappens = true;
    }

    //If exception happens or the number could not be found out
    if(exceptionHappens || (nextChecker != null && !result.numberExists()))
    {
    	Result nextResult = nextChecker.check(name, number,appId);
    	if(result == null || result.derivationVariableValue().equals(CheckNameIDCardResult.ServiceNotAvailable.getValue())){
    		return nextResult;
    	}
    }

    return result;
  }
  public Result check(String name, String number) throws RetryRequiredException {
      return check(name,number,"00000000-0000-0000-0000-000000000000");
  }

}
