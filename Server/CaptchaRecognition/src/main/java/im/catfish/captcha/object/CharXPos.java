package im.catfish.captcha.object;

public class CharXPos {

  public int start;
  public int end;

  public CharXPos(int start, int end) {
    super();
    this.start = start;
    this.end = end;
  }

  public int length() {
    return end - start + 1;
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof CharXPos)) {
      return false;
    }
    if (this.start == ((CharXPos)object).start && this.end == ((CharXPos)object).end) {
      return true;
    }
    return false;
  }
}
