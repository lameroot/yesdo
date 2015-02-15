package ru.yesdo.graph.config;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.yesdo.graph.AbstractConfigTest;
import ru.yesdo.graph.repository.ActivityGraphRepository;
import ru.yesdo.graph.repository.MerchantGraphRepository;
import ru.yesdo.graph.repository.ProductGraphRepository;
import ru.yesdo.graph.repository.UserGraphRepository;
import ru.yesdo.model.User;

import javax.annotation.Resource;
import javax.transaction.TransactionManager;
import java.util.HashMap;

/**
 * Created by lameroot on 21.01.15.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,classes = {
        //Neo4jConfigurationTest.Neo4jConfigurationForTest.class
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
        @Resource
        protected GraphDatabaseService graphDatabaseService;


    @Resource
    private UserGraphRepository userGraphRepository;

        @BeforeTransaction
        //@Before
//        @Transactional
//        @Rollback(false)
        public void setUp() {
                System.out.println("-----");
                neo4jTemplate.query("MATCH (n) OPTIONAL MATCH (n)-[r]-() DELETE n,r", new HashMap<>());
                //Neo4jHelper.cleanDb(graphDatabaseService);
        }


        @Test
        public void testExist() {
                assertNotNull(applicationContext);
                assertNotNull(neo4jTemplate);
                assertNotNull(activityGraphRepository);
                assertNotNull(platformTransactionManager);
            assertNotNull(userGraphRepository);

//            Result<User> users = userGraphRepository.findAll();
//            System.out.println(users);
        }

        @Test
        @Transactional
        public void test1() {
                System.out.println("this is test1");
        }


        @Configuration
        @EnableTransactionManagement(mode = AdviceMode.PROXY)
        @EnableNeo4jRepositories(basePackages = "ru.yesdo.graph.repository")
        @ComponentScan("ru.yesdo.graph.service")
        static class Neo4jConfigurationForTest extends org.springframework.data.neo4j.config.Neo4jConfiguration
        {

                private static final String DB_PATH = "data/graph.db";

                public Neo4jConfigurationForTest() {
                        setBasePackage("ru.yesdo.model");
                }



                @Bean
                public GraphDatabaseService graphDatabaseService() {
                        return new TestGraphDatabaseFactory().newImpermanentDatabase();
                        //return new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
                }
        }



}
