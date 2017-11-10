package jma.handlers;

import java.util.ArrayList;
import java.util.List;

import catfish.base.business.util.AppDerivativeVariable;
import catfish.base.business.util.AppDerivativeVariableNames;

public class CreditReferenceQuestionsAnalysisV2 {
  
  public static List<AppDerivativeVariable> Analyze(String appId, String jsonQuestions) {
    List<AppDerivativeVariable> results = new ArrayList<>();

    try {
      FurtherHandle handler = new FurtherHandle(jsonQuestions);
      handler.furtherGo();

      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_CARD_NUMBER, handler.getCreditCardNumber()));
      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_CARD_MAX_AMOUNT, handler.getCreditCardMaxAmount()));
      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_CARD_AVERAGE_AMOUNT, handler.getCreditCardAverageAmount()));
      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_CARD_PREVIOUS_TIME, handler.getCreditCardPreviousTime()));
      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_CARD_LATER_TIME, handler.getCreditCardLaterTime()));
      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_LOAN_NUMBER, handler.getLoanNumber()));
      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_LOAN_MAX_AMOUNT, handler.getLoanMaxAmount()));
      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_LOAN_AVERAGE_AMOUNT, handler.getLoanAverageAmount()));
      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_LOAN_PREVIOUS_TIME, handler.getLoanPreviousTime()));
      results.add(new AppDerivativeVariable(
          appId, AppDerivativeVariableNames.CREDIT_LOAN_LATER_TIME, handler.getLoanLaterTime()));
      
    } catch (Exception e) {
      results.clear();
    }

    return results;
  }
}
