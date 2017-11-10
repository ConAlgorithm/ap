package im.catfish.captcha.object;

public class CharFeature {

  public int totalBlackPixels;
  public double centerX;
  public double centerY;
  public int pixelWidth;

  public CharFeature(int totalBlackPixels, double centerX, double centerY, int pixelWidth) {
    super();
    this.totalBlackPixels = totalBlackPixels;
    this.centerX = centerX;
    this.centerY = centerY;
    this.pixelWidth = pixelWidth;
  }
}
