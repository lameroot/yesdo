package ru.yesdo.graph.service;

import org.junit.Before;
import org.junit.Test;
import ru.yesdo.graph.data.OfferData;
import ru.yesdo.graph.data.ProductData;
import ru.yesdo.graph.repository.OfferGraphRepository;
import ru.yesdo.graph.repository.ProductGraphRepository;
import ru.yesdo.model.Merchant;
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
    }

    private Product create(String title, String merchantTitle, Long amount, boolean addOffer) {
        Merchant merchant = findMerchantByName(merchantTitle);
        assertNotNull(merchant);
        ProductData productData = new ProductData().setTitle(title)
                .setMerchant(merchant)

                .setCreatedAt(new Date());
        Product product = productGraphService.create(productData);
        assertNotNull(product.getId());



        if ( addOffer ) merchantGraphService.concludeOffer(merchant,product,new OfferData().setAmount(amount));

        return product;
    }

    @Test
    public void testCreateProducts() {
        super.testCreateMerchants();
        Product p11 = create("p11","m11",100L,true);
        Product p12 = create("p12","m11",110L,true);
        Product p13 = create("p13","m12",120L,true);
        Product p14 = create("p14","m12",140L,true);
        Product p21 = create("p21","m21",200L,true);
        Product p22 = create("p22","m21",210L,true);
        Product p23 = create("p23","m22",220L,true);
        Product p24 = create("p24","m22",230L,true);
        Product p31 = create("p31","m31",300L,true);
        Product p32 = create("p32","m31",310L,true);
        Product p33 = create("p33","m32",320L,true);
        Product p34 = create("p34","m32",330L,true);
        Product p35 = create("p35","m33",340L,true);
        Product p36 = create("p36","m33",350L,true);
        Product p37 = create("p37","m34",360L,false);
        Product p38 = create("p38","m34",370L,false);
    }

}
