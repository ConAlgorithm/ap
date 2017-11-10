package catfish.fundcontroller.jimubox;

import java.util.Map;


import catfish.base.CollectionUtils;
import catfish.base.business.dao.AbstractDao;
import catfish.base.database.DatabaseApi;

public class ApplicationModelDao extends AbstractDao<ApplicationObject> {
    //private static final String singleSql = "select Principal, Repayments, IdName,IdNumber from InstallmentApplicationObjects inner join EndUserExtensionObjects on InstallmentApplicationObjects.UserId = EndUserExtensionObjects.Id where InstallmentApplicationObjects.Id = :AppId";
    private static final String UserAttachmentSql = "select top 1 FilePath from InstallmentApplicationObjects"
            + " left join UserAttachmentObjects on InstallmentApplicationObjects.UserId  =UserAttachmentObjects.UserId"
            + " left join AttachmentObjects on UserAttachmentObjects.AttachmentId = AttachmentObjects.Id"
            + " where InstallmentApplicationObjects.Id=:AppId and Type = %s "
            + " order by UserAttachmentObjects.DateAdded desc";
    private static final String IdPhotoSql = String.format(UserAttachmentSql, "101");
    private static final String IOUSql = "select FilePath from AttachmentObjects ao "
    		+ "join UserAttachmentObjects ua on ao.id=ua.AttachmentId "
    		+ "join InstallmentApplicationObjects ia on ia.UserId=ua.UserId "
    		+ "where ua.Type=108 and ia.id=:AppId "
    		+ "order by ua.DateAdded desc";
    
    private static final String ApplicationAttachmentSql = "select top 1  FilePath from InstallmentApplicationObjects"
            + " left join ApplicationAttachmentObjects on InstallmentApplicationObjects.Id = ApplicationAttachmentObjects.ApplicationId"
            + " left join AttachmentObjects on ApplicationAttachmentObjects.AttachmentId = AttachmentObjects.Id"
            + " where InstallmentApplicationObjects.Id=:AppId and ApplicationAttachmentObjects.Type = %s"
            + " order by ApplicationAttachmentObjects.DateAdded desc";
    private static final String AgreementSql = String.format(ApplicationAttachmentSql, "800");
    private static final String TransferVoucherSql = String.format(ApplicationAttachmentSql, "300");
    
    private static final String singleSql="select top 1 ia.Id as 'ProjectNo',"
    		+ "e.idname as 'ChineseName',"
    		+ "e.IdNumber as 'IdentityNumber',"
    		+ "co.Content as 'Phone',"
    		+ "pio.MarriageStatus as 'MaritalStatus',"
    		+ "jio.CompanyName as 'CompanyName',"
    		+ "jio.JobType as 'CompanyNature',"
    		+ "jco.Content as 'CompanyTel',"
    		+ "cpo.Name as 'RelaName',"
    		+ "rco.Content as 'RelaPhone',"
    		+ "cpo.Relationship as 'Relationship',"
    		+ "ia.Repayments as 'Batch',"
    		+ "ia.principal as 'FinancingAmount',"
    		+ "ia.MonthlyPay as 'HJXD_RepaymentByMonth',"
    		+ "CONVERT(varchar(100), ia.FirstPaybackDate, 23) as 'RepaymentStartDate',"
    		+ "CONVERT(varchar(100), DATEADD(mm,ia.Repayments-1,ia.FirstPaybackDate), 23) as 'RepaymentEndDate',"
    		+ "DATEPART(dd,ia.MonthlyPaybackDay) as 'RepaymentDay',"
    		+ "ia.ProductName as 'ProductName',"
    		+ "cdo.Price as 'SalePrice',"
    		+ "po.BankName as 'BankName',"
    		+ "po.BankAccount as 'AccountNo',"
    		+ "ms.Name as 'MerchantName',"
    		+ "p.name as 'MerchantProvince',"
    		+ "c.name as 'MerchantCity' ,"
    		+ "idao.FilePath as 'idPhotoPath',"
    		+ "agao.FilePath as 'iouPath',"
    		+ "tvao.FilePath as 'agreementPath',"
    		+ "iouuao.FilePath as 'transferVoucher' "
    		+ "from installmentapplicationobjects ia "
    		+ "join EndUserExtensionObjects e on e.id=ia.userid "
    		+ "join PaymentApplicationObjects pa on pa.ApplicationId=ia.Id "
    		+ "join PaymentObjects po on po.id=pa.paymentid "
    		+ "join merchantstoreobjects ms on ms.id=ia.merchantstoreid "
    		+ "join CNAreaObjects d on d.id=ms.cnareaid "
    		+ "join cnareaobjects c on d.parentcode=c.code "
    		+ "join cnareaobjects p on c.ParentCode=p.code "
    		+ "join UserObjects u on u.Id=ia.userid "
    		+ "join ContactObjects co on co.Id=u.MobileContactId "
    		+ "join ContactPersonObjects cpo on cpo.AppId=ia.Id "
    		+ "join ContactObjects rco on rco.Id=cpo.MobileContactId "
    		+ "join JobInfoObjects jio on jio.ApplicationId=ia.Id "
    		+ "join ContactObjects jco on jco.Id=jio.CompanyTelId "
    		+ "join PersonalInfoObjects pio on pio.AppId=ia.Id "
    		+ "left join CommodityObjects cdo on cdo.Name=ia.ProductName "
    		+ "left join UserAttachmentObjects ida on ida.UserId=ia.UserId "
    		+ "left join AttachmentObjects idao on idao.Id=ida.AttachmentId "
    		+ "left join ApplicationAttachmentObjects aga on aga.ApplicationId=ia.Id "
    		+ "left join AttachmentObjects agao on agao.Id=aga.AttachmentId "
    		+ "left join ApplicationAttachmentObjects tva on tva.ApplicationId=ia.Id "
    		+ "left join AttachmentObjects tvao on tvao.Id=tva.AttachmentId "
    		+ "left join UserAttachmentObjects iouua on iouua.UserId=ia.UserId "
    		+ "left join AttachmentObjects iouuao on iouuao.Id=iouua.AttachmentId "
    		+ "where ia.status=100  "
    		+ "and (ida.Type=101 OR ida.Type=null) "
    		+ "and (aga.Type=800  OR aga.Type=null) "
    		+ "and (tva.Type=300 OR tva.Type=null) "
    		+ "and (iouua.Type=108 OR iouua.Type=null)  "
    		+ "and ia.id = :AppId "
    		+ "order by pa.DateAdded desc,jio.DateAdded desc, cpo.DateAdded desc, pio.DateAdded desc";
    
