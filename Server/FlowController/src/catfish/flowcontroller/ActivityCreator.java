package catfish.flowcontroller;

import java.util.Map;

import catfish.flowcontroller.activities.Activity;

public interface ActivityCreator {
    Activity create(Map<String, Object> activityConfig);
}
