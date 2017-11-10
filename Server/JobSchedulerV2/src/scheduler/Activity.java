package scheduler;

public interface Activity {
  void setFinishedHandler(FinishedHandler handler);
  void trigger();
}
