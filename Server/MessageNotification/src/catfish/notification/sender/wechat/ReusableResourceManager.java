package catfish.notification.sender.wechat;

import java.util.Calendar;
import java.util.Date;

public abstract class ReusableResourceManager {

  protected String name;         // for logging
  protected String identifier;
  protected Date expiration = new Date();

  public ReusableResourceManager(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public synchronized String getIdentifier() {
    if (isIdentifierExpired()) {
      update();
    }
    return identifier;
  }

  public abstract void update();   // should be synchronized

  protected abstract int getExpirationInMinutes();

  protected void updateExpirationDate() {
    updateExpirationDate(new Date());
  }
  protected void updateExpirationDate(Date generatedDate) {
    expiration = addMinutes(generatedDate, getExpirationInMinutes());
  }

  protected static Date addMinutes(Date base, int minutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(base);
    calendar.add(Calendar.MINUTE, minutes);
    return calendar.getTime();
  }

  protected boolean isIdentifierExpired() {
    return expiration.before(new Date());
  }
}
