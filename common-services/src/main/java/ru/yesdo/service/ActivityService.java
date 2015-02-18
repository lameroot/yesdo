package ru.yesdo.service;

import org.springframework.stereotype.Service;
import ru.yesdo.graph.repository.ActivityGraphRepository;
import ru.yesdo.model.Activity;
import ru.yesdo.model.data.ActivityData;
import ru.yesdo.repository.ActivityRepository;

import javax.annotation.Resource;

/**
 * Created by lameroot on 18.02.15.
 */
@Service
public class ActivityService {

    @Resource
    private ActivityRepository activityRepository;
    @Resource
    private ActivityGraphRepository activityGraphRepository;

    public Activity create(ActivityData activityData) {
        Activity activity = new Activity();

        return activity;
    }

}
