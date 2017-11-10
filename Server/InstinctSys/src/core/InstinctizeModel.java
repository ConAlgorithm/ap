package core;

import catfish.base.Logger;
import catfish.base.StartupConfig;
import instinct.model.*;
import omni.database.DataContainer;
import util.StringBuilderUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//import omni.database.catfish.dao.CatfishDaoImp;

/**
 * This class is the bridge converting Omni data(from Catfish) to Instinct data(on Instinct server).
 * 
 * @author guoqing
 * @version 1.1.0
 * @since 1.0.0
 * 
 */
public final class InstinctizeModel
{

	private InstinctizeModel()
	{

	}

	private static String delimiter = StartupConfig.get("instinct.delimiter") == null ? "|" : StartupConfig.get("instinct.delimiter");

	private static String delimiter2 = StartupConfig.get("instinct.delimiter") == null ? "|" : StartupConfig.get("instinct.delimiter");

//	private static CatfishDaoImp dao = new CatfishDaoImp();

	public static String application(Application app)
	{
	    // v20160802 modify start 用StringBuilder替换字符串+拼接
//		String ia = app.organisation + delimiter
//				+ app.country_Code + delimiter
//				+ app.group_Member_Code + delimiter
//				+ app.appId + delimiter
//				+ app.expiry_Date + delimiter
//				+ app.application_Date + delimiter
//				+ app.application_Type + delimiter
//				+ app.amount_Limit + delimiter
//				+ app.decision + delimiter
//				+ app.decision_Reason + delimiter
//				+ app.decision_Date + delimiter
//				+ app.user_Field1 + delimiter
//				+ app.user_Field2 + delimiter
//				+ app.user_Field3 + delimiter
//				+ app.user_Field4 + delimiter
//				+ app.user_Field5 + delimiter
//				+ app.user_Field6 + delimiter
//				+ app.user_Field7 + delimiter
//				+ app.user_Field8 + delimiter
//				+ app.user_Field9 + delimiter
//				+ app.user_Field10 + delimiter
//				+ app.user_Field11 + delimiter
//				+ app.user_Field12 + delimiter
//				+ app.user_Field13 + delimiter
//				+ app.user_Field14;
//		System.out.println(ia);
//	    return ia;
	    // v20160802 modify end

		StringBuilderUtil result = new StringBuilderUtil();
		result.appendFirst(app.organisation)
		.append(app.country_Code)
        .append(app.group_Member_Code)
        .append(app.appId)
        .append(app.expiry_Date)
        .append(app.application_Date)
        .append(app.application_Type)
        .append(app.amount_Limit)
        .append(app.decision)
        .append(app.decision_Reason)
        .append(app.decision_Date)
        .append(app.user_Field1)
        .append(app.user_Field2)
        .append(app.user_Field3)
        .append(app.user_Field4)
        .append(app.user_Field5)
        .append(app.user_Field6)
        .append(app.user_Field7)
        .append(app.user_Field8)
        .append(app.user_Field9)
        .append(app.user_Field10)
        .append(app.user_Field11)
        .append(app.user_Field12)
        .append(app.user_Field13)
        .append(app.user_Field14)
		// v20160802 add start
		.append(app.user_Field15)
        .append(app.user_Field16)
        .append(app.user_Field17)
        .append(app.user_Field18)
		// v20160802 add end
		// v20161230 add start
		.append(app.user_Field19)
		.append(app.user_Field20)
		.append(app.user_Field21)
		.append(app.user_Field22)
		.append(app.user_Field23);

		// v20161230 add end
		return result.toString();
	}

