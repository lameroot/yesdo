package ru.yesdo.graph.repository;

import org.junit.Test;
import ru.yesdo.graph.config.Neo4jConfigurationTest;
import ru.yesdo.model.Merchant;

import javax.annotation.Resource;

/**
 * Created by lameroot on 25.01.15.
 */
public class MerchantGraphRepositoryTest extends Neo4jConfigurationTest {

    @Resource
    private MerchantGraphRepository merchantGraphRepository;

    @Test
    public void testCreate() {
        Merchant merchant1 = new Merchant();
        merchant1.setTitle("testMerchantTitle1");
        merchantGraphRepository.save(merchant1);
        assertNotNull(merchant1.getId());

        Merchant foundMerchant1 = merchantGraphRepository.findBySchemaPropertyValue("merchantLogin",merchant1.getMerchantLogin());
        assertNotNull(foundMerchant1);
    }
}
