package im.catfish.captcha;

import im.catfish.captcha.algorithm.Classifier;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Hello world!
 *
 */
public class App {

  public static void main( String[] args ) {
    try {
      String pic = "res/k377sd.jpg";
      BufferedImage originImg = ImageIO.read(new File(pic));
//      BufferedImage bgRomovedImg = removeBackgroud(pic);
//      ImageIO.write(bgRomovedImg, JPG, new File("res/srrdpc_background_removed.jpg"));
//      BufferedImage binaryImg = Preprocessor.getMeanValueFilteredImage(originImg);
//      ImageIO.write(binaryImg, JPG, new File("res/b94sfa_binary.jpg"));
//      List<CharXPos> chars = Preprocessor.Split(binaryImg);
//      for (CharXPos ch : chars) {
//        System.out.println("x:" + ch.start + ", y:" + ch.end);
//      }
      String result = new Classifier(originImg, CaptchaConstant.CHAR_NUMBER_OF_CAPTCHA)
          .initialize()
          .getCaptchaByKnn();
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
