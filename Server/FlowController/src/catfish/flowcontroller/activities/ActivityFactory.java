package catfish.flowcontroller.activities;

import java.util.Map;

public interface ActivityFactory {
    Activity create(Map<String, Object> activityConfig);
}
