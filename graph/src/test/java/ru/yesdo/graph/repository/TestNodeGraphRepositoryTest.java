package ru.yesdo.graph.repository;

import org.junit.Test;
import ru.yesdo.graph.config.Neo4jConfigurationTest;
import ru.yesdo.model.TestNode;

import javax.annotation.Resource;

/**
 * Created by lameroot on 25.01.15.
 */
public class TestNodeGraphRepositoryTest extends Neo4jConfigurationTest {

    @Resource
    private TestNodeGraphRepository testNodeGraphRepository;

    @Test
    public void testCreate() {
        TestNode testNode1 = new TestNode();
        testNode1.setTitle("testnode1");
        testNode1.setId(100L);
        testNode1 = testNodeGraphRepository.save(testNode1);

        TestNode testNode11 = new TestNode();
        testNode11.setTitle("testnode11");
        testNode11 = testNodeGraphRepository.save(testNode11);

        TestNode testNode2 = new TestNode();
        testNode2.setTitle("testnode2");
        testNode2.getParents().add(testNode1);
        testNode2.getParents().add(testNode11);
        testNode2 = testNodeGraphRepository.save(testNode2);

        TestNode foundTestNode = testNodeGraphRepository.findByTitle("testnode2");
        assertNotNull(foundTestNode.getParents().size() == 2);
        neo4jTemplate.fetch(foundTestNode.getParents());
        assertTrue(foundTestNode.getParents().contains(testNode1));

    }
}