	public static String applicant(Applicant app)
	{
	    // v20160802 modify start 用StringBuilder替换字符串+拼接
//		String ia = app.category + delimiter
//				+ app.id_Number1 + delimiter
//				+ app.id_Number2 + delimiter
//				+ app.id_Number3 + delimiter
//				+ app.surname + delimiter
//				+ app.first_Name + delimiter
//				+ app.sex + delimiter
//				+ app.home_Address1 + delimiter
//				+ app.home_Address2 + delimiter
//				+ app.home_Address3 + delimiter
//				+ app.home_Address4 + delimiter
//				+ app.mobile_Phone_Number + delimiter
//				+ app.company_Name + delimiter
//				+ app.company_Address1 + delimiter
//				+ app.company_Phone_Number + delimiter
//				+ app.user_Field1 + delimiter
//				+ app.user_Field2 + delimiter
//				+ app.user_Field3 + delimiter
//				+ app.user_Field4 + delimiter
//				+ app.user_Field5 + delimiter
//				+ app.user_Field6 + delimiter
//				+ app.user_Field8 + delimiter
//				+ app.user_Field9 + delimiter
//				+ app.user_Field10 + delimiter
//				+ app.user_Field13 + delimiter
//				+ app.user_Field14 + delimiter
//				+ app.user_Field15;
////		System.out.println(ia);
//		return ia;
	    // v20160802 modify end

	    StringBuilderUtil result = new StringBuilderUtil();
        result.appendFirst(app.category)
        .append(app.id_Number1)
        .append(app.id_Number2)
        .append(app.id_Number3)
        .append(app.surname)
        .append(app.first_Name)
        .append(app.sex)
        .append(app.home_Address1)
        .append(app.home_Address2)
        .append(app.home_Address3)
        .append(app.home_Address4)
        .append(app.mobile_Phone_Number)
        .append(app.company_Name)
        .append(app.company_Address1)
        .append(app.company_Phone_Number)
        .append(app.user_Field1)
        .append(app.user_Field2)
        .append(app.user_Field3)
        .append(app.user_Field4)
        .append(app.user_Field5)
        .append(app.user_Field6)
        .append(app.user_Field8)
        .append(app.user_Field9)
        .append(app.user_Field10)
        .append(app.user_Field13)
        .append(app.user_Field14)
        .append(app.user_Field15)
        // v20160802 add start
        .append(app.user_Field17)
        .append(app.user_Field18)
        .append(app.user_Field19)
        .append(app.user_Field20);
        // v20160802 add end
        return result.toString();
	}

	public static String cba(CBA cba)
	{
	    // v20160809 modify start 用StringBuilder替换字符串+拼接
//		String iia = ia.category + delimiter
//				+ ia.id_Number1 + delimiter
//				+ ia.id_Number2 + delimiter
//				+ ia.surname + delimiter
//				+ ia.first_Name + delimiter
//				+ ia.middle_Name + delimiter
//				+ ia.home_Address1 + delimiter
//				+ ia.home_Address2 + delimiter
//				+ ia.home_Address3 + delimiter
//				+ ia.user_Field4 + delimiter
//				+ ia.user_Field5 + delimiter
//				+ ia.user_Field6 + delimiter
//				+ ia.user_Field7 + delimiter
//				+ ia.user_Field8 + delimiter
//				+ ia.user_Field9 + delimiter
//				+ ia.user_Field10 + delimiter
//				+ ia.user_Field11 + delimiter
//				+ ia.user_Field12 + delimiter
//				+ ia.user_Field13 + delimiter
//				+ ia.user_Field14 + delimiter
//				+ ia.user_Field29;
////		System.out.println(iia);
//		return iia;
		// v20160802 add end

	    StringBuilderUtil result = new StringBuilderUtil();
        result.appendFirst(cba.category)
        .append(cba.id_Number1)
        .append(cba.id_Number2)
        .append(cba.surname)
        .append(cba.first_Name)
        .append(cba.middle_Name)
        // v20160809 add start
        .append(cba.short_Name)
        // v20160809 add end
        .append(cba.home_Address1)
        .append(cba.home_Address2)
        .append(cba.home_Address3)
        .append(cba.mobile_Phone_Number)
        // v20160809 add start
        .append(cba.other_Phone_Number)
        .append(cba.user_Field1)
        // v20160809 add end
        // v20160830 add start
        .append(cba.user_Field2)
        .append(cba.user_Field3)
        .append(cba.user_Field4)
        // v20160830 add end
        .append(cba.user_Field5)
        .append(cba.user_Field6)
        .append(cba.user_Field7)
        .append(cba.user_Field8)
        .append(cba.user_Field9)
        .append(cba.user_Field10)
        .append(cba.user_Field11)
        .append(cba.user_Field12)
        .append(cba.user_Field13)
        .append(cba.user_Field14)
        // v20160830 add start
        .append(cba.user_Field15)
        .append(cba.user_Field16)
        .append(cba.user_Field17)
        .append(cba.user_Field18)
        .append(cba.user_Field19)
        .append(cba.user_Field20)
        // v20160830 add end
        .append(cba.user_Field29)
        // v20160809 add start
        .append(cba.user_Field30)
        .append(cba.user_Field31)
        .append(cba.user_Field32);
        // v20160809 add end
        return result.toString();
	}

