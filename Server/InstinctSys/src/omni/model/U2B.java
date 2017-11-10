/**
 * Copyright (C), 上海秦苍信息科技有限公司
 */
package omni.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import catfish.base.Logger;
import omni.database.DataContainer;
import omni.database.catfish.object.hybrid.AddressInfoBean;
import omni.database.catfish.object.hybrid.AppContactPersonObject;
import omni.database.catfish.object.hybrid.Contact1InfoBean;
import omni.database.catfish.object.hybrid.PersonWorkDetailBean;
import omni.database.mongo.DerivativeVariables;

/**
 * 〈百融黑名单字段〉
 *
 * @author dengw
 * @version U2B.java, V1.0 2016年11月18日 下午6:20:34
 */
public class U2B {
    /** 百融身份证银行欺诈 */
    private String x_BR_SpecialListIdBankFraud;
    /** 百融身份证银行失联 */
    private String x_BR_SpecialListIdBankLost;
    /** 百融身份证银行拒绝 */
    private String x_BR_SpecialListIdBankRefuse;
    /** 百融身份证P2P欺诈 */
    private String x_BR_SpecialListIdP2PFraud;
    /** 百融身份证P2P失联 */
    private String x_BR_SpecialListIdP2PLost;
    /** 百融身份证P2P拒绝*/
    private String x_BR_SpecialListIdP2PRefuse;
    /** 百融身份证保险骗保 */
    private String x_BR_SpecialListIdInsFraud;
    /** 百融手机银行欺诈 */
    private String x_BR_SpecialListCellBankFraud;
    /** 百融手机银行失联 */
    private String x_BR_SpecialListCellBankLost;
    /** 百融手机银行拒绝 */
    private String x_BR_SpecialListCellBankRefuse;
    /** 百融手机P2P欺诈 */
    private String x_BR_SpecialListCellP2PFraud;
    /** 百融手机P2P失联 */
    private String x_BR_SpecialListCellP2PLost;
    /** 百融手机P2P拒绝 */
    private String x_BR_SpecialListCellP2PRefuse;
    /** 百融手机保险骗保 */
    private String x_BR_SpecialListCellInsFraud;

    /** 上海资信 -姓名 */
    private String x_SHZX_Name;
    /** 上海资信 -证件号码 */
    private String x_SHZX_IdNo;
    /** 上海资信 -配偶姓名 */
    private String x_SHZX_MateName;
    /** 上海资信 -配偶证件号码 */
    private String x_SHZX_MateIdNo;
    /** 上海资信 -婚姻明细 */
    private String x_SHZX_MaritalStatus;
    /** 上海资信 -婚姻信息获取日期 */
    private Date x_SHZX_MaritalInfoDateTime;
    /** 上海资信 -学历明细 */
    private String x_SHZX_EducationDegree;
    /** 上海资信 -职称明细 */
    private String x_SHZX_TitleDetails;
    /** 上海资信 -住宅电话明细 */
    private String x_SHZX_HomePhone;
    /** 上海资信 -手机号码明细 */
    private String x_SHZX_Mobile;
    /** 上海资信 -电子邮箱明细 */
    private String x_SHZX_Email;

    /** 上海资信 -配偶工作单位明细 */
    private String x_SHZX_MateWorkDetail;
    /** 上海资信 -配偶联系电话明细 */
    private String x_SHZX_MateContactPhone;

    /**上海资信-联系人姓名是否在联系人姓名里*/
    private String x_SHZX_IsInContact1Name;
    /**上海资信-联系人电话是否在联系人电话里 */
    private String x_SHZX_IsInContact1Phone;

    /** 上海资信 -手机号码信息获取日期 */
    private Date x_SHZX_MobileInfoDateTime;

    /** 上海资信 -配偶联系方式信息获取日期 */
    private Date x_SHZX_MateContactInfoDateTime;

    /** 上海资信 工作单位列表(xx公司，职业，日期；)*/
    private String x_SHZX_PersonWorkDetailList;
    /** 上海资信 地址列表(xx室，日期；)*/
    private String x_SHZX_AddressInfoList;
    
    /** 设备信息id**/
    private String x_DEVICEINFO_ID;
    /** 设备信息name**/
    private String x_DEVICEINFO_NAME;
    /** 设备信息纬度**/
    private Double x_DEVICEINFO_LATITUDE;
    /** 设备信息经度**/
    private Double x_DEVICEINFO_LOGITUDE;

    public U2B(String appId) {
        this(appId, "");
    }

    public U2B(String appId, String instinctCall) {
        this.initialize(appId);
    }

