package ru.yesdo.graph.service;

import org.junit.Before;
import org.junit.Test;
import ru.yesdo.graph.data.MerchantData;
import ru.yesdo.graph.data.ProductData;
import ru.yesdo.graph.repository.ActivityGraphRepository;
import ru.yesdo.graph.repository.MerchantGraphRepository;
import ru.yesdo.model.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

/**
 * Created by lameroot on 25.01.15.
 */
public class MerchantGraphServiceTest extends ActivityGraphServiceTest {

    @Resource
    private MerchantGraphRepository merchantGraphRepository;
    @Resource
    private ActivityGraphRepository activityGraphRepository;
    @Resource
    protected MerchantGraphService merchantGraphService;

    @Before
    public void clearMerchantGraphRepositories() {
        activityGraphRepository.deleteAll();
        merchantGraphRepository.deleteAll();
    }

    private Merchant create(String title, String... activityTitles) {
        MerchantData merchantData = new MerchantData().setName(title).setTitle(title);
        for (String activityTitle : activityTitles) {
            Activity activity = findActivityByTitle(activityTitle);
            assertNotNull(activity);
            merchantData.addActivity(activity);
        }
        Merchant merchant = merchantGraphService.create(merchantData);
        assertNotNull(merchant.getId());
        return merchant;
    }

    @Test
    public void testCreateMerchants() {
        super.testCreateActivities();
        Merchant m11 = create("m11","a11");
        Merchant m12 = create("m12","a13");
        Merchant m21 = create("m21","a21","a22");
        Merchant m31 = create("m31","a31");
        Merchant m32 = create("m32","a31");
        Merchant m33 = create("m33","a32");
        Merchant m34 = create("m34","a32");
    }

    protected Merchant findMerchantByName(String title) {
        return merchantGraphRepository.findByName(title);
    }

    @Test
    public void testCreateRealMerchant() {
        super.testCreateRealActivity();

        MerchantData merchantDataLevel2Num1 = new MerchantData().setTitle("merchant_level2_num1").addActivity(findActivityByTitle("activity_level2_num1"));
        Merchant merchantLevel2Num1 = merchantGraphService.create(merchantDataLevel2Num1);
        assertNotNull(merchantLevel2Num1.getId());

        MerchantData merchantDataLevel2Num2 = new MerchantData().setTitle("merchant_level2_num2")
                .addActivity(findActivityByTitle("activity_level2_num1")).addActivity(findActivityByTitle("activity_level2_num2"));
        Merchant merchantLevel2Num2 = merchantGraphService.create(merchantDataLevel2Num2);
        assertNotNull(merchantLevel2Num2.getId());

        MerchantData merchantDataLevel3Num1 = new MerchantData().setTitle("merchant_level3_num1")
                .addActivity(findActivityByTitle("activity_level3_num1")).addActivity(findActivityByTitle("activity_level3_num2"));
        Merchant merchantLevel3Num1 = merchantGraphService.create(merchantDataLevel3Num1);
        assertNotNull(merchantLevel3Num1.getId());

    }

    @Test
    public void testCreateMerchants1() {
        testCreateActivity();

        Activity activityLevel0Num1 = activityGraphRepository.findByTitle("activity-parent1");
        assertNotNull(activityLevel0Num1);
        Activity activityLevel0Num2 = activityGraphRepository.findByTitle("activity-parent2");
        assertNotNull(activityLevel0Num2);
        Activity activityLevel0Num3 = activityGraphRepository.findByTitle("activity-parent3");
        assertNotNull(activityLevel0Num3);

        Activity activityLevel1Num1 = activityGraphRepository.findByTitle("activity-level1-num1");
        assertNotNull(activityLevel1Num1);
        Activity activityLevel1Num2 = activityGraphRepository.findByTitle("activity-level1-num2");
        assertNotNull(activityLevel1Num2);
        Activity activityLevel2Num1 = activityGraphRepository.findByTitle("activity-level2-num1");
        assertNotNull(activityLevel2Num1);
        Activity activityLevel2Num2 = activityGraphRepository.findByTitle("activity-level2-num2");
        assertNotNull(activityLevel2Num2);



        MerchantData merchantData1 = new MerchantData()
                .setName("merchant1-level1")
                .setTitle("merchant1-level1-title")
                .addActivity(activityLevel1Num1);
        Merchant merchant1 = merchantGraphService.create(merchantData1);
        assertNotNull(merchant1);

        MerchantData merchantData2 = new MerchantData()
                .setName("merchant2-level1")
                .setTitle("merchant2-level1-title")
                .addActivity(activityLevel1Num1)
                .addActivity(activityLevel1Num2);
        Merchant merchant2 = merchantGraphService.create(merchantData2);
        assertNotNull(merchant2);

        MerchantData merchantData3 = new MerchantData()
                .setName("merchant1-level2")
                .setTitle("merchant1-level2-title")
                .addActivity(activityLevel2Num1)
                .addActivity(activityLevel2Num2);
        Merchant merchant3 = merchantGraphService.create(merchantData3);
        assertNotNull(merchant3);

        //-----------//
        Merchant foundMerchant1 = merchantGraphRepository.findByName(merchant1.getName());
        assertNotNull(foundMerchant1.getId());
        assertTrue(foundMerchant1.getActivities().size() == 3);
        neo4jTemplate.fetch(foundMerchant1.getActivities());
        contains(foundMerchant1.getActivities(),activityLevel1Num1);
        contains(foundMerchant1.getActivities(),activityLevel0Num1);
        contains(foundMerchant1.getActivities(),activityLevel0Num2);

        Merchant foundMerchant2 = merchantGraphRepository.findByName(merchant2.getName());
        assertNotNull(foundMerchant2.getId());
        assertTrue(foundMerchant2.getActivities().size() == 5);
        neo4jTemplate.fetch(foundMerchant2.getActivities());
        contains(foundMerchant2.getActivities(),activityLevel0Num1);
        contains(foundMerchant2.getActivities(),activityLevel0Num2);
        contains(foundMerchant2.getActivities(),activityLevel0Num3);
        contains(foundMerchant2.getActivities(),activityLevel1Num1);
        contains(foundMerchant2.getActivities(),activityLevel1Num2);


        Merchant foundMerchant3 = merchantGraphRepository.findByName(merchant3.getName());
        assertNotNull(foundMerchant3.getId());
        neo4jTemplate.fetch(foundMerchant3.getActivities());
        assertTrue(foundMerchant3.getActivities().size() == 7);
        contains(foundMerchant3.getActivities(),activityLevel0Num1);
        contains(foundMerchant3.getActivities(),activityLevel0Num2);
        contains(foundMerchant3.getActivities(),activityLevel0Num3);
        contains(foundMerchant3.getActivities(),activityLevel1Num1);
        contains(foundMerchant3.getActivities(),activityLevel1Num2);
        contains(foundMerchant3.getActivities(),activityLevel2Num1);
        contains(foundMerchant3.getActivities(),activityLevel2Num2);

    }

}
