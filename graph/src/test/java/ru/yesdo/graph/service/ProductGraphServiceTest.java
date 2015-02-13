package ru.yesdo.graph.service;

import org.junit.Before;
import org.junit.Test;
import ru.yesdo.graph.data.ContactData;
import ru.yesdo.graph.data.OfferData;
import ru.yesdo.graph.data.ProductData;
import ru.yesdo.graph.repository.OfferGraphRepository;
import ru.yesdo.graph.repository.ProductGraphRepository;
import ru.yesdo.model.Merchant;
import ru.yesdo.model.OfferContact;
import ru.yesdo.model.Product;
import ru.yesdo.model.ProductType;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by lameroot on 01.02.15.
 */
public class ProductGraphServiceTest extends MerchantGraphServiceTest {

    @Resource
    private ProductGraphService productGraphService;
    @Resource
    private ProductGraphRepository productGraphRepository;
    @Resource
    private OfferGraphRepository offerGraphRepository;

    @Before
    public void clearProducts() {
        super.clearMerchantGraphRepositories();
        productGraphRepository.deleteAll();
        offerGraphRepository.deleteAll();
        for (OfferContact offerContact : neo4jTemplate.findAll(OfferContact.class)) {
            neo4jTemplate.delete(offerContact);
        }
    }

    private Product create(String title, String merchantTitle, Long amount, boolean addOffer, double lon, double lat) {
        Merchant merchant = findMerchantByName(merchantTitle);
        assertNotNull(merchant);
        ProductData productData = new ProductData().setTitle(title)
                .setMerchant(merchant)

                .setCreatedAt(new Date());
        Product product = productGraphService.create(productData);
        assertNotNull(product.getId());

        if ( addOffer ) merchantGraphService.concludeOffer(merchant,product,new OfferData().setAmount(amount).setContactData(new ContactData().setLocation(lon, lat)));

        return product;
    }

    @Test
    public void testCreateProducts() {
        super.testCreateMerchants();
        Product p11 = create("p11","m11",100L,true,11.0,34.0);
        Product p12 = create("p12","m11",110L,true,12.0,34.0);
        Product p13 = create("p13","m12",120L,true,13.0,34.0);
        Product p14 = create("p14","m12",140L,true,14.0,34.0);
        Product p21 = create("p21","m21",200L,true,15.0,34.0);
        Product p22 = create("p22","m21",210L,true,16.0,34.0);
        Product p23 = create("p23","m22",220L,true,17.0,34.0);
        Product p24 = create("p24","m22",230L,true,18.0,34.0);
        Product p31 = create("p31","m31",300L,true,19.0,34.0);
        Product p32 = create("p32","m31",310L,true,20.0,34.0);
        Product p33 = create("p33","m32",320L,true,21.0,34.0);
        Product p34 = create("p34","m32",330L,true,22.0,34.0);
        Product p35 = create("p35","m33",340L,true,23.0,34.0);
        Product p36 = create("p36","m33",350L,true,24.0,34.0);
        Product p37 = create("p37","m34",360L,false,25.0,34.0);
        Product p38 = create("p38","m34",370L,false,26.0,34.0);
    }

}
