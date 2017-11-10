package catfish.sales.models;

import java.util.Date;

public class UserModel {

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

    public Date MobileValidatedOn ;

    public String WeiXinUserId ;
    
}
