package ru.yesdo.repository;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.data.jpa.repository.support.PersistenceProvider;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import ru.yesdo.GeneralTest;
import ru.yesdo.model.*;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lameroot on 08.02.15.
 */
public class GeneralTestRepository extends GeneralTest {

    @Resource
    protected ActivityRepository activityRepository;
    @Resource
    protected MerchantRepository merchantRepository;
    @Resource
    protected ProductRepository productRepository;
    @Resource
    protected OfferRepository offerRepository;
    @Resource
    protected UserRepository userRepository;
    @PersistenceContext
    protected EntityManager entityManager;
    @Resource
    protected PlatformTransactionManager platformTransactionManager;

    private Merchant findByName(String name) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        return transactionTemplate.execute(new TransactionCallback<Merchant>() {
            @Override
            public Merchant doInTransaction(TransactionStatus status) {
                return merchantRepository.findByName(name);
            }
        });
    }



    @Test
    public void testExists() {
        assertNotNull(merchantRepository);
        assertNotNull(productRepository);
        assertNotNull(offerRepository);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void testCreateMerchantWithProductAndOffers() {
        Merchant merchant = new Merchant();
        merchant.setName("merchant");
        merchant.setTitle("title-merchant");
        merchantRepository.save(merchant);
        assertNotNull(merchant.getId());

        User user = new User();
        user.setMerchant(merchant);
        user.setLogin("user");
        user.getPermissions().add(Permission.APPLICATION_ADMIN);
        userRepository.save(user);
        assertNotNull(user.getId());

        Product product = new Product();
        product.setMerchant(merchant);
        product.setCreatedAt(new Date());
        product.setTitle("title-product");
        productRepository.save(product);
        assertNotNull(product.getId());

        Offer offer = new Offer();
        offer.setMerchant(merchant);
        offer.setProduct(product);;
        offer.setEnabled(true);
        offer.setAmount(100L);
        offer.setPublicity(Publicity.PUBLIC);
        offerRepository.save(offer);
        assertNotNull(offer.getId());

        Offer offer1 = new Offer();
        offer1.setMerchant(merchant);
        offer1.setProduct(product);
        offer1.setEnabled(false);
        offer1.setAmount(200L);
        offerRepository.save(offer1);
        assertNotNull(offer1);

        Activity activity1 = new Activity("activity1");
        activityRepository.save(activity1);
//        activity1.addMerchant(merchant);
//        activityRepository.save(activity1);
        Activity activity2 = new Activity("activity2");
        activity2.addParent(activity1);
        activityRepository.save(activity2);
//        activity2.addMerchant(merchant);
//        activityRepository.save(activity2);

        Activity activity3 = new Activity("activity3");
        activity3.addChildren(activity2);
        activityRepository.save(activity3);
        merchant.addActivity(activity3).addActivity(activity1).addActivity(activity2);
        merchantRepository.save(merchant);

//        Set<Activity> activities = new HashSet<>();
//        activities.add(activity1);
//        activities.add(activity2);
//        merchant.setActivities(activities);

//        merchantRepository.save(merchant);

        boolean contains = entityManager.contains(merchant);

        System.out.println("contains = " + contains);
        Merchant foundMerchant = merchantRepository.findByName(merchant.getName());
        //Merchant foundMerchant = entityManager.find(Merchant.class,merchant.getId());
        assertNotNull(foundMerchant);
        System.out.println("users = " + foundMerchant.getUsers());
        System.out.println("products = " + foundMerchant.getProducts());
        System.out.println("offers = " + foundMerchant.getOffers());

        Hibernate.initialize(foundMerchant.getUsers());
        Hibernate.initialize(foundMerchant.getProducts());
        Hibernate.initialize(foundMerchant.getOffers());
        System.out.println("--after init");
        System.out.println("users = " + foundMerchant.getUsers());
        System.out.println("products = " + foundMerchant.getProducts());
        System.out.println("offers = " + foundMerchant.getOffers());


    }

    @Test
    //@Transactional
    public void testGetCollection() {
        testCreateMerchantWithProductAndOffers();
        //Merchant foundMerchant = merchantRepository.findByName("merchant");
        Merchant foundMerchant = findByName("merchant");
        assertNotNull(foundMerchant);

        assertNotNull(foundMerchant);
        System.out.println("users = " + foundMerchant.getUsers());
        System.out.println("products = " + foundMerchant.getProducts());
        System.out.println("offers = " + foundMerchant.getOffers());
        System.out.println("activities = " + foundMerchant.getActivities());


    }



    //@Test
    @Rollback(false)
    @Transactional
    public void test2() {
        testCreateMerchantWithProductAndOffers();
        for (User user : userRepository.findAll()) {
            System.out.println(user.getId() + ":" + user.getMerchant());
        }
        for (Merchant merchant : merchantRepository.findAll()) {
            System.out.println(merchant.getId() + ":" + merchant.getUsers());
        }
        Merchant foundMerchant = merchantRepository.findAll().iterator().next();

        assertNotNull(foundMerchant);
        System.out.println("id = " + foundMerchant.getId());
        foundMerchant = entityManager.find(Merchant.class,foundMerchant.getId());
        Hibernate.initialize(foundMerchant.getProducts());
        System.out.println("users = " + foundMerchant.getUsers());
        System.out.println("prodcuts : " +  foundMerchant.getProducts().size() );
        for (Product product1 : foundMerchant.getProducts()) {
            System.out.println(product1.getTitle());
        }
        System.out.println("offers : " + foundMerchant.getOffers());
        for (Offer offer2 : foundMerchant.getOffers()) {
            System.out.println(offer2.getMerchant().getName() + "-->" + offer2.getProduct().getTitle() + " [" + offer2.getAmount() + "]");
        }
    }


}