    private void initialize(String appId) {
        DerivativeVariables mongodv = DataContainer.mongodv.get(appId);
        List<AppContactPersonObject> cpObj = DataContainer.conPerObj.get(appId);
        String contact1Name = null;
        String contact1Phone = null;
        for (AppContactPersonObject appContactPersonObject : cpObj) {
            if (appContactPersonObject.ContactPersonType == 2) {
                contact1Name = appContactPersonObject.Name;
                contact1Phone = appContactPersonObject.mobile;
            }
        }
        if (mongodv != null) {
            this.x_BR_SpecialListIdBankFraud = mongodv.X_BR_SpecialListIdBankFraud;
            this.x_BR_SpecialListIdBankLost = mongodv.X_BR_SpecialListIdBankLost;
            this.x_BR_SpecialListIdBankRefuse = mongodv.X_BR_SpecialListIdBankRefuse;
            this.x_BR_SpecialListIdP2PFraud = mongodv.X_BR_SpecialListIdP2PFraud;
            this.x_BR_SpecialListIdP2PLost = mongodv.X_BR_SpecialListIdP2PLost;
            this.x_BR_SpecialListIdP2PRefuse = mongodv.X_BR_SpecialListIdP2PRefuse;
            this.x_BR_SpecialListIdInsFraud = mongodv.X_BR_SpecialListIdInsFraud;
            this.x_BR_SpecialListCellBankFraud = mongodv.X_BR_SpecialListCellBankFraud;
            this.x_BR_SpecialListCellBankLost = mongodv.X_BR_SpecialListCellBankLost;
            this.x_BR_SpecialListCellBankRefuse = mongodv.X_BR_SpecialListCellBankRefuse;
            this.x_BR_SpecialListCellP2PFraud = mongodv.X_BR_SpecialListCellP2PFraud;
            this.x_BR_SpecialListCellP2PLost = mongodv.X_BR_SpecialListCellP2PLost;
            this.x_BR_SpecialListCellP2PRefuse = mongodv.X_BR_SpecialListCellP2PRefuse;
            this.x_BR_SpecialListCellInsFraud = mongodv.X_BR_SpecialListCellInsFraud;
            this.x_SHZX_Name = mongodv.X_SHZX_Name;
            this.x_SHZX_IdNo = mongodv.X_SHZX_IdNo;
            this.x_SHZX_MateName = mongodv.X_SHZX_MateName;
            this.x_SHZX_MateIdNo = mongodv.X_SHZX_MateIdNo;
            this.x_SHZX_MaritalStatus = mongodv.X_SHZX_MaritalStatus;
            this.x_SHZX_MaritalInfoDateTime = getDate(mongodv.X_SHZX_MaritalInfoDateTime);
            this.x_SHZX_EducationDegree = mongodv.X_SHZX_EducationDegree;
            this.x_SHZX_TitleDetails = mongodv.X_SHZX_TitleDetails;
            this.x_SHZX_HomePhone = mongodv.X_SHZX_HomePhone;
            this.x_SHZX_Mobile = mongodv.X_SHZX_Mobile;
            this.x_SHZX_Email = mongodv.X_SHZX_Email;
            this.x_SHZX_MateWorkDetail = mongodv.X_SHZX_MateWorkDetail;
            this.x_SHZX_MateContactPhone = mongodv.X_SHZX_MateContactPhone;
            this.x_SHZX_IsInContact1Name = getIsInContact1Name(new Gson().fromJson(mongodv.X_SHZX_Contact1InfoBeanList, new TypeToken<List<Contact1InfoBean>>() {
            }.getType()), contact1Name);
            this.x_SHZX_IsInContact1Phone = getIsInContact1Phone(new Gson().fromJson(mongodv.X_SHZX_Contact1InfoBeanList, new TypeToken<List<Contact1InfoBean>>() {
            }.getType()), contact1Phone);
            this.x_SHZX_MobileInfoDateTime = getDate(mongodv.X_SHZX_MobileInfoDateTime);
            this.x_SHZX_MateContactInfoDateTime = getDate(mongodv.X_SHZX_MateContactInfoDateTime);
            this.x_SHZX_PersonWorkDetailList = getPersonWorkDetailList(new Gson().fromJson(mongodv.X_SHZX_PersonWorkDetailList, new TypeToken<List<PersonWorkDetailBean>>() {
            }.getType()));
            this.x_SHZX_AddressInfoList = getAddressInfoList(new Gson().fromJson(mongodv.X_SHZX_AddressInfoList, new TypeToken<List<AddressInfoBean>>() {
            }.getType()));
            this.x_DEVICEINFO_ID=mongodv.X_DEVICEINFO_ID;
            this.x_DEVICEINFO_NAME=mongodv.X_DEVICEINFO_NAME;
            this.x_DEVICEINFO_LATITUDE=mongodv.X_DEVICEINFO_LATITUDE;
            this.x_DEVICEINFO_LOGITUDE=mongodv.X_DEVICEINFO_LOGITUDE;
        }
    }

