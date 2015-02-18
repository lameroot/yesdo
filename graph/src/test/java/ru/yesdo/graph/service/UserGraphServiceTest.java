package ru.yesdo.graph.service;


import org.junit.Test;

import ru.yesdo.model.data.UserData;
import ru.yesdo.graph.repository.ContactGraphRepository;
import ru.yesdo.graph.repository.UserGraphRepository;
import ru.yesdo.model.Merchant;
import ru.yesdo.model.Offer;
import ru.yesdo.model.Rating;
import ru.yesdo.model.User;

import javax.annotation.Resource;

/**
 * User: Krainov
 * Date: 12.02.2015
 * Time: 18:15
 */
public class UserGraphServiceTest extends ProductGraphServiceTest {

    @Resource
    private UserGraphService userGraphService;
    @Resource
    private UserGraphRepository userGraphRepository;
    @Resource
    private ContactGraphRepository contactGraphRepository;

//    @Before
//    @Neo4jTransactional
    public void clearUsers() {
        //Neo4jHelper.cleanDb(graphDatabaseService);
        //userGraphRepository.deleteAll();
    }

    private User create(String login, String merchantName) {

        Merchant merchant = findMerchantByName(merchantName);
        assertNotNull(merchant);
        UserData userData = new UserData().setLogin(login).setMerchant(merchant);
        User user = userGraphService.create(userData);
        assertNotNull(user);
        assertNotNull(user.getId());

        return user;
    }

    @Test
    public void testCreateUsers() {
        testCreateProducts();
        User u11 = create("u11","m11");
        User u12 = create("u12","m11");
        User u13 = create("u13","m12");
        User u14 = create("u14","m12");

        Merchant m11 = findMerchantByName("m11");
        int i = 1;
        for (Offer offer : m11.getOffers()) {
            Rating rating = userGraphService.rate(offer, u11, i, "this is comment: " + i);
            i++;
        }
        Merchant m12 = findMerchantByName("m11");
        for (Offer offer : m12.getOffers()) {
            Rating rating = userGraphService.rate(offer, u12, i, "this is 2comment: " + i);
            i++;
        }

    }
}
