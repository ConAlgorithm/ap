package catfish.plugins.sales.service;

import catfish.base.CollectionUtils;
import catfish.base.business.common.MerchantUserRole;
import catfish.base.database.DatabaseApi;

public class DealerUserDataService {

  public static String getD3IdByD1Id(String d1Id) {
    String sql = "select dodu3.AffiliateId "
        + "from DOrgNodeObjects as do1 "
        + "join DOrgDUserRelationObjects as dodu1 on do1.Id = dodu1.OrgNodeId "
        + "join DOrgRelationObjects as dor12 on dodu1.OrgNodeId = dor12.NodeId "
        + "join DOrgRelationObjects as dor23 on dor12.ParentId = dor23.NodeId "
        + "join DOrgDUserRelationObjects as dodu3 on dor23.ParentId = dodu3.OrgNodeId "
        + "join DOrgNodeObjects as do3 on dodu3.OrgNodeId = do3.Id "
        + "where dodu1.AffiliateId = :d1Id "
        + "    and do1.PostionRole = :roleD1 and do3.PostionRole = :roleD3 "
        + "    and dodu1.DeletedOn is null and dor12.DeletedOn is null "
        + "    and dor23.DeletedOn is null and dodu3.DeletedOn is null "
        + "order by dodu3.LastModified desc";
    return DatabaseApi.querySingleStringOrDefault(
        sql,
        CollectionUtils.mapOf(
            "d1Id", d1Id,
            "roleD1", MerchantUserRole.D1.getValue(),
            "roleD3", MerchantUserRole.D3.getValue()),
        null);
  }

}
