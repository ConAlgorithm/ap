package im.catfish.captcha.algorithm;

import im.catfish.captcha.object.CharFeature;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImgUtil {

  public static final int CLOSE_BLACK = 100;
  public static final int CLOSE_WHITE = 600;

  public static boolean isCloseBlack(int colorInt) {
    Color color = new Color(colorInt);
    if (color.getRed() + color.getGreen() + color.getBlue() <= CLOSE_BLACK) {
        return true;
    }
    return false;
  }

  public static boolean isCloseWhite(int colorInt) {
    Color color = new Color(colorInt);
    if (color.getRed() + color.getGreen() + color.getBlue() > CLOSE_WHITE) {
        return true;
    }
    return false;
  }

  public static CharFeature getCharFeatures(BufferedImage img) {
    int count = 0;
    int sumX = 0;
    int sumY = 0;
    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        if (isCloseBlack(img.getRGB(x, y))) {
          count++;
          sumX += x;
          sumY += y;
        }
      }
    }
    return new CharFeature(count, (double)sumX / count, (double)sumY / count, img.getWidth());
  }

  public static int getMeanColor(BufferedImage img) {
    int sum = 0;
    int width = img.getWidth();
    int height = img.getHeight();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        sum += getRgbSum(img.getRGB(x, y));
      }
    }
    return sum / (width * height);
  }

  public static int getRgbSum(int rgb) {
    Color color = new Color(rgb);
    return color.getRed() + color.getGreen() + color.getBlue();
  }
}
