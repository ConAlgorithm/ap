package catfish.plugins.sales.rest.handler;

import com.google.gson.Gson;

import catfish.plugins.sales.model.ErrorResponseModel;

public abstract class BaseHandler implements IHandler {

  private static final Gson GSON = new Gson();

  private String response;

  protected void setResponse(String response) {
    this.response = response;
  }

  protected <T> void setSuccessResponse(T model) {
    this.response = GSON.toJson(model);
  }

  protected void setErrorResponse(ErrorResponseModel error) {
    this.response = GSON.toJson(error);
  }

  protected void setErrorResponse(String message, String errorCode) {
    ErrorResponseModel model = new ErrorResponseModel(message, errorCode);
    this.response = GSON.toJson(model);
  }

  @Override
  public String getResponse() {
    return response;
  }

}
