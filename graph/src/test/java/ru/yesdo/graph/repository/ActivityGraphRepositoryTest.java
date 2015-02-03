package ru.yesdo.graph.repository;

import org.junit.Test;
import ru.yesdo.graph.config.Neo4jConfigurationTest;
import ru.yesdo.model.Activity;

import java.util.Set;

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

        System.out.println(foundActivityByTitle.getId());

    }

    @Test
    public void testCreateParentActivity() {
        String titleParent = "activityParent";
        Activity activityParent = new Activity();
        activityParent.setTitle(titleParent);
        activityGraphRepository.save(activityParent);
        assertNotNull(activityParent.getId());

        String titleParent2 = "activityParent2";
        Activity activityParent2 = new Activity();
        activityParent2.setTitle(titleParent2);
        activityGraphRepository.save(activityParent2);
        assertNotNull(activityParent2.getId());

        String titleParent3 = "activityParent3";
        Activity activityParent3 = new Activity();
        activityParent3.setTitle(titleParent3);
        activityGraphRepository.save(activityParent3);
        assertNotNull(activityParent3.getId());

        String titleChild1 = "activityChild1";
        Activity activityChild1 = new Activity();
        activityChild1.setTitle(titleChild1);
        activityGraphRepository.save(activityChild1);
        assertNotNull(activityChild1.getId());

        String titleChild2 = "activityChild2";
        Activity activityChild2 = new Activity();
        activityChild2.setTitle(titleChild2);
        activityGraphRepository.save(activityChild2);
        assertNotNull(activityChild2.getId());

        activityParent.addChildren(activityChild1);
        activityParent.addChildren(activityChild2);

        activityGraphRepository.save(activityParent);
        Activity foundParent = activityGraphRepository.findByTitle(activityParent.getTitle());
        assertNotNull(foundParent);
        Set<Activity> child = foundParent.getChild();
        assertTrue(child.size() > 0 );
        for (Activity activity : child) {
            activity = activityGraphRepository.findOne(activity.getId());
            System.out.println(activity.getTitle());
            assertNotNull(activity.getTitle());
        }


    }
}
