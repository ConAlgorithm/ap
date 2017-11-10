package scheduler;

public interface FollowingActivityCreator {
  Activity create(String baseJobResult);
}