	public static String reference(SubReference subref)
	{
	    // v20160809 modify start 用StringBuilder替换字符串+拼接
//		String ir = subref.category + delimiter
//				+ subref.surname + delimiter
//				+ subref.first_Name + delimiter
//				+ subref.middle_Name + delimiter
//				+ subref.home_Address1 + delimiter
//				+ subref.mobile_Phone_Number + delimiter
//				+ subref.user_Field1 + delimiter
//				+ subref.user_Field2 + delimiter
//				+ subref.user_Field6 + delimiter
//				+ subref.user_Field7 + delimiter
//				+ subref.user_Field8 + delimiter
//				+ subref.user_Field9 + delimiter
//				+ subref.user_Field10;
////		System.out.println(ir);
//		return ir;
	    // v20160809 modify end

	    StringBuilderUtil result = new StringBuilderUtil();
	    result.appendFirst(subref.category)
	    .append(subref.surname)
        .append(subref.first_Name)
        .append(subref.middle_Name)
        .append(subref.home_Address1)
        // v20160809 add start
        .append(subref.home_Address2)
        // v20160809 add end
        .append(subref.mobile_Phone_Number)
        .append(subref.user_Field1)
        .append(subref.user_Field2)
        .append(subref.user_Field6)
        .append(subref.user_Field7)
        .append(subref.user_Field8)
        .append(subref.user_Field9)
        .append(subref.user_Field10);
        return result.toString();
	}

	public static String cbb(CBB cbb)
	{
	    // v20160830 modify start 用StringBuilder替换字符串+拼接
//		String iv = valuer.category + delimiter
//				+ valuer.id_Number1 + delimiter
//				+ valuer.id_Number2 + delimiter
//				+ valuer.id_Number3 + delimiter
//				+ valuer.surname + delimiter
//				+ valuer.first_Name + delimiter
//				+ valuer.middle_Name + delimiter
//				+ valuer.user_Field2 + delimiter
//				+ valuer.user_Field3 + delimiter
//				+ valuer.user_Field4 + delimiter
//				+ valuer.user_Field5 + delimiter
//				+ valuer.user_Field6 + delimiter
//				+ valuer.user_Field7 + delimiter
//				+ valuer.user_Field8 + delimiter
//				+ valuer.user_Field9 + delimiter
//				+ valuer.user_Field10 + delimiter
//				+ valuer.user_Field11 + delimiter
//				+ valuer.user_Field12 + delimiter
//				+ valuer.user_Field13 + delimiter
//				+ valuer.user_Field14 + delimiter
//				+ valuer.user_Field15 + delimiter
//				+ valuer.user_Field16 + delimiter
//				+ valuer.user_Field17 + delimiter
//				+ valuer.user_Field18 + delimiter
//				+ valuer.user_Field29;
////		System.out.println(iv);
//		return iv;
		// v20160830 modify end

	    StringBuilderUtil result = new StringBuilderUtil();
	    result.appendFirst(cbb.category)
        .append(cbb.id_Number1)
        .append(cbb.id_Number2)
        .append(cbb.id_Number3)
        .append(cbb.surname)
        .append(cbb.first_Name)
        .append(cbb.middle_Name)
        // v20160830 add start
        .append(cbb.home_Address1)
        .append(cbb.home_Address2)
        .append(cbb.home_Address3)
        .append(cbb.home_Address4)
        .append(cbb.home_Address5)
        .append(cbb.home_Address6)
        .append(cbb.home_Phone_Number)
        .append(cbb.office_Phone_Number)
        .append(cbb.mobile_Phone_Number)
        .append(cbb.other_Phone_Number)
        // v20160830 add end
        .append(cbb.user_Field2)
        .append(cbb.user_Field3)
        .append(cbb.user_Field4)
        .append(cbb.user_Field5)
        .append(cbb.user_Field6)
        .append(cbb.user_Field7)
        .append(cbb.user_Field8)
        .append(cbb.user_Field9)
        .append(cbb.user_Field10)
        .append(cbb.user_Field11)
        .append(cbb.user_Field12)
        .append(cbb.user_Field13)
        .append(cbb.user_Field14)
        .append(cbb.user_Field15)
        .append(cbb.user_Field16)
        .append(cbb.user_Field17)
        .append(cbb.user_Field18)
        // v20160830 add start
        .append(cbb.user_Field25)
        .append(cbb.user_Field26)
        .append(cbb.user_Field28)
        // v20160830 add end
        .append(cbb.user_Field29)
	    // v20160830 add start
        .append(cbb.user_Field32);
        // v20160830 add end
        return result.toString();
	}

