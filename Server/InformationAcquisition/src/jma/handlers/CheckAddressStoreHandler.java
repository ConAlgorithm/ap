package jma.handlers;

import catfish.base.CollectionUtils;
import catfish.base.database.DatabaseApi;

public class CheckAddressStoreHandler extends CheckAddressAbstractHandler {

  @Override
  protected String getAddress(String appId) {
    String sql =
        "SELECT M.Address " +
        "FROM MerchantStoreObjects AS M, InstallmentApplicationObjects AS I " +
        "WHERE I.Id = :AppId " +
        "    AND M.Id = I.MerchantStoreId";

    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }

  @Override
  protected String getCity(String appId) {
    return getStoreCity(appId);
  }

  @Override
  protected int getAddressType() {
    return STORE;
  }

  @Override
  protected String getRefId(String appId) {
    String sql =
        "SELECT MerchantStoreId " +
        "FROM InstallmentApplicationObjects " +
        "WHERE Id = :AppId";

    return DatabaseApi.querySingleString(sql, CollectionUtils.mapOf("AppId", appId));
  }
}