    public String getX_BR_SpecialListIdBankFraud() {
        return x_BR_SpecialListIdBankFraud;
    }

    public void setX_BR_SpecialListIdBankFraud(String x_BR_SpecialListIdBankFraud) {
        this.x_BR_SpecialListIdBankFraud = x_BR_SpecialListIdBankFraud;
    }

    public String getX_BR_SpecialListIdBankLost() {
        return x_BR_SpecialListIdBankLost;
    }

    public void setX_BR_SpecialListIdBankLost(String x_BR_SpecialListIdBankLost) {
        this.x_BR_SpecialListIdBankLost = x_BR_SpecialListIdBankLost;
    }

    public String getX_BR_SpecialListIdBankRefuse() {
        return x_BR_SpecialListIdBankRefuse;
    }

    public void setX_BR_SpecialListIdBankRefuse(String x_BR_SpecialListIdBankRefuse) {
        this.x_BR_SpecialListIdBankRefuse = x_BR_SpecialListIdBankRefuse;
    }

    public String getX_BR_SpecialListIdP2PFraud() {
        return x_BR_SpecialListIdP2PFraud;
    }

    public void setX_BR_SpecialListIdP2PFraud(String x_BR_SpecialListIdP2PFraud) {
        this.x_BR_SpecialListIdP2PFraud = x_BR_SpecialListIdP2PFraud;
    }

    public String getX_BR_SpecialListIdP2PLost() {
        return x_BR_SpecialListIdP2PLost;
    }

    public void setX_BR_SpecialListIdP2PLost(String x_BR_SpecialListIdP2PLost) {
        this.x_BR_SpecialListIdP2PLost = x_BR_SpecialListIdP2PLost;
    }

    public String getX_BR_SpecialListIdP2PRefuse() {
        return x_BR_SpecialListIdP2PRefuse;
    }

    public void setX_BR_SpecialListIdP2PRefuse(String x_BR_SpecialListIdP2PRefuse) {
        this.x_BR_SpecialListIdP2PRefuse = x_BR_SpecialListIdP2PRefuse;
    }

    public String getX_BR_SpecialListIdInsFraud() {
        return x_BR_SpecialListIdInsFraud;
    }

    public void setX_BR_SpecialListIdInsFraud(String x_BR_SpecialListIdInsFraud) {
        this.x_BR_SpecialListIdInsFraud = x_BR_SpecialListIdInsFraud;
    }

    public String getX_BR_SpecialListCellBankFraud() {
        return x_BR_SpecialListCellBankFraud;
    }

    public void setX_BR_SpecialListCellBankFraud(String x_BR_SpecialListCellBankFraud) {
        this.x_BR_SpecialListCellBankFraud = x_BR_SpecialListCellBankFraud;
    }

    public String getX_BR_SpecialListCellBankLost() {
        return x_BR_SpecialListCellBankLost;
    }

    public void setX_BR_SpecialListCellBankLost(String x_BR_SpecialListCellBankLost) {
        this.x_BR_SpecialListCellBankLost = x_BR_SpecialListCellBankLost;
    }

    public String getX_BR_SpecialListCellBankRefuse() {
        return x_BR_SpecialListCellBankRefuse;
    }

    public void setX_BR_SpecialListCellBankRefuse(String x_BR_SpecialListCellBankRefuse) {
        this.x_BR_SpecialListCellBankRefuse = x_BR_SpecialListCellBankRefuse;
    }

    public String getX_BR_SpecialListCellP2PFraud() {
        return x_BR_SpecialListCellP2PFraud;
    }

    public void setX_BR_SpecialListCellP2PFraud(String x_BR_SpecialListCellP2PFraud) {
        this.x_BR_SpecialListCellP2PFraud = x_BR_SpecialListCellP2PFraud;
    }

    public String getX_BR_SpecialListCellP2PLost() {
        return x_BR_SpecialListCellP2PLost;
    }

    public void setX_BR_SpecialListCellP2PLost(String x_BR_SpecialListCellP2PLost) {
        this.x_BR_SpecialListCellP2PLost = x_BR_SpecialListCellP2PLost;
    }