	public static String user(User usr)
	{
		String iu = usr.category + delimiter
				+ usr.id_Number1 + delimiter
				+ usr.id_Number2 + delimiter
				+ usr.id_Number3 + delimiter
				+ usr.surname + delimiter
				+ usr.first_Name + delimiter
				+ usr.middle_Name + delimiter
				+ usr.home_Address1 + delimiter
				+ usr.home_Address2 + delimiter
				+ usr.home_Address3 + delimiter
				+ usr.home_Address4 + delimiter
				+ usr.home_Address5 + delimiter
				+ usr.home_Address6 + delimiter
				+ usr.company_Name + delimiter
				+ usr.company_Address1 + delimiter
				+ usr.company_Address2 + delimiter
				+ usr.company_Address3 + delimiter
				+ usr.company_Address4 + delimiter
				+ usr.company_Address5 + delimiter
				+ usr.Company_Address6 + delimiter
				+ usr.Company_Phone_Number + delimiter
				+ usr.User_Field1 + delimiter
				+ usr.User_Field2 + delimiter
				+ usr.User_Field6 + delimiter
				+ usr.User_Field7 + delimiter
				+ usr.User_Field8 + delimiter
				+ usr.User_Field9 + delimiter
				+ usr.User_Field10;
//		System.out.println(iu);
		return iu;
	}

	public static String user2(User2 usr2)
	{
	    // v20160809 modify start 用StringBuilder替换字符串+拼接
//		String iu = usr.category + delimiter
//				+ usr.user_Field1 + delimiter
//				+ usr.user_Field2 + delimiter
//				+ usr.user_Field3 + delimiter
//				+ usr.user_Field4 + delimiter
//				+ usr.user_Field5 + delimiter
//				+ usr.user_Field6 + delimiter
//				+ usr.user_Field7 + delimiter
//				+ usr.user_Field8 + delimiter
//				+ usr.user_Field9 + delimiter
//				+ usr.user_Field10 + delimiter
//				+ usr.user_Field11 + delimiter
//				+ usr.user_Field12 + delimiter
//				+ usr.user_Field13 + delimiter
//				+ usr.user_Field14 + delimiter
//				+ usr.user_Field15 + delimiter
//				+ usr.user_Field16 + delimiter
//				+ usr.user_Field17 + delimiter
//				+ usr.user_Field18 + delimiter
//				+ usr.user_Field19 + delimiter
//				+ usr.user_Field20 + delimiter
//				+ usr.user_Field21 + delimiter
//				+ usr.user_Field22 + delimiter
//				+ usr.user_Field23 + delimiter
//				+ usr.user_Field24 + delimiter
//				+ usr.user_Field25 + delimiter
//				+ usr.user_Field26 + delimiter
//				+ usr.user_Field27 + delimiter
//				+ usr.user_Field28 + delimiter
//				+ usr.user_Field29 + delimiter
//				+ usr.user_Field30 + delimiter
//				+ usr.user_Field31 + delimiter
//				+ usr.user_Field32 + delimiter
//				+ usr.user_Field33 + delimiter
//				+ usr.user_Field34 + delimiter
//				+ usr.user_Field35 + delimiter
//				+ usr.user_Field36 + delimiter
//				+ usr.user_Field37;
////		System.out.println(iu);
//		return iu;
		// v20160809 modify end

        StringBuilderUtil result = new StringBuilderUtil();
        result.appendFirst(usr2.category)
        .append(usr2.user_Field1)
        .append(usr2.user_Field2)
        .append(usr2.user_Field3)
        .append(usr2.user_Field4)
        .append(usr2.user_Field5)
        .append(usr2.user_Field6)
        .append(usr2.user_Field7)
        .append(usr2.user_Field8)
        .append(usr2.user_Field9)
        .append(usr2.user_Field10)
        .append(usr2.user_Field11)
        .append(usr2.user_Field12)
        .append(usr2.user_Field13)
        .append(usr2.user_Field14)
        .append(usr2.user_Field15)
        .append(usr2.user_Field16)
        .append(usr2.user_Field17)
        .append(usr2.user_Field18)
        .append(usr2.user_Field19)
        .append(usr2.user_Field20)
        .append(usr2.user_Field21)
        .append(usr2.user_Field22)
        .append(usr2.user_Field23)
        .append(usr2.user_Field24)
        .append(usr2.user_Field25)
        .append(usr2.user_Field26)
        .append(usr2.user_Field27)
        .append(usr2.user_Field28)
        .append(usr2.user_Field29)
        .append(usr2.user_Field30)
        .append(usr2.user_Field31)
        .append(usr2.user_Field32)
        .append(usr2.user_Field33)
        .append(usr2.user_Field34)
        .append(usr2.user_Field35)
        .append(usr2.user_Field36)
        .append(usr2.user_Field37)
        // v20160809 add start
        .append(usr2.user_Field41)
        .append(usr2.user_Field42)
        .append(usr2.user_Field43)
        .append(usr2.user_Field44);
        // v20160809 add end
        return result.toString();
	}

