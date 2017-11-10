package jma.handlers;

import java.math.BigDecimal;
import java.util.Date;

import jma.AppDerivativeVariablesBuilder;
import jma.JobHandler;
import jma.RetryRequiredException;
import catfish.base.Logger;
import catfish.base.business.autosqlquery.AutoSqlQueryInstance;
import catfish.base.business.autosqlquery.AutoSqlQueryManager;
import catfish.base.business.common.SqlExecutionStage;
import catfish.base.business.util.AppDerivativeVariableManager;

public class ExecuteIdentityStageQueriesHandler extends JobHandler {

  @Override
  public void execute(String appId) throws RetryRequiredException {
    AppDerivativeVariablesBuilder builder = new AppDerivativeVariablesBuilder(appId);
    for (AutoSqlQueryInstance query
        : AutoSqlQueryManager.execute(SqlExecutionStage.IdentityInfoConfirmed, appId)) {
      if (query.isSuccessful()) {
        String variableName = query.getVariableName().substring(3);  // remove prefix "XA_";
        switch (query.getResultType()) {
        case Boolean:
          builder.add(variableName, (Boolean) query.getResult());
          break;
        case Integer:
          builder.add(variableName, (Integer) query.getResult());
          break;
        case String:
          builder.add(variableName, (String) query.getResult());
          break;
        case Decimal:
          builder.add(variableName, (BigDecimal) query.getResult());
          break;
        case DateTime:
          builder.add(variableName, (Date) query.getResult());
          break;
        default:
          break;
        }
      } else {
        Logger.get().error(
            String.format("ExecuteIdentityStageQueries faild ! SQL execution error: %s - %s", query.getVariableName(), query.getSql()),
            query.getException());
      }
    }
    AppDerivativeVariableManager.addVariables(builder.build());
  }
}
