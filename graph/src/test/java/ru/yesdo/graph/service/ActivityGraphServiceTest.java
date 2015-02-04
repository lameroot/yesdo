package ru.yesdo.graph.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.neo4j.repository.GraphRepository;
import ru.yesdo.graph.config.Neo4jConfigurationTest;
import ru.yesdo.graph.data.ActivityData;
import ru.yesdo.graph.repository.ActivityGraphRepository;
import ru.yesdo.model.Activity;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * Created by lameroot on 24.01.15.
 */
public class ActivityGraphServiceTest extends Neo4jConfigurationTest {

    @Resource
    private ActivityGraphService activityGraphService;
    @Resource
    private ActivityGraphRepository activityGraphRepository;

    //@Before
    public void clearAllGraphRepositories() {
        Map<String, GraphRepository> graphRepositories = applicationContext.getBeansOfType(GraphRepository.class);
        for (GraphRepository graphRepository : graphRepositories.values()) {
            graphRepository.deleteAll();
        }
    }

    protected Activity findActivityByTitle(String title) {
        return activityGraphRepository.findByTitle(title);
    }

    @Before
    public void clearActivityGraphRepositories() {
        activityGraphRepository.deleteAll();
    }

    @Test
    public void testFillData() {
        activityGraphService.fillTestData(3,3);
    }

    @Test
    public void testCreateRealActivity() {
        ActivityData activityDataRoot = new ActivityData().setTitle("activity_root");
        Activity activityRoot = activityGraphService.create(activityDataRoot);
        assertNotNull(activityRoot.getId());

        ActivityData activityDataLevel1Num1 = new ActivityData().setTitle("activity_level1_num1").addParent(activityRoot);
        Activity activityLevel1Num1 = activityGraphService.create(activityDataLevel1Num1);
        assertNotNull(activityLevel1Num1.getId());

        ActivityData activityDataLevel1Num2 = new ActivityData().setTitle("activity_level1_num2").addParent(activityRoot);
        Activity activityLevel1Num2 = activityGraphService.create(activityDataLevel1Num2);
        assertNotNull(activityLevel1Num2.getId());

        ActivityData activityDataLevel1Num3 = new ActivityData().setTitle("activity_level1_num3").addParent(activityRoot);
        Activity activityLevel1Num3 = activityGraphService.create(activityDataLevel1Num3);
        assertNotNull(activityLevel1Num3.getId());

        ActivityData activityDataLevel2Num1 = new ActivityData().setTitle("activity_level2_num1").addParent(activityLevel1Num1).addParent(activityLevel1Num2);
        Activity activityLevel2Num1 = activityGraphService.create(activityDataLevel2Num1);
        assertNotNull(activityLevel2Num1.getId());

        ActivityData activityDataLevel2Num2 = new ActivityData().setTitle("activity_level2_num2").addParent(activityLevel1Num2).addParent(activityLevel1Num3);
        Activity activityLevel2Num2 = activityGraphService.create(activityDataLevel2Num2);
        assertNotNull(activityLevel2Num2.getId());

        ActivityData activityDataLevel3Num1 = new ActivityData().setTitle("activity_level3_num1").addParent(activityLevel2Num1);
        Activity activityLevel3Num1 = activityGraphService.create(activityDataLevel3Num1);
        assertNotNull(activityLevel3Num1.getId());

        ActivityData activityDataLevel3Num2 = new ActivityData().setTitle("activity_level3_num2").addParent(activityLevel2Num2);
        Activity activityLevel3Num2 = activityGraphService.create(activityDataLevel3Num2);
        assertNotNull(activityLevel3Num2.getId());

    }

    @Test
    public void testCreateActivity() {
        ActivityData activityData1 = new ActivityData().setTitle("activity-parent1");
        Activity activity1 = activityGraphService.create(activityData1);
        assertNotNull(activity1);
        assertNotNull(activity1.getId());

        ActivityData activityData2 = new ActivityData().setTitle("activity-parent2");
        Activity activity2 = activityGraphService.create(activityData2);

        ActivityData activityData3 = new ActivityData().setTitle("activity-parent3");
        Activity activity3 = activityGraphService.create(activityData3);

        //---------//
        ActivityData activityDataLevel1Num1 = new ActivityData()
                .setTitle("activity-level1-num1")
                .addParent(activity1).addParent(activity2);
        Activity activityLevel1Num1 = activityGraphService.create(activityDataLevel1Num1);
        assertNotNull(activityLevel1Num1.getId());
        assertTrue(activityLevel1Num1.getParents().size() == 2);

        ActivityData activityDataLevel1Num2 = new ActivityData()
                .setTitle("activity-level1-num2")
                .addParent(activity2).addParent(activity3);
        Activity activityLevel1Num2 = activityGraphService.create(activityDataLevel1Num2);
        assertNotNull(activityLevel1Num2);

        //--------//
        ActivityData activityDataLevel2Num1 = new ActivityData()
                .setTitle("activity-level2-num1")
                .addParent(activityLevel1Num1);
        Activity activityLevel2Num1 = activityGraphService.create(activityDataLevel2Num1);
        assertNotNull(activityLevel2Num1);

        ActivityData activityDataLevel2Num2 = new ActivityData()
                .setTitle("activity-level2-num2")
                .addParent(activityLevel1Num2);
        Activity activityLevel2Num2 = activityGraphService.create(activityDataLevel2Num2);
        assertNotNull(activityLevel2Num2);


        //---------//
        Activity foundActivity1 = activityGraphRepository.findByTitle(activityLevel2Num1.getTitle());
        assertNotNull(foundActivity1);
        assertTrue(foundActivity1.getParents().size() == 3);
        neo4jTemplate.fetch(foundActivity1.getParents());
        assertTrue(contains(foundActivity1.getParents(),activityLevel1Num1));
        assertTrue(contains(foundActivity1.getParents(),activity1));
        assertTrue(contains(foundActivity1.getParents(),activity2));


        Activity foundActivity2 = activityGraphRepository.findByTitle(activity1.getTitle());
        assertNotNull(foundActivity2);
        assertTrue(foundActivity2.getChild().size() == 2);
    }

    protected boolean contains(Collection<Activity> activities, Activity activity) {
        return activities.stream().filter(a -> a.getTitle().equals(activity.getTitle())).findFirst().isPresent();

    }
}