	public static String uca(UCA uca)
	{
		String iu = uca.category + delimiter
				+ uca.id_Number1 + delimiter
				+ uca.id_Number2 + delimiter
				+ uca.id_Number3 + delimiter
				+ uca.surname + delimiter
				+ uca.first_Name + delimiter
				+ uca.middle_Name + delimiter
				+ uca.home_Address1 + delimiter
				+ uca.home_Address2 + delimiter
				+ uca.home_Address3 + delimiter
				+ uca.home_Address4 + delimiter
				+ uca.home_Address5 + delimiter
				+ uca.company_Address1 + delimiter
				+ uca.company_Address2 + delimiter
				+ uca.user_Field1 + delimiter
				+ uca.user_Field2 + delimiter
				+ uca.user_Field3 + delimiter
				+ uca.user_Field4 + delimiter
				+ uca.user_Field6 + delimiter
				+ uca.user_Field7 + delimiter
				+ uca.user_Field8 + delimiter
				+ uca.user_Field9 + delimiter
				+ uca.user_Field10 + delimiter
				+ uca.user_Field11;
//		System.out.println(iu);
		return iu;
	}

	public static String u2a(U2A u2a)
    {
        StringBuilderUtil result = new StringBuilderUtil();
        result.appendFirst(u2a.category)
        .append(u2a.user_Field1)
        .append(u2a.user_Field2)
        .append(u2a.user_Field3)
        .append(u2a.user_Field4)
        .append(u2a.user_Field5)
        .append(u2a.user_Field6)
        .append(u2a.user_Field7)
        .append(u2a.user_Field8)
        .append(u2a.user_Field9)
        .append(u2a.user_Field10)
        .append(u2a.user_Field11)
        .append(u2a.user_Field12)
        // v20160830 add start
        .append(u2a.user_Field13)
        .append(u2a.user_Field14)
        .append(u2a.user_Field15)
        .append(u2a.user_Field16)
        .append(u2a.user_Field17)
        .append(u2a.user_Field18)
        .append(u2a.user_Field19)
        .append(u2a.user_Field20)
        .append(u2a.user_Field21)
        .append(u2a.user_Field22)
        .append(u2a.user_Field23)
        .append(u2a.user_Field24)
        .append(u2a.user_Field25)
        .append(u2a.user_Field26)
        .append(u2a.user_Field27)
        .append(u2a.user_Field28)
        .append(u2a.user_Field29)
        .append(u2a.user_Field30)
        .append(u2a.user_Field31)
        .append(u2a.user_Field32)
        .append(u2a.user_Field33)
        .append(u2a.user_Field34)
        .append(u2a.user_Field35)
        .append(u2a.user_Field36)
        .append(u2a.user_Field56)
        .append(u2a.user_Field57);
        // v20160830 add end

        return result.toString();
    }

