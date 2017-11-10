package catfish.plugins.finance.object;

import com.google.gson.Gson;

public class JsonDataBuilder<TData> {

  public int status;

  public String msg;

  public TData data;

  public JsonDataBuilder(int status, String msg) {
    this.status = status;
    this.msg = msg;
  }

  public JsonDataBuilder(int status, String msg, TData data) {
    this.status = status;
    this.msg = msg;
    this.data = data;
  }

  public static <TData> JsonDataBuilder<TData> build(int status, String msg, TData data) {
    return new JsonDataBuilder<TData>(status, msg, data);
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

}
