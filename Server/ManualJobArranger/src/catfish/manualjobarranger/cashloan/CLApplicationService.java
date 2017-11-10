package catfish.manualjobarranger.cashloan;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import catfish.base.StartupConfig;
import catfish.base.httpclient.HttpClientApi;

public class CLApplicationService {

  private static final String COWFISH_HOST = StartupConfig.get("cowfish.rest.host");

  @SuppressWarnings("serial")
  public static List<CLApplicationSubmittedModel> getApplicationSubmittedDates(List<String> appIds) {
    StringBuilder sb = new StringBuilder();
    for (String id : appIds) {
      sb.append("appIds=" + id + "&");
    }
    String jsonData = HttpClientApi.get(
        String.format("%s/application/submitDate?", COWFISH_HOST, sb.toString()));
    if (jsonData == null) {
      return null;
    }

    return getLongDateGson().fromJson(
        jsonData, new TypeToken<List<CLApplicationSubmittedModel>>() {}.getType());
  }

  public static Gson getLongDateGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
      @Override
      public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
         return new Date(json.getAsJsonPrimitive().getAsLong());
      }
    });
    return builder.create();
  }
}
