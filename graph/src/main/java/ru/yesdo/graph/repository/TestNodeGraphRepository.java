package ru.yesdo.graph.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import ru.yesdo.model.TestNode;

/**
 * Created by lameroot on 25.01.15.
 */
public interface TestNodeGraphRepository extends GraphRepository<TestNode> {

    public TestNode findByTitle(String title);
}
