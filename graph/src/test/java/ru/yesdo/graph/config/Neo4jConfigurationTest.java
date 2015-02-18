package ru.yesdo.graph.config;

import com.vividsolutions.jts.geom.Coordinate;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.gis.spatial.*;
import org.neo4j.gis.spatial.encoders.SimplePointEncoder;
import org.neo4j.gis.spatial.osm.OSMImporter;
import org.neo4j.gis.spatial.pipes.GeoPipeFlow;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.test.TestGraphDatabaseFactory;
import org.neo4j.unsafe.batchinsert.BatchInserter;
import org.neo4j.unsafe.batchinsert.BatchInserterImpl;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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
        protected SpatialDatabaseService spatialDatabaseService;


    @Resource
    private UserGraphRepository userGraphRepository;

        //@BeforeTransaction
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
                assertNotNull(spatialDatabaseService);

//            Result<User> users = userGraphRepository.findAll();
//            System.out.println(users);
        }

        @Test
        public void testSpatial() throws IOException {
//                ShapefileImporter shapefileImporter = new ShapefileImporter(graphDatabaseService);
//                File file = new File("/Users/lameroot/Downloads/highway.shp");
//                System.out.println(file.exists());
//                shapefileImporter.importFile("/Users/lameroot/Downloads/highway.shp","test");

//                SpatialDatabaseService db = new SpatialDatabaseService( graphDatabaseService );
//                EditableLayer layer = (EditableLayer) db.createLayer("test", SimplePointEncoder.class, EditableLayerImpl.class, "lon:lat");
//                assertNotNull( layer );


                SimplePointLayer pointLayer = spatialDatabaseService.createSimplePointLayer("test");
                assertNotNull(pointLayer);
                pointLayer.add(new Coordinate(11.2,22.3));
                pointLayer.add(new Coordinate(21.2,22.3));
                pointLayer.add(new Coordinate(31.2,22.3));
                pointLayer.add(new Coordinate(41.2,22.3));

                Coordinate myPosition = new Coordinate(22.2,22.3);
                List<GeoPipeFlow> list = pointLayer.findClosestPointsTo(myPosition,10);
                for (GeoPipeFlow geoPipeFlow : list) {
                        System.out.println(geoPipeFlow.getGeometry().getCoordinate());
                }

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
