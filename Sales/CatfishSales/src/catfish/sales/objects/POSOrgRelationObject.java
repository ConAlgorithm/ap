package catfish.sales.objects;

import java.util.Date;

public class POSOrgRelationObject extends BaseDataObject {
	public String POSId ;
    // 挂接OrgID
    public String OrgNodeId ;
    // 是否已删除
    public Date DeletedOn ;
}
