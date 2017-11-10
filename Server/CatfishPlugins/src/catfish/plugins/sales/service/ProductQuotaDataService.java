package catfish.plugins.sales.service;

import java.util.List;
import catfish.base.CollectionUtils;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.plugins.sales.dbobject.ProductQuotaObject;
import catfish.plugins.sales.model.ProductQuotaAndD3Model;

public class ProductQuotaDataService {

  public static int getQuotaByProductIdAndD3Id(String productId, String d3Id) {
    String sql = "select Quota "
        + "from ProductQuotaObjects "
        + "where ProductId = :productId and D3Id = :d3Id and DeletedOn is null "
        + "order by LastModified desc";
    return DatabaseApi.querySingleIntegerOrDefault(
        sql, CollectionUtils.mapOf("productId", productId, "d3Id", d3Id), -1);
  }

  public static List<ProductQuotaObject> getQuotaByD3Id(String d3Id) {
    String sql = "select * "
        + "from ProductQuotaObjects as pqouter "
        + "where pqouter.D3Id = :d3Id and pqouter.DeletedOn is null "
        + "    and not exists (select * "
        + "                    from ProductQuotaObjects as pqinner "
        + "                    where pqinner.ProductId = pqouter.ProductId "
        + "                        and pqinner.D3Id = pqouter.D3Id "
        + "                        and pqinner.DeletedOn is null "
        + "                        and pqinner.LastModified > pqouter.LastModified)";
    Dao<ProductQuotaObject> productQuotaDao = Dao.create(ProductQuotaObject.class, DatabaseApi.database);
    return productQuotaDao.getMultiple(sql, CollectionUtils.mapOf("d3Id", d3Id));
  }

  public static List<ProductQuotaObject> getAll() {
    String sql = "select * "
        + "from ProductQuotaObjects as pqouter "
        + "where pqouter.DeletedOn is null "
        + "    and not exists (select * "
        + "                    from ProductQuotaObjects as pqinner "
        + "                    where pqinner.ProductId = pqouter.ProductId "
        + "                        and pqinner.D3Id = pqouter.D3Id "
        + "                        and pqinner.DeletedOn is null "
        + "                        and pqinner.LastModified > pqouter.LastModified)";
    Dao<ProductQuotaObject> productQuotaDao = Dao.create(ProductQuotaObject.class, DatabaseApi.database);
    return productQuotaDao.getMultiple(sql, null);
  }

  public static List<ProductQuotaAndD3Model> getAllDetail() {
    String sql ="select pqouter.Id as productQuotaId, du.IdName as d3Name, du.IdNumber as d3IdNumber, "
        + "    p.Name as productName, pqouter.Quota as quota "
        + "from ProductQuotaObjects as pqouter "
        + "join ProductObjects as p on pqouter.ProductId = p.Id "
        + "join DealerUserObjects as du on pqouter.D3Id = du.Id "
        + "where du.DeletedOn is null and pqouter.DeletedOn is null "
        + "    and not exists (select * "
        + "                    from ProductQuotaObjects as pqinner "
        + "                    where pqinner.ProductId = pqouter.ProductId "
        + "                        and pqinner.D3Id = pqouter.D3Id "
        + "                        and pqinner.DeletedOn is null "
        + "                        and pqinner.LastModified > pqouter.LastModified)";
    Dao<ProductQuotaAndD3Model> productQuotaDao = Dao.create(ProductQuotaAndD3Model.class, DatabaseApi.database);
    return productQuotaDao.getMultiple(sql, null);
  }

  public static boolean exists(String productId, String d3Id) {
    String sql = "select count(*) from ProductQuotaObjects "
        + "where ProductId = :productId and D3Id = :d3Id and DeletedOn is null";
    int result = DatabaseApi.querySingleIntegerOrDefault(
        sql, CollectionUtils.mapOf("productId", productId, "d3Id", d3Id), -1);
    return result > 0;
  }

  public static boolean add(String productId, String d3Id, int quota) {
    String sql = "insert into ProductQuotaObjects "
        + "(ProductId, D3Id, Quota, DateAdded, LastModified) "
        + "values "
        + "(:productId, :d3Id, :quota, getdate(), getdate())";
    return DatabaseApi.updateTable(
        sql, CollectionUtils.mapOf("productId", productId, "d3Id", d3Id, "quota", quota)) == 1;
  }

  public static boolean softDelete(String id) {
    String sql = "update ProductQuotaObjects "
        + "set DeletedOn = getdate() "
        + "where Id = :id";
    return DatabaseApi.updateTable(sql, CollectionUtils.mapOf("id", id)) == 1;
  }

}
