package catfish.sales.objects;

import java.util.Date;

public class UserObject extends BaseDataObject {

	public Boolean IsRegistered ;

    public int WeiXinAccountFollowed ;

    public String UserName ;

    public String UserNameLower ;

    public String Password ;

    public String PasswordSalt ;

    public Date LastLogInDate ;

    public int FailedPasswordAttemptCount ;

    public Date FailedPasswordAttemptTime ;

    public String MobileContactId ;


    //public String IdName ;

    //public String IdNumber ;

    //public Date RealNameFilledOn ;

    public Date MobileValidatedOn ;

    public String WeiXinUserId ;
    
}
