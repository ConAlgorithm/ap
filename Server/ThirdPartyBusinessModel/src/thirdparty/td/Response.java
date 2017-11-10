package thirdparty.td;

import java.util.ArrayList;
import java.util.List;

public class Response {
  private String rawResonse;
  private boolean success;
  private String reason_code;
  private String final_decision;
  private List<Rule> hit_rules = new ArrayList<>();
  private String seq_id;

  public String getRawResponse() {
    return rawResonse;
  }

  public void setRawResponse(String rawResponse) {
    this.rawResonse = rawResponse;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getReason_code() {
    return reason_code;
  }

  public void setReason_code(String reason_code) {
    this.reason_code = reason_code;
  }

  public String getFinal_decision() {
    return final_decision;
  }

  public void setFinal_decision(String final_decision) {
    this.final_decision = final_decision;
  }

  public List<Rule> getHit_rules() {
    return hit_rules;
  }


  public void setHit_rules(List<Rule> hit_rules) {
    this.hit_rules = hit_rules;
  }

  public String getSeq_id() {
    return seq_id;
  }

  public void setSeq_id(String seq_id) {
    this.seq_id = seq_id;
  }

}
