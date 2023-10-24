import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {
    private List<Activity> activities = new ArrayList<>();
    private long nextId = 1;

    @GetMapping("/")
    public List<Activity> getAllActivities() {
        return activities;
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity) {
        activity.setId(nextId++);
        activities.add(activity);
        return activity;
    }

    @PutMapping("/{activityId}")
    public Activity updateActivity(@PathVariable Long activityId, @RequestBody Activity updatedActivity) {
        for (Activity activity : activities) {
            if (activity.getId().equals(activityId)) {
                activity.setName(updatedActivity.getName());
                activity.setStatus(updatedActivity.isStatus());
                return activity;
            }
        }
        return null;
    }

    @DeleteMapping("/{activityId}")
    public void deleteActivity(@PathVariable Long activityId) {
        activities.removeIf(activity -> activity.getId().equals(activityId));
    }
}