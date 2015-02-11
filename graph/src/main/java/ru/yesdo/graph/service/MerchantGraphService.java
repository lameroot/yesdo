package ru.yesdo.graph.service;

import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import ru.yesdo.graph.data.MerchantData;
import ru.yesdo.graph.data.OfferData;
import ru.yesdo.graph.data.ProductData;
import ru.yesdo.graph.repository.ActivityGraphRepository;
import ru.yesdo.graph.repository.MerchantGraphRepository;

import ru.yesdo.graph.repository.ProductGraphRepository;
import ru.yesdo.model.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lameroot on 25.01.15.
 */
@Service
public class MerchantGraphService {

    @Resource
    private MerchantGraphRepository merchantGraphRepository;
    @Resource
    private ActivityGraphRepository activityGraphRepository;
    @Resource
    private ProductGraphRepository productGraphRepository;

    @Resource
    private Neo4jTemplate neo4jTemplate;


    public Merchant create(MerchantData merchantData) {
        Merchant merchant = new Merchant(merchantData.getTitle());//test
        merchant.setTitle(merchantData.getTitle());
        merchant.setName(merchantData.getName());
        merchant.getActivities().addAll(merchantData.getActivities());
        merchantGraphRepository.save(merchant);
        return merchant;
    }

    public Merchant concludeOffer(Merchant merchant, Product product, OfferData offerData) {
        Merchant m = merchantGraphRepository.findOne(merchant.getId());
        Product p = productGraphRepository.findOne(product.getId());
        Offer o = offerData.toOffer();
        Offer o1 = offerData.toOffer();
        o1.setAmount(o1.getAmount() + 13);
        Offer newOffer = m.concludeOffer(p, o);
        Offer newOffer1 = m.concludeOffer(p, o1);
        neo4jTemplate.save(newOffer);
        neo4jTemplate.save(newOffer1);

//        Offer of1 = neo4jTemplate.createRelationshipBetween(m, p, Offer.class, "OFFER", true);
//        of1.setAmount(o.getAmount());
//        Offer of2 = neo4jTemplate.createRelationshipBetween(m, p, Offer.class, "OFFER1", true);
//        of2.setAmount(o1.getAmount());
//        neo4jTemplate.save(of1);
//        neo4jTemplate.save(of2);

        m.getOffers().add(newOffer);
        m.getOffers().add(newOffer1);

        System.out.println("----size = " + m.getOffers().size());

//        Offer oo = neo4jTemplate.createRelationshipBetween(m, p, Offer.class, "OFFER", false);
//        neo4jTemplate.save(oo);

        //merchantGraphRepository.save(m);

        return m;
    }

    public void joinToMerchant(Merchant merchant,Product...products) {
//        for (Product product : products) {
//            MerchantProductRelationship merchantProductRelationship = merchant.addMerchantProduct(product,product.getRelationship().getAmount());
//        }
//        merchantGraphRepository.save(merchant);
    }

    public MerchantProductRelationship joinToMerchant(Merchant merchant,Product product) {
//        MerchantProductRelationship merchantProductRelationship = merchant.addMerchantProduct(product, product.getRelationship().getAmount());//amount for test
//        merchantGraphRepository.save(merchant);
//        return merchantProductRelationship;
        return null;
    }

}