	public static String u2b(U2B u2b)
    {
	    StringBuilderUtil result = new StringBuilderUtil();
        result.appendFirst(u2b.category)
        .append(u2b.user_Field1)
        .append(u2b.user_Field2)
        .append(u2b.user_Field3)
        .append(u2b.user_Field4)
        .append(u2b.user_Field5)
        .append(u2b.user_Field6)
        .append(u2b.user_Field7)
        .append(u2b.user_Field8)
        .append(u2b.user_Field9)
        .append(u2b.user_Field10)
        .append(u2b.user_Field11)
        .append(u2b.user_Field12)
        .append(u2b.user_Field13)
        .append(u2b.user_Field14)
        .append(u2b.user_Field15)
        .append(u2b.user_Field16)
        .append(u2b.user_Field17)
        .append(u2b.user_Field18)
        .append(u2b.user_Field19)
        .append(u2b.user_Field20)
        .append(u2b.user_Field21)
        .append(u2b.user_Field22)
        .append(u2b.user_Field23)
        .append(u2b.user_Field24)
        .append(u2b.user_Field25)
        .append(u2b.user_Field26)
        .append(u2b.user_Field27)
        .append(u2b.user_Field28)
        .append(u2b.user_Field29)
        .append(u2b.user_Field30)
        .append(u2b.user_Field31)
        .append(u2b.user_Field32)
        .append(u2b.user_Field33)
        .append(u2b.user_Field46)
        .append(u2b.user_Field47)
        .append(u2b.user_Field56)
        .append(u2b.user_Field57);

	    return result.toString();
    }

	public static String u2c(U2C u2c)
    {
        StringBuilderUtil result = new StringBuilderUtil();
        result.appendFirst(u2c.category)
        .append(u2c.user_Field1)
        .append(u2c.user_Field2)
        .append(u2c.user_Field3)
        .append(u2c.user_Field4)
        .append(u2c.user_Field5)
        .append(u2c.user_Field26)
        .append(u2c.user_Field27)
        .append(u2c.user_Field28)
        .append(u2c.user_Field29)
        .append(u2c.user_Field30)
        .append(u2c.user_Field31)
        .append(u2c.user_Field32)
        .append(u2c.user_Field33)
        .append(u2c.user_Field34)
        .append(u2c.user_Field35)
        .append(u2c.user_Field36)
        .append(u2c.user_Field37)
        .append(u2c.user_Field38)
        .append(u2c.user_Field39)
        .append(u2c.user_Field40)
        .append(u2c.user_Field41)
        .append(u2c.user_Field42)
        .append(u2c.user_Field43)
        .append(u2c.user_Field44)
        .append(u2c.user_Field45)
        .append(u2c.user_Field46)
        .append(u2c.user_Field47);

        return result.toString();
    }

	public static String ucb(UCB ucb)
    {
        StringBuilderUtil result = new StringBuilderUtil();
        result.appendFirst(ucb.category)
        .append(ucb.user_Field1)
        .append(ucb.user_Field2);

        return result.toString();
    }

	/**
	 * This method constructs a request string of a single application from Omni model to Instinct model.
	 *
	 * @param appId the application id of Omni model to construct to Instinct model.
	 * @return resulting Instinct string delimited by <tt>delimiter</tt>.
	 * @see #allInOne(List)
	 *
	 */
	public static String allInOne(String appId)
	{
		List<String> input = new ArrayList<>();
		input.add(appId);
		List<String> res = new ArrayList<>();
		res = allInOne(input);
		return res.get(0);
	}

	/**
	 * This method constructs a request string of a single application from Omni model to Instinct model with additional parameter
	 * <tt>instinctCall</tt> indicating the judging phase from Omni process.
	 *
	 * @param appId the application ID of Omni model to construct to Instinct model.
	 * @param instinctCall supports "preCheck", "finalCheck" and "update".
	 * @return resulting Instinct string delimited by <tt>delimiter</tt>.
	 * @see #allInOne(List)
	 * @since 1.0.0
	 *
	 */
	public static synchronized String allInOne(String appId, String instinctCall)
	{
		List<String> input = new ArrayList<>();
		input.add(appId);
		List<String> res = new ArrayList<>();
		res = allInOne(input, instinctCall);
		return res.get(0);
	}

