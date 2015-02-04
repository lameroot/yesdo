package ru.yesdo.graph.service;

import org.junit.Before;
import org.junit.Test;
import org.neo4j.cypherdsl.expression.PathExpression;
import ru.yesdo.graph.data.ProductData;
import ru.yesdo.graph.repository.ProductGraphRepository;
import ru.yesdo.model.Merchant;
import ru.yesdo.model.Product;
import ru.yesdo.model.MerchantProductRelationship;
import ru.yesdo.model.ProductType;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.neo4j.cypherdsl.CypherQuery.*;
import static org.neo4j.cypherdsl.querydsl.CypherQueryDSL.*;

/**
 * Created by lameroot on 01.02.15.
 */
public class ProductGraphServiceTest extends MerchantGraphServiceTest {

    @Resource
    private ProductGraphService productGraphService;
    @Resource
    private ProductGraphRepository productGraphRepository;

    @Before
    public void clearProducts() {
        super.clearMerchantGraphRepositories();
        productGraphRepository.deleteAll();
    }

    private Product create(String title, String merchantTitle, Long amount) {
        ProductData productData = new ProductData().setTitle(title)
                .setProductType(ProductType.SERVICE)
                .setAmount(amount)
                .setCreatedAt(new Date());
        Product product = productGraphService.create(productData);
        assertNotNull(product.getId());

        Merchant merchant = findMerchantByTitle(merchantTitle);
        assertNotNull(merchant);
        merchantGraphService.joinToMerchant(merchant,product);

        return product;
    }

    @Test
    public void testCreateProducts() {
        Product p11 = create("p11","m11",100L);
        Product p12 = create("p12","m11",110L);
        Product p13 = create("p13","m12",120L);
        Product p14 = create("p14","m12",140L);
        Product p21 = create("p21","m21",200L);
        Product p22 = create("p22","m21",210L);
        Product p23 = create("p23","m22",220L);
        Product p24 = create("p24","m22",230L);
        Product p31 = create("p31","m31",300L);
        Product p32 = create("p32","m31",310L);
        Product p33 = create("p33","m32",320L);
        Product p34 = create("p34","m32",330L);
        Product p35 = create("p35","m33",340L);
        Product p36 = create("p36","m33",350L);
        Product p37 = create("p37","m34",360L);
        Product p38 = create("p38","m34",370L);
    }

    @Test
    public void testCreateRealProduct() {
        super.testCreateRealMerchant();

        ProductData productDataMerchantLevel2Num1_num1 = new ProductData()
                .setProductType(ProductType.SERVICE)
                .setAmount(100L)
                .setCreatedAt(new Date())
                .setTitle("product_merchant_level2_num1_num1");
        Product productMerchantLevel2Num1_num1 = productGraphService.create(productDataMerchantLevel2Num1_num1);
        assertNotNull(productMerchantLevel2Num1_num1.getId());

        ProductData productDataMerchantLevel2Num1_num2 = new ProductData()
                .setProductType(ProductType.SERVICE)
                .setAmount(110L)
                .setCreatedAt(new Date())
                .setTitle("product_merchant_level2_num1_num2");
        Product productMerchantLevel2Num1_num2 = productGraphService.create(productDataMerchantLevel2Num1_num2);
        assertNotNull(productMerchantLevel2Num1_num2.getId());


        merchantGraphService.joinToMerchant(findMerchantByTitle("merchant_level2_num1"),productMerchantLevel2Num1_num1,productMerchantLevel2Num1_num2);
        Merchant testMerchant = findMerchantByTitle("merchant_level2_num1");
        assertNotNull(testMerchant);
        assertTrue(testMerchant.getMerchantProducts().size() == 2);

        //
        ProductData productDataMerchantLevel2Num2_num1 = new ProductData()
                .setProductType(ProductType.SERVICE)
                .setAmount(200L)
                .setCreatedAt(new Date())
                .setTitle("product_merchant_level2_num2_num1");
        Product productMerchantLevel2Num2_num1 = productGraphService.create(productDataMerchantLevel2Num2_num1);
        assertNotNull(productMerchantLevel2Num2_num1.getId());

        ProductData productDataMerchantLevel2Num2_num2 = new ProductData()
                .setProductType(ProductType.SERVICE)
                .setAmount(210L)
                .setCreatedAt(new Date())
                .setTitle("product_merchant_level2_num1_num2");
        Product productMerchantLevel2Num2_num2 = productGraphService.create(productDataMerchantLevel2Num2_num2);
        assertNotNull(productMerchantLevel2Num2_num2.getId());

        merchantGraphService.joinToMerchant(findMerchantByTitle("merchant_level2_num2"),productMerchantLevel2Num2_num1,productMerchantLevel2Num2_num2);
        Merchant testMerchant2 = findMerchantByTitle("merchant_level2_num2");
        assertNotNull(testMerchant2);
        assertTrue(testMerchant2.getMerchantProducts().size() == 2);

        //
        ProductData productDataMerchantLevel3Num1_num1 = new ProductData()
                .setProductType(ProductType.SERVICE)
                .setAmount(300L)
                .setCreatedAt(new Date())
                .setTitle("product_merchant_level3_num1_num1");
        Product productMerchantLevel3Num1_num1 = productGraphService.create(productDataMerchantLevel3Num1_num1);
        assertNotNull(productMerchantLevel3Num1_num1.getId());

        ProductData productDataMerchantLevel3Num1_num2 = new ProductData()
                .setProductType(ProductType.SERVICE)
                .setAmount(310L)
                .setCreatedAt(new Date())
                .setTitle("product_merchant_level3_num1_num2");
        Product productMerchantLevel3Num1_num2 = productGraphService.create(productDataMerchantLevel3Num1_num2);
        assertNotNull(productMerchantLevel3Num1_num2.getId());

        ProductData productDataMerchantLevel3Num1_num3 = new ProductData()
                .setProductType(ProductType.SERVICE)
                .setAmount(320L)
                .setCreatedAt(new Date())
                .setTitle("product_merchant_level3_num1_num3");
        Product productMerchantLevel3Num1_num3 = productGraphService.create(productDataMerchantLevel3Num1_num3);
        assertNotNull(productMerchantLevel3Num1_num3.getId());

        merchantGraphService.joinToMerchant(findMerchantByTitle("merchant_level3_num1"),
                productMerchantLevel3Num1_num1,productMerchantLevel3Num1_num2,productMerchantLevel3Num1_num3);
        Merchant testMerchant3 = findMerchantByTitle("merchant_level3_num1");
        assertNotNull(testMerchant3);
        assertTrue(testMerchant3.getMerchantProducts().size() == 3);

    }

    @Test
    public void testFindByAmount() {
        testCreateRealProduct();


        //match (p:Product)<-[r:PRODUCT]-(m)<-[:MERCHANT]-()<-[:ACTIVITY*{2}]-(a:Activity {title: {0}}) where r.amount >= {1} return distinct p
//        start(lookup("p","Product",null,null))
//        .match(from("p").in("PRODUCT"))
//
//        productGraphRepository.query()

        List<Product> products = productGraphRepository.findByAmount("activity_level1_num1",200);
        assertNotNull(products);
        System.out.println("size = " + products.size());
        for (Product product : products) {
            System.out.println(product.getId() + ":" + product.getTitle());
        }


    }
}
