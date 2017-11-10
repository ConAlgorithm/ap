package im.catfish.captcha.algorithm;

import im.catfish.captcha.CaptchaConstant;
import im.catfish.captcha.object.CharXPos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Preprocessor {

  public static List<CharXPos> Split(BufferedImage biImg) {
    int width = biImg.getWidth();
    int height = biImg.getHeight();
    Map<Integer, Integer> colToBlackCountMap = new LinkedHashMap<Integer, Integer>();

    for (int x = 0; x < width; x++) {
      int blackCount = 0;
      for (int y = 0; y < height; y++) {
        if (ImgUtil.isCloseBlack(biImg.getRGB(x, y))) {
          blackCount++;
        }
      }
      colToBlackCountMap.put(x, blackCount);
    }

    return splitByWhiteCol(colToBlackCountMap);
  }

  private static List<CharXPos> splitByWhiteCol(Map<Integer, Integer> map) {
    List<CharXPos> chars = new LinkedList<CharXPos>();
    int preBlackCount = 0;
    int start = 0;
    int end = 0;
    for (Entry<Integer, Integer> entry : map.entrySet()) {
      if (preBlackCount == 0 && entry.getValue() > 0) {
        start = entry.getKey();
      }
      else if (preBlackCount > 0 && entry.getValue() == 0 && start < entry.getKey() - 1) {
        end = entry.getKey() - 1;
        chars.add(new CharXPos(start, end));
      }
      preBlackCount = entry.getValue();
    }
    return chars;
  }

  public static BufferedImage getMeanValueFilteredImage(BufferedImage img) {
    int mean = ImgUtil.getMeanColor(img);
    int width = img.getWidth();
    int height = img.getHeight();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (ImgUtil.getRgbSum(img.getRGB(x, y)) >= mean * CaptchaConstant.MEAN_VALUE_FILTER_FACTOR) {
          img.setRGB(x, y, Color.WHITE.getRGB());
        }
        else {
          img.setRGB(x, y, Color.BLACK.getRGB());
        }
      }
    }
    return img;
  }

}
