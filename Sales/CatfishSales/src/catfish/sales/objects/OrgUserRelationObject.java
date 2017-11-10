package catfish.sales.objects;

import java.util.Date;

public class OrgUserRelationObject extends BaseDataObject {
	// Affiliate User
    public String AffiliateId ;
    // 当前 Org 节点
    public String OrgNodeId ;
    // 是否已删除
    public Date DeletedOn ;
}
