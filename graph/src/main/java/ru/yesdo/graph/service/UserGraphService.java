package ru.yesdo.graph.service;

import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import ru.yesdo.graph.data.UserData;
import ru.yesdo.graph.repository.MerchantGraphRepository;
import ru.yesdo.graph.repository.UserGraphRepository;
import ru.yesdo.model.Offer;
import ru.yesdo.model.Rating;
import ru.yesdo.model.User;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Krainov
 * Date: 12.02.2015
 * Time: 18:12
 */
@Service
public class UserGraphService {

    @Resource
    private UserGraphRepository userGraphRepository;
    @Resource
    private MerchantGraphRepository merchantGraphRepository;
    @Resource
    private Neo4jTemplate neo4jTemplate;

    public User create(UserData userData) {
        User user = new User();
        user.setLogin(userData.getLogin());
        user.setMerchant(userData.getMerchant());
        userGraphRepository.save(user);

        return user;
    }

    public Rating rate(Offer offer, User user, int stars, String comment) {
        final Rating rating = neo4jTemplate.createRelationshipBetween(user, offer, Rating.class, "RATED", false);
        rating.rate(stars, comment);
        return neo4jTemplate.save(rating);
    }
}