    public String getX_BR_SpecialListCellP2PRefuse() {
        return x_BR_SpecialListCellP2PRefuse;
    }

    public void setX_BR_SpecialListCellP2PRefuse(String x_BR_SpecialListCellP2PRefuse) {
        this.x_BR_SpecialListCellP2PRefuse = x_BR_SpecialListCellP2PRefuse;
    }

    public String getX_BR_SpecialListCellInsFraud() {
        return x_BR_SpecialListCellInsFraud;
    }

    public void setX_BR_SpecialListCellInsFraud(String x_BR_SpecialListCellInsFraud) {
        this.x_BR_SpecialListCellInsFraud = x_BR_SpecialListCellInsFraud;
    }

    public String getX_SHZX_Name() {
        return x_SHZX_Name;
    }

    public void setX_SHZX_Name(String x_SHZX_Name) {
        this.x_SHZX_Name = x_SHZX_Name;
    }

    public String getX_SHZX_IdNo() {
        return x_SHZX_IdNo;
    }

    public void setX_SHZX_IdNo(String x_SHZX_IdNo) {
        this.x_SHZX_IdNo = x_SHZX_IdNo;
    }

    public String getX_SHZX_MateName() {
        return x_SHZX_MateName;
    }

    public void setX_SHZX_MateName(String x_SHZX_MateName) {
        this.x_SHZX_MateName = x_SHZX_MateName;
    }

    public String getX_SHZX_MateIdNo() {
        return x_SHZX_MateIdNo;
    }

    public void setX_SHZX_MateIdNo(String x_SHZX_MateIdNo) {
        this.x_SHZX_MateIdNo = x_SHZX_MateIdNo;
    }

    public String getX_SHZX_MaritalStatus() {
        return x_SHZX_MaritalStatus;
    }

    public void setX_SHZX_MaritalStatus(String x_SHZX_MaritalStatus) {
        this.x_SHZX_MaritalStatus = x_SHZX_MaritalStatus;
    }

    public Date getX_SHZX_MaritalInfoDateTime() {
        return x_SHZX_MaritalInfoDateTime;
    }

    public void setX_SHZX_MaritalInfoDateTime(Date x_SHZX_MaritalInfoDateTime) {
        this.x_SHZX_MaritalInfoDateTime = x_SHZX_MaritalInfoDateTime;
    }

    public String getX_SHZX_EducationDegree() {
        return x_SHZX_EducationDegree;
    }

    public void setX_SHZX_EducationDegree(String x_SHZX_EducationDegree) {
        this.x_SHZX_EducationDegree = x_SHZX_EducationDegree;
    }

    public String getX_SHZX_TitleDetails() {
        return x_SHZX_TitleDetails;
    }

    public void setX_SHZX_TitleDetails(String x_SHZX_TitleDetails) {
        this.x_SHZX_TitleDetails = x_SHZX_TitleDetails;
    }

    public String getX_SHZX_HomePhone() {
        return x_SHZX_HomePhone;
    }

    public void setX_SHZX_HomePhone(String x_SHZX_HomePhone) {
        this.x_SHZX_HomePhone = x_SHZX_HomePhone;
    }

    public String getX_SHZX_Mobile() {
        return x_SHZX_Mobile;
    }

    public void setX_SHZX_Mobile(String x_SHZX_Mobile) {
        this.x_SHZX_Mobile = x_SHZX_Mobile;
    }

    public String getX_SHZX_Email() {
        return x_SHZX_Email;
    }

    public void setX_SHZX_Email(String x_SHZX_Email) {
        this.x_SHZX_Email = x_SHZX_Email;
    }

    public String getX_SHZX_MateWorkDetail() {
        return x_SHZX_MateWorkDetail;
    }

    public void setX_SHZX_MateWorkDetail(String x_SHZX_MateWorkDetail) {
        this.x_SHZX_MateWorkDetail = x_SHZX_MateWorkDetail;
    }

    public String getX_SHZX_MateContactPhone() {
        return x_SHZX_MateContactPhone;
    }

    public void setX_SHZX_MateContactPhone(String x_SHZX_MateContactPhone) {
        this.x_SHZX_MateContactPhone = x_SHZX_MateContactPhone;
    }

    public Date getX_SHZX_MobileInfoDateTime() {
        return x_SHZX_MobileInfoDateTime;
    }

    public void setX_SHZX_MobileInfoDateTime(Date x_SHZX_MobileInfoDateTime) {
        this.x_SHZX_MobileInfoDateTime = x_SHZX_MobileInfoDateTime;
    }

    public Date getX_SHZX_MateContactInfoDateTime() {
        return x_SHZX_MateContactInfoDateTime;
    }

    public void setX_SHZX_MateContactInfoDateTime(Date x_SHZX_MateContactInfoDateTime) {
        this.x_SHZX_MateContactInfoDateTime = x_SHZX_MateContactInfoDateTime;
    }

    public String getX_SHZX_IsInContact1Name() {
        return x_SHZX_IsInContact1Name;
    }

    public void setX_SHZX_IsInContact1Name(String x_SHZX_IsInContact1Name) {
        this.x_SHZX_IsInContact1Name = x_SHZX_IsInContact1Name;
    }

    public String getX_SHZX_IsInContact1Phone() {
        return x_SHZX_IsInContact1Phone;
    }

    public void setX_SHZX_IsInContact1Phone(String x_SHZX_IsInContact1Phone) {
        this.x_SHZX_IsInContact1Phone = x_SHZX_IsInContact1Phone;
    }

    public String getX_SHZX_PersonWorkDetailList() {
        return x_SHZX_PersonWorkDetailList;
    }

    public void setX_SHZX_PersonWorkDetailList(String x_SHZX_PersonWorkDetailList) {
        this.x_SHZX_PersonWorkDetailList = x_SHZX_PersonWorkDetailList;
    }

    public String getX_SHZX_AddressInfoList() {
        return x_SHZX_AddressInfoList;
    }

    public void setX_SHZX_AddressInfoList(String x_SHZX_AddressInfoList) {
        this.x_SHZX_AddressInfoList = x_SHZX_AddressInfoList;
    }

    
    public String getX_DEVICEINFO_ID() {
		return x_DEVICEINFO_ID;
	}

	public void setX_DEVICEINFO_ID(String x_DEVICEINFO_ID) {
		this.x_DEVICEINFO_ID = x_DEVICEINFO_ID;
	}

	public String getX_DEVICEINFO_NAME() {
		return x_DEVICEINFO_NAME;
	}

	public void setX_DEVICEINFO_NAME(String x_DEVICEINFO_NAME) {
		this.x_DEVICEINFO_NAME = x_DEVICEINFO_NAME;
	}

	public Double getX_DEVICEINFO_LATITUDE() {
		return x_DEVICEINFO_LATITUDE;
	}

	public void setX_DEVICEINFO_LATITUDE(Double x_DEVICEINFO_LATITUDE) {
		this.x_DEVICEINFO_LATITUDE = x_DEVICEINFO_LATITUDE;
	}

	public Double getX_DEVICEINFO_LOGITUDE() {
		return x_DEVICEINFO_LOGITUDE;
	}

	public void setX_DEVICEINFO_LOGITUDE(Double x_DEVICEINFO_LOGITUDE) {
		this.x_DEVICEINFO_LOGITUDE = x_DEVICEINFO_LOGITUDE;
	}

	public Date getDate(String str) {
        if(str != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        	try {
                return sdf.parse(str);
            } catch (ParseException e) {
                Logger.get().error("parse date " + str, e);
            }
        }
        return null;
    }

    public String getIsInContact1Name(List<Contact1InfoBean> list, String name) {
        if (list != null && list.size() > 0 && name != null) {
            for (Contact1InfoBean contact1InfoBean : list) {
                if (contact1InfoBean.getContact1Name().equals(name)) {
                    return "true";
                }
            }
            return "false";
        }
        return null;
    }

    public String getIsInContact1Phone(List<Contact1InfoBean> list, String phone) {
        if (list != null && list.size() > 0 && phone != null) {
            for (Contact1InfoBean contact1InfoBean : list) {
                if (contact1InfoBean.getContact1Phone().equals(phone)) {
                    return "true";
                }
            }
            return "false";
        }
        return null;
    }

    public String getPersonWorkDetailList(List<PersonWorkDetailBean> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (PersonWorkDetailBean personWorkDetailBean : list) {
                sb.append(personWorkDetailBean.getWorkAddress() + ",");
                sb.append(personWorkDetailBean.getWorkProfession() + ",");
                sb.append(personWorkDetailBean.getWorkInfoDateTime() + ";");
            }
            return sb.toString();
        }
        return null;
    }

    public String getAddressInfoList(List<AddressInfoBean> list) {
        StringBuffer sb = new StringBuffer();
        if (list != null && list.size() > 0) {
            for (AddressInfoBean addressInfoBean : list) {
                sb.append(addressInfoBean.getAddressDetail() + ",");
                sb.append(addressInfoBean.getAddressInfoDateTime() + ";");
            }
            return sb.toString();
        }
        return null;
    }

}
