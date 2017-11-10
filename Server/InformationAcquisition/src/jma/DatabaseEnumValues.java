package jma;

public class DatabaseEnumValues {

  public static class ContactPersonType {
    public static final int FIRST_CONTACT_PERSON = catfish.base.business.common.ContactPersonType.FirstContactPerson.getValue();
    public static final int SECOND_CONTACT_PERSON = catfish.base.business.common.ContactPersonType.SecondContactPerson.getValue();
    public static final int THIRD_CONTACT_PERSON = catfish.base.business.common.ContactPersonType.ThirdContactPerson.getValue();
    public static final int ADDITIONAL_CONTACT_PERSON_BASE = catfish.base.business.common.ContactPersonType.AdditionalContactPerson_1.getValue();
    public static final int ADDITIONAL_CONTACT_PERSON_UP = catfish.base.business.common.ContactPersonType.AdditionalContactPerson_8.getValue();
  }
  public static class ContactType {
    public static final int QQ = 1;
    public static final int MOBILE = 2;
    public static final int COMPANY_TEL = 4;
  }

  public static class ApplicationStatus {
    public static final int SUBMITTED = 10;
    public static final int APPROVED = 30;
    public static final int REJECTED = 40;
    public static final int COMPLETED = 100;
    public static final int DELAYED = 200;
  }
  public static class CNAreaType {
    public static final int PROVINCE = 0;
    public static final int CITY = 10;
    public static final int DISTRICT = 20;
  }
  public static class BankReferenceResult{
    public static final int None=0;
    public static final int NoRecords=10;
    public static final int QueryException=15;
    public static final int NotEnoughInfo=20;
    public static final int PasswordSet=30;
    public static final int RegisteredOrRegistering=31;
    public static final int NotSupported=35;
    public static final int HasCreditRecordAndFitDescription=40;
    public static final int HasCreditRecordAndNotFit=50;
    public static final int HasCreditRecord=51;
  }

  public static class ContactRelationShip {
    public static final int PARENT = 0;
    public static final int SPOUSE = 1;
    public static final int RELATIVE = 2;
    public static final int COLLEAGUE = 3;
    public static final int FRIEND = 4;
    public static final int BROTHERS_AND_SISTERS = 5;
    public static final int FATHER = 6;
    public static final int MOTHER = 7;
    public static final int HUSBAND = 8;
    public static final int WIFE = 9;
    public static final int SON = 10;
    public static final int DAUGHTER = 11;
    public static final int BROTHER = 12;
    public static final int SISTER = 13;

    public static final int OTHER = 100;   // only for compatible check jobs
  }

  public static class BlacklistType {
    public static final int UNKNOWN = 0;
    public static final int PHONE = 10;
    public static final int QQ = 20;
    public static final int WEIXIN = 30;
    public static final int ID_NUMBER = 40;
  }
}