	/**
	 * This method constructs a list of request strings from Omni model to Instinct model.
	 *
	 * @param appIds the list of application IDs from Omni model to construct to Instinct model.
	 * @return a list of resulting Instinct strings separated by line break and internally delimited by <tt>delimiter</tt>.
	 * @see #allInOne(List)
	 *
	 */
	public static synchronized List<String> allInOne(List<String> appIds)
	{
		return allInOne(appIds, "update");
	}

	/**
	 * This method constructs a list of request strings from Omni model to Instinct model with additional parameter
	 * <tt>instinctCall</tt> indicating the judging phase from Omni process.
	 *
	 * @param appIds the list of application IDs from Omni model to construct to Instinct model.
	 * @param instinctCall supports "preCheck", "finalCheck" and "update".
	 * @return a list of resulting Instinct strings separated by line break and internally delimited by <tt>delimiter</tt>.
	 * @since 1.0.0
	 *
	 */
	public static synchronized List<String> allInOne(List<String> appIds, String instinctCall)
	{
		List<String> res = new ArrayList<>();

		DataContainer.initialize(appIds, instinctCall);
		appIds.forEach(appId->
		{
			Application application = new Application(new omni.model.Application(appId, instinctCall));
			Applicant applicant = new Applicant(new omni.model.Applicant(appId, instinctCall));
			CBA introducerAgent = new CBA(new omni.model.CBA(appId, instinctCall));

			Reference reference = new Reference(new omni.model.Reference(appId, instinctCall));
			CBB valuer = new CBB(new omni.model.CBB(appId, instinctCall));
			User user = new User(new omni.model.User(appId, instinctCall));
			User2 user2 = new User2(new omni.model.User2(appId, instinctCall));
			UCA uca = new UCA(new omni.model.UCA(appId, instinctCall));
			// v20160809 add start
	        U2A u2a = new U2A(new omni.model.U2A(appId, instinctCall));
	        // v20160809 add end
	        // v20161121 add start
            U2B u2b = new U2B(new omni.model.U2B(appId, instinctCall));
            U2C u2c = new U2C(new omni.model.U2C(appId, instinctCall));
            // v20161121 add end
            UCB ucb = new UCB(new omni.model.UCB(appId, instinctCall));

			String subRefResult = "";
			Iterator<SubReference> ite = reference.subReferences.iterator();
			while (ite.hasNext())
			{
				subRefResult += InstinctizeModel.reference(ite.next()) + delimiter2;
			}

			// v20160809 modify start 用StringBuilder替换字符串+拼接
//			String allInOne = InstinctizeModel.application(application) + delimiter2 + InstinctizeModel.applicant(applicant) + delimiter2
//					+ subRefResult + InstinctizeModel.user(user) + delimiter2
//					+ InstinctizeModel.user2(user2) + delimiter2 + InstinctizeModel.uca(uca) + delimiter2 + InstinctizeModel.cba(introducerAgent) + delimiter2
//					+ InstinctizeModel.cbb(valuer);
			StringBuilderUtil result = new StringBuilderUtil();
	        result.appendFirst(InstinctizeModel.application(application))
	        .append(InstinctizeModel.applicant(applicant))
	        .append(subRefResult)
	        .appendNoDelimiter(InstinctizeModel.user(user))
	        .append(InstinctizeModel.user2(user2))
	        .append(InstinctizeModel.uca(uca))
	        .append(InstinctizeModel.cba(introducerAgent))
	        .append(InstinctizeModel.u2a(u2a))
	        .append(InstinctizeModel.ucb(ucb))
	        .append(InstinctizeModel.cbb(valuer))
	        .append(InstinctizeModel.u2b(u2b))
	        .append(InstinctizeModel.u2c(u2c));

			String allInOne = result.toString();
			// v20160809 modify end

			res.add(allInOne);
		});

		//	res.forEach(result->System.out.println(result));
		Logger.get().info("One batch completed!!");
		return res;
	}

	/**
	 * This method constructs a single black list string from Omni model to Instinct model.
	 *
	 * @param appblk the application field of Omni model's single black list.
	 * @param apptblk the applicant field of Omni model's single black list.
	 * @return a black list string delimited by <tt>delimiter</tt>.
	 *
	 */
	public static synchronized String singleBlackList(Application appblk, Applicant apptblk)
	{
		String blacklist = InstinctizeModel.application(appblk) + delimiter2
				+ InstinctizeModel.applicant(apptblk);
		System.out.println(blacklist);
		return blacklist;
	}

}
