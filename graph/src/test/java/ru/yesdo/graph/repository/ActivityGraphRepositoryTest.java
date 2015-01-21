package ru.yesdo.graph.repository;

import org.junit.Test;
import ru.yesdo.graph.config.Neo4jConfigurationTest;
import ru.yesdo.model.Activity;

/**
 * Created by lameroot on 22.01.15.
 */
public class ActivityGraphRepositoryTest extends Neo4jConfigurationTest {

    @Test
    public void testCreateActivity() {
        String title = "activity 1";
        Activity activity = new Activity();
        activity.setTitle(title);
        activityGraphRepository.save(activity);
        assertNotNull(activity.getId());

        Activity foundActivity = activityGraphRepository.findOne(activity.getId());
        assertNotNull(foundActivity);

        Activity foundActivityByTitle = activityGraphRepository.findBySchemaPropertyValue("title", title);
        assertNotNull(foundActivityByTitle);
        assertTrue(activity.getId().equals(foundActivityByTitle.getId()));

    }
}