    private static final String getFundTagByAppIdSql="SELECT FundTag FROM FundObjects fo "
    		+ "join InstallmentApplicationObjects ia on ia.FundId= fo.Id where ia.Id=:AppId";
    
    private static final String insertResponseRecordsSql = 
			  "INSERT INTO JMBoxResponseResultObjects "
			  + "(AppId,ChineseName,IdentityNumber,ProjectID,ProjectNo,Status,Message,RetryTime,DateAdded,LastModified)"
			  + "VALUES "
			  + "(:AppId ,:ChineseName ,:IdentityNumber ,:ProjectID,:ProjectNo ,:Status ,:Message,:RetryTime , GETDATE(), GETDATE())";
    
    public ApplicationModelDao(String appId) {
        super(appId);
    }
    
    @Override
    protected String buildSingleSql() {
      return singleSql;
    }

    @Override
    protected Map<String, ?> buildSingleParams() {
        return CollectionUtils.mapOf("AppId", appId);
    }

    @Override
    protected String buildMultipleSql() {
      return null;
    }
    
    @Override
    protected Map<String, ?> buildMultipleParams() {
      // TODO Auto-generated method stub
      return null;
    }
    
    public String getIdPhotoPath(){
        return DatabaseApi.querySingleStringOrDefault(IdPhotoSql, CollectionUtils.mapOf("AppId", appId), null);
    }
    
    public String getIOUPath(){
        return DatabaseApi.querySingleStringOrDefault(IOUSql, CollectionUtils.mapOf("AppId", appId), null);
    }
    
    public String getAgreementPDFPath(){
        return DatabaseApi.querySingleStringOrDefault(AgreementSql, CollectionUtils.mapOf("AppId", appId), null);
    }
    
    public String getTransferVoucherPath(){
        return DatabaseApi.querySingleStringOrDefault(TransferVoucherSql, CollectionUtils.mapOf("AppId", appId), null);
    }
    
    public String getFundTagByAppId(){
        return DatabaseApi.querySingleStringOrDefault(getFundTagByAppIdSql, CollectionUtils.mapOf("AppId", appId), null);
    }
      
	public static int InsertResponseRecord(JMBoxResponseModel response){
		  Map<String, Object> params = CollectionUtils.<String, Object>newMapBuilder()
				  .add("AppId", response.AppId)
				  .add("ChineseName", response.ChineseName)
				  .add("IdentityNumber", response.IdentityNumber)
				  .add("Message", response.Message)
				  .add("ProjectID", response.ProjectID)
				  .add("ProjectNo", response.ProjectNo)
				  .add("RetryTime", response.RetryTime)
				  .add("Status", response.Status)				  
				  .build();
		  return DatabaseApi.updateTable(insertResponseRecordsSql, params);
	}
}

