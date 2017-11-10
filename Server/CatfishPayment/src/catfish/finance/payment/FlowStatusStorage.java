package catfish.finance.payment;

import catfish.base.StartupConfig;
import catfish.flowcontroller.storage.MongoDBStorage;

public class FlowStatusStorage extends MongoDBStorage{

    @Override
    protected String mongoCollectionName() {
         return String.format(
                 "%s_apppayment", StartupConfig.get("catfish.mongo.collectionprefix"));
    }

}
