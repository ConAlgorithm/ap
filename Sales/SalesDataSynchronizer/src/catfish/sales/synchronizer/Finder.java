package catfish.sales.synchronizer;

import java.util.Date;
import catfish.base.CollectionUtils;
import catfish.base.business.object.AttachmentObject;
import catfish.base.business.object.BaseObject;
import catfish.base.business.object.ContactObject;
import catfish.base.business.object.MerchantCompanyObject;
import catfish.base.business.object.MerchantStoreObject;
import catfish.base.business.object.MerchantUserObject;
import catfish.base.business.object.PaymentObject;
import catfish.base.business.object.UserObject;
import catfish.base.database.Database;
import catfish.framework.database.IDatabaseService;
import catfish.sales.objects.*;

public class Finder {
    private Worker worker;
    
    public Finder(IDatabaseService dbService){
        Database sqlserver = dbService.getDatabase("sqlserver");
        Database mysql = dbService.getDatabase("mysql");
        worker = new Worker(sqlserver, mysql, "Id");
    }
    
    public static DatasetSynchronizer[] list = {
			new DatasetSynchronizer(AttachmentObject.class, CollectionUtils.<String, String>newMapBuilder()
					.add("MerchantUserAttachmentObjects", "AttachmentId")
					.add("MerchantCompanyAttachmentObjects", "AttachmentId")
					.add("PaymentObjects p join MerchantUserPaymentObjects mp on p.id=mp.paymentid", "AttachmentId")
					.add("MerchantStoreAttachmentObjects", "AttachmentId")
					.add("MerchantUserWithdrawApplicationObjects", "TransferVoucherPhotoAttachmentId")
					.build()),
	        new DatasetSynchronizer(ContactObject.class,CollectionUtils.<String, String>newMapBuilder()
	        		.add("UserObjects u where u.id in ((select userid from BusinessDevelopUserObjects) "
	        				+ "union (select userid from DealerUserObjects) "
	        				+ "union (select userid from MerchantUserObjects) "
	        				+ "union (select userid from MerchantUserTransactionObjects))","MobileContactId")
				    .add("MerchantUserContactObjects","ContactId")
					    .build()),
	        new DatasetSynchronizer(UserObject.class, CollectionUtils.<String, String>newMapBuilder()
	        		.add("BusinessDevelopUserObjects","UserId")
				    .add("DealerUserObjects","UserId")
				    .add("MerchantUserObjects","UserId")
				    .add("MerchantUserTransactionObjects","UserId")
				    .build()),
	        new DatasetSynchronizer(PaymentObject.class, CollectionUtils.<String, String>newMapBuilder()
	        		.add("MerchantUserPaymentObjects","PaymentId")
				    .build()),
	        //new DatasetSynchronizer(CNAreaObject.class, null),
			new DatasetSynchronizer(MerchantCompanyObject.class, null),
		    new DatasetSynchronizer(MerchantStoreObject.class, null),
	        new DatasetSynchronizer(MerchantUserObject.class, null),
	        
	        new DatasetSynchronizer(MerchantUserPaymentObject.class, null),
	        new DatasetSynchronizer(BDOrgNodeObject.class, null),
	        new DatasetSynchronizer(BDOrgBDUserRelationObject.class, null),
	        new DatasetSynchronizer(BDOrgRelationObject.class, null),
	        new DatasetSynchronizer(DOrgDUserRelationObject.class, null),
	        new DatasetSynchronizer(DOrgRelationObject.class, null),
	        new DatasetSynchronizer(DOrgNodeObject.class, null),
	        new DatasetSynchronizer(POSBDOrgRelationObject.class, null),
	        new DatasetSynchronizer(POSDOrgRelationObject.class, null),
	        
	        new DatasetSynchronizer(BusinessDevelopUserObject.class, null),
	        new DatasetSynchronizer(DealerUserObject.class, null),
	        new DatasetSynchronizer(DealerObject.class, null),
	        new DatasetSynchronizer(CommissionListObject.class, null),
	        new DatasetSynchronizer(CommissionDetailObject.class, null),
	        new DatasetSynchronizer(SellerCommissionObject.class, null),
	        new DatasetSynchronizer(MerchantStoreAttachmentObject.class, null),
	        new DatasetSynchronizer(POSS1RelationObject.class, null),
	        new DatasetSynchronizer(POSS2RelationObject.class, null),
	        new DatasetSynchronizer(SellerPOSRelationObject.class, null),
	        new DatasetSynchronizer(SellerS3RelationObject.class, null),
	        new DatasetSynchronizer(SalesDistrictObject.class, null),
	        new DatasetSynchronizer(DistrictEntityRelationObject.class, null),
	        new DatasetSynchronizer(DistrictRelationObject.class, null),
	        new DatasetSynchronizer(ActivityWeixinRedPackObject.class, null),
	        new DatasetSynchronizer(ActivityWeixinRedPackRecordObject.class, null),
	        new DatasetSynchronizer(MerchantUserWithdrawApplicationObject.class, null),
	        new DatasetSynchronizer(MerchantUserTransactionObject.class, null),
	        new DatasetSynchronizer(MerchantUserContactObject.class, null),
	        new DatasetSynchronizer(POSTagObject.class, null),
	        new DatasetSynchronizer(CollectionNoteObject.class, null),
	        new DatasetSynchronizer(POSTagRelationObject.class, null),
	        new DatasetSynchronizer(BonusPointsRuleObject.class, null),
	        new DatasetSynchronizer(BonusPointsObject.class, null),
	        new DatasetSynchronizer(BonusPointsRankObject.class, null),
	        new DatasetSynchronizer(AffiliateOperationObject.class, null),
	        new DatasetSynchronizer(EnumTypeObject.class, null),
	        new DatasetSynchronizer(EnumValueObject.class, null),
	        new DatasetSynchronizer(MerchantCompanyAttachmentObject.class, null),
	        new DatasetSynchronizer(MerchantUserAttachmentObject.class, null),
	};
    
    

	public void doFind(String[] tables){
        String timeStamp = "2014-01-01 11:32:04.380";
        Date currentTime=new Date();
        for(DatasetSynchronizer item: list){
        	String objType=item.objectType.toString();
        	if(objType != null){
    			String[] items = objType.split("\\.");
    			objType = items[items.length-1] ;
    		}

        	if(!tables[0].equals("InitializeSalesData")){
	        	for(String table:tables){
	        		if(table.equals(objType)){
	        			worker.run((Class<BaseObject>)item.objectType,timeStamp,item.map,currentTime);
	        			break;
	        		}
	        	}
	        	continue;
        	}
        }
        
        
    }
    
    public void initializeData(){
        String timeStamp = "2014-01-01 11:32:04.380";
        Date currentTime=new Date();
        for(DatasetSynchronizer item: list){
        	String objType=item.objectType.toString();
        	if(objType != null){
    			String[] items = objType.split("\\.");
    			objType = items[items.length-1] ;
    		}
        	worker.run((Class<BaseObject>)item.objectType,timeStamp,item.map,currentTime);
        }
    }
}
