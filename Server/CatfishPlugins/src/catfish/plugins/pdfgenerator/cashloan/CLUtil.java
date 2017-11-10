package catfish.plugins.pdfgenerator.cashloan;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import javax.imageio.ImageIO;

import catfish.base.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.itextpdf.text.pdf.codec.Base64;

public class CLUtil {

  private static String chineseNumber[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };

  public static String getImgBaset64FromFile(String filepath) {
    File file = new File(filepath);
    try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      BufferedImage bufimg = ImageIO.read(file);
      ImageIO.write(bufimg, "gif", baos);
      byte[] bytes = baos.toByteArray();
      return Base64.encodeBytes(bytes);
    } catch (IOException e) {
      Logger.get().error(e);
    }
    return "";
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

  public static String changeNumber2Chinese(double value) {
    int number = (int)value;
    double decimal = value - number + 0.001;
    int num[] = new int[20];
    for (int i=0; i < num.length; i++)
        num[i] = 0;
    String s = "";
    int count = 0;
    while (number>0) {
        num[count++] = number%10;
        number /= 10;
    }
    for (int i=count-1; i>=0; i--) {
        switch(i) {
            case 0: s += num[i] == 0 ? "圆" : chineseNumber[num[i]] + "圆"; break;
            case 1: s += num[i] == 0 ? (s.endsWith("零") ? "" : "零") : chineseNumber[num[i]] + "拾"; break;
            case 2: s += num[i] == 0 ? (s.endsWith("零") ? "" : "零") : chineseNumber[num[i]] + "佰"; break;
            case 3: s += num[i] == 0 ? (s.endsWith("零") ? "" : "零") : chineseNumber[num[i]] + "仟"; break;
            case 4: s += num[i] == 0 ? "万" : chineseNumber[num[i]] + "万"; break;
            case 5: s += num[i] == 0 ? (s.endsWith("零") ? "" : "零") : chineseNumber[num[i]] + "拾"; break;
            case 6: s += chineseNumber[num[i]] + "佰"; break;
        }
    }
    s = s.replace("零圆", "圆");
    s = s.replace("零万", "万");

    int d = 0;
    if (decimal < 0.01) {
        return s;
    } else {
        d = (int)(decimal*10);
        decimal = decimal*10 - d + 0.001;
        if(d > 9) {
            s += chineseNumber[9]+"角";
        } else {
            s += chineseNumber[d]+"角";
        }
        d = (int)(decimal*10);
        if(d > 9) {
            s += chineseNumber[9]+"分";
        } else {
            s += chineseNumber[d]+"分";
        }
    }

    return s;
  }

}
