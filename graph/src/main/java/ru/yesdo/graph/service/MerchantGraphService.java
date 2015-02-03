package ru.yesdo.graph.service;

import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import ru.yesdo.graph.data.MerchantData;
import ru.yesdo.graph.data.ProductData;
import ru.yesdo.graph.repository.ActivityGraphRepository;
import ru.yesdo.graph.repository.MerchantGraphRepository;
import ru.yesdo.graph.repository.ProductGraphRepository;
import ru.yesdo.model.Activity;
import ru.yesdo.model.Merchant;
import ru.yesdo.model.Product;
import ru.yesdo.model.MerchantProductRelationship;

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
        merchant.getActivities().addAll(merchantData.getActivities());
        merchantGraphRepository.save(merchant);
        return merchant;
    }

    public void joinToMerchant(Merchant merchant,Product...products) {
        for (Product product : products) {
            MerchantProductRelationship merchantProductRelationship = merchant.addMerchantProduct(product,product.getAmount());
        }
        merchantGraphRepository.save(merchant);
    }

    public MerchantProductRelationship joinToMerchant(Merchant merchant,Product product) {
        MerchantProductRelationship merchantProductRelationship = merchant.addMerchantProduct(product, product.getAmount());//amount for test
        merchantGraphRepository.save(merchant);
        return merchantProductRelationship;
    }

    public void addProducts(Merchant merchant, ProductData... productDatas) {
        if ( null == productDatas || null == merchant ) throw new IllegalArgumentException("Merchant is null");

        merchant = merchantGraphRepository.findByMerchantLogin(merchant.getMerchantLogin());
        if ( null == merchant ) throw new IllegalArgumentException("Can't find merchant with login: " + merchant.getMerchantLogin());
        Set<Activity> activities = neo4jTemplate.fetch(merchant.getActivities());

        Set<Product> products = new HashSet<>();

        for (ProductData productData : productDatas) {
            Product product = new Product();
            product.setTitle(productData.getTitle());
            product.setAmount(productData.getAmount());
            product.setCreatedAt(productData.getCreatedAt());
            product.setLocation(productData.getLocation());
            product.setProductType(productData.getProductType());
            product.setTimeProduct(productData.getTimeProduct());
            product.setMerchant(merchant);
            for (Activity activity : activities) {
                //product.inActivity(activity);
            }
            products.add(productGraphRepository.save(product));

        }
        for (Product product : products) {
            MerchantProductRelationship relationship = merchant.addMerchantProduct(product, product.getAmount());
            neo4jTemplate.save(relationship);//todo: save relationship
        }
        merchantGraphRepository.save(merchant);
    }



    public Merchant joinToActivity(Merchant merchant, Activity activity) {
        //merchant.addActivity(activity);
        merchantGraphRepository.save(merchant);
        activityGraphRepository.save(activity);
        return merchant;
    }


    public void fillTestData(Activity activity, int count) {

            for (int i = 0; i < count; i++) {
            }



    }

    private Merchant createMerchant(Activity activity) {
        Merchant merchant = new Merchant();
        //merchant.addActivity(activity);
        merchantGraphRepository.save(merchant);
        return merchant;
    }

}
