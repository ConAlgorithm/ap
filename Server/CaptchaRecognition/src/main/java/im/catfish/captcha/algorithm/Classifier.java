package im.catfish.captcha.algorithm;

import im.catfish.captcha.CaptchaRecognitionFailException;
import im.catfish.captcha.CaptchaConstant;
import im.catfish.captcha.object.CharFeature;
import im.catfish.captcha.object.CharXPos;
import im.catfish.captcha.object.Pair;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

public class Classifier {

  private static final String[] TRAIN_DATA = { "3", "4", "5", "6", "7", "8", "9",
      "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "n", "p", "q", "r", "s", "t", "u", "v", "x", "y" };
  private BufferedImage captcha;
  private int captchaLength;
  private static Map<String, CharFeature> templates;

  public Classifier(BufferedImage captcha, int captchaLength) {
    this.captcha = captcha;
    this.captchaLength = captchaLength;
  }

  public Classifier initialize() throws CaptchaRecognitionFailException {
    if (templates != null) {
      return this;
    }

    try {
      templates = new HashMap<String, CharFeature>();
      for (String data : TRAIN_DATA) {
        InputStream is = getResource(CaptchaConstant.TRAINING_DATA_PATH + data + ".jpg");
        BufferedImage img = ImageIO.read(is);
        is.close();
        templates.put(data, ImgUtil.getCharFeatures(img));
      }
    }
    catch (Exception e) {
      throw new CaptchaRecognitionFailException();
    }
    return this;
  }

  public String getCaptchaByKnn() throws CaptchaRecognitionFailException {
    StringBuilder code = new StringBuilder();
    BufferedImage biImg = Preprocessor.getMeanValueFilteredImage(captcha);
    List<CharXPos> chars = Preprocessor.Split(biImg);
    for (String ch : increaseSizeToNumber(biImg, chars, captchaLength)) {
      code.append(ch);
    }
    return code.toString();
  }

  private List<String> increaseSizeToNumber(BufferedImage img, List<CharXPos> chars, int number)
      throws CaptchaRecognitionFailException {
    if (chars.size() > number) {
      throw new CaptchaRecognitionFailException();
    }

    List<String> captchaList = new ArrayList<String>();
    for (CharXPos ch : chars) {
      captchaList.addAll(matchChars(img.getSubimage(ch.start, 0, ch.length(), img.getHeight())));
    }
    if (captchaList.size() != number) {
      throw new CaptchaRecognitionFailException();
    }
    return captchaList;
  }

  private List<String> matchChars(BufferedImage img) {
    int imgWidth = img.getWidth();
    List<String> chars = new ArrayList<String>();
    Pair<String, Integer> charAndWidth = getFirstMostFitCharAndWidth(img);
    if (charAndWidth.getKey() == null || charAndWidth.getValue() == 0) {
      return chars;
    }

    chars.add(charAndWidth.getKey());
    if (imgWidth - charAndWidth.getValue() >= 2) {
      chars.addAll(matchChars(img.getSubimage(
          charAndWidth.getValue(), 0, imgWidth - charAndWidth.getValue(), img.getHeight())));
    }
    return chars;
  }

  private Pair<String, Integer> getFirstMostFitCharAndWidth(BufferedImage img) {
    String mostFitChar = null;
    int mostFitWidth = 0;
    double minDiff = Double.MAX_VALUE;
    for (Entry<String, CharFeature> entry : templates.entrySet()) {
      CharFeature cfTmp = entry.getValue();
      int width = cfTmp.pixelWidth;
      if (width > img.getWidth()) {
        continue;
      }
      BufferedImage subImg = img.getSubimage(0, 0, width, img.getHeight());
      CharFeature subImgCf = ImgUtil.getCharFeatures(subImg);
      double tempDiff = getCharDiff(cfTmp, subImgCf);
      if (minDiff > tempDiff) {
        minDiff = tempDiff;
        mostFitChar = entry.getKey();
        mostFitWidth = width;
      }
    }
    return new Pair<String, Integer>(mostFitChar, mostFitWidth);
  }

  private double getCharDiff(CharFeature cf1, CharFeature cf2) {
    return Math.abs(cf1.totalBlackPixels - cf2.totalBlackPixels)
        + Math.abs(cf1.centerX - cf2.centerX) * CaptchaConstant.CHAR_FEATURE_X_FACTOR
        + Math.abs(cf1.centerY - cf2.centerY) * CaptchaConstant.CHAR_FEATURE_Y_FACTOR;
  }

  private InputStream getResource(String data) {
    return this.getClass().getResourceAsStream(data);
  }

}
