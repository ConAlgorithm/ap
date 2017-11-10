package exporter.userAttachment;

import java.util.Date;
import java.util.List;

import catfish.base.CollectionUtils;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;

public class AttachmentDao {
  
  public List<Model> getAllAppsByTagAndBankNames(String fundTag, List<String> bankNames, 
      Integer status, Date startDate, Date endDate) {
    
    StringBuilder str = new StringBuilder();
    for(String bankName : bankNames) {
      str.append("'").append(bankName).append("', ");
    }
  
    return Dao.create(Model.class, DatabaseApi.database, true)
        .getMultiple(String.format(getAllAppsByTagAndBankNamesSql, 
            str.substring(0, str.length() - 2)), 
            CollectionUtils.mapOf("fundTag", fundTag, "status", status, 
                "startDate", startDate, "endDate", endDate));
  }
  
  public PaymentModel getPaymentInfo(String appId) {
    
    return Dao.create(PaymentModel.class, DatabaseApi.database, true)
        .getSingle(getPaymentInfoSql, CollectionUtils.mapOf("appId", appId));
  }
  
  public String getUserPhoto(String userId, Integer type) {
    
    return DatabaseApi.querySingleStringOrDefault(getUserPhotoSql, 
       CollectionUtils.mapOf("userId", userId, "type", type), "");
  }
  
  private static final String getAllAppsByTagAndBankNamesSql = 
      "SELECT m.AppId, m.UserId, m.BankAccount, m.BankName, m.BankAccountName, m.FilePath "
      + "FROM "
      + "(SELECT n = ROW_NUMBER() OVER (PARTITION BY AppId ORDER BY DateAdded DESC), * FROM "
      + "(SELECT i.Id AS AppId, i.UserId, pa.Id AS paId, "
      + "pa.DateAdded, p.BankName,  p.BankAccount, p.BankAccountName, a.FilePath "
      + "FROM FundObjects f "
      + "INNER JOIN InstallmentApplicationObjects i ON i.FundId = f.Id "
      + "INNER JOIN PaymentApplicationObjects pa ON pa.ApplicationId = i.Id "
      + "INNER JOIN PaymentObjects p ON p.Id = pa.PaymentId "
      + "INNER JOIN AttachmentObjects a ON a.Id = p.AttachmentId "
      + "WHERE f.FundTag = :fundTag AND i.Status = :status "
      + "AND DATEDIFF(DAY, :startDate, i.DateAdded) >= 0 "
      + "AND DATEDIFF(DAY, :endDate, i.DateAdded) <= 0) t) m "
      + "INNER JOIN (SELECT DISTINCT BankName FROM DetailedBankObjects WHERE BankCategoryName IN (%s)) "
      + " db ON db.BankName = m.BankName "
      + "where m.n = 1";
  
  private static final String getPaymentInfoSql = 
      "SELECT TOP 1 a.FilePath, p.BankAccount, p.BankName, p.BankAccountName "
      + "FROM PaymentApplicationObjects pa "
      + "INNER JOIN PaymentObjects p ON p.Id = pa.PaymentId "
      + "INNER JOIN AttachmentObjects a ON a.Id = p.AttachmentId "
      + "WHERE pa.ApplicationId = :appId ORDER BY pa.DateAdded DESC";
  
  private static final String getUserPhotoSql = "SELECT TOP 1 a.FilePath FROM UserAttachmentObjects ua "
      + "INNER JOIN AttachmentObjects a ON ua.AttachmentId = a.Id "
      + "WHERE ua.UserId = :userId AND ua.Type = :type ORDER BY ua.DateAdded DESC";
}
