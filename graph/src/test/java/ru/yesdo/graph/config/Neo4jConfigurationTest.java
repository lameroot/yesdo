package ru.yesdo.graph.config;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;
import ru.yesdo.graph.AbstractConfigTest;
import ru.yesdo.graph.repository.ActivityGraphRepository;
import ru.yesdo.graph.repository.MerchantGraphRepository;
import ru.yesdo.graph.repository.ProductGraphRepository;

import javax.annotation.Resource;

/**
 * Created by lameroot on 21.01.15.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,classes = {
        Neo4jConfiguration.class
})
public class Neo4jConfigurationTest extends AbstractConfigTest {

        @Resource
        protected ApplicationContext applicationContext;
        @Resource
        protected Neo4jTemplate neo4jTemplate;
        @Resource
        protected ActivityGraphRepository activityGraphRepository;
        @Resource
        protected PlatformTransactionManager platformTransactionManager;




        @Test
        public void testExist() {
                assertNotNull(applicationContext);
                assertNotNull(neo4jTemplate);
                assertNotNull(activityGraphRepository);
                assertNotNull(platformTransactionManager);

        }



}
