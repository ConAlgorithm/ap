package catfish.plugins.sales.model;

public class ErrorResponseModel {

  public ErrorCodeAndMessageModel error;

  public ErrorResponseModel(String message, String errorCode) {
    super();
    this.error = new ErrorCodeAndMessageModel(message, errorCode);
  }

}

class ErrorCodeAndMessageModel {

  public String message;
  public String code;

  public ErrorCodeAndMessageModel(String message, String code) {
    super();
    this.message = message;
    this.code = code;
  }

}
