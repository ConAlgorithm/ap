package risk.services.restfulobjects;

import com.google.gson.Gson;


public class RestfulResponse {
	private String status;
	private Integer warnActionType;
	private Content content;
	public final static Content InternalServerError = new Content(-10, "Internal Server Error.");

	private RestfulResponse(String status, Content content){
		this.status = status;
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getWarnActionType() {
	  return warnActionType;
	}

	public void setWarnActionType(Integer warnActionType) {
	  this.warnActionType = warnActionType;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

  public static RestfulResponse createSuccessfulResponse(){
    return new RestfulResponse("Success", null);
  }

  public static RestfulResponse createSuccessfulResponse(Integer warnActionType) {
    RestfulResponse response = new RestfulResponse("Success", null);
    response.setWarnActionType(warnActionType);
    return response;
  }

	public static RestfulResponse createSuccessfulResponse(String message){
		return new RestfulResponse("Success", new Content(0, message));
	}

	public static RestfulResponse createFailedResponse(int code, String message){
		return new RestfulResponse("Fail", new Content(code, message));
	}

	public static RestfulResponse createFailedResponse(Content content){
		return new RestfulResponse("Fail", content);
	}
}

class Content{
	private int code = 0;
	private String message;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Content(int code, String message){
		this.code = code;
		this.message = message;
	}
}