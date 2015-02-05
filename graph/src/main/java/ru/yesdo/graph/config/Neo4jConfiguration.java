package ru.yesdo.graph.config;


import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by lameroot on 21.01.15.
 */
@Configuration
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "ru.yesdo.graph.repository")
@ComponentScan("ru.yesdo.graph.service")
public class Neo4jConfiguration extends org.springframework.data.neo4j.config.Neo4jConfiguration {


    private static final String DB_PATH = "data/graph.db";

    public Neo4jConfiguration() {
        setBasePackage("ru.yesdo.model");
    }

//    @Bean
//    public GraphDatabaseService graphDatabaseService() {
//
//        return new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
//    }
    @Bean
    public GraphDatabaseService graphDatabaseService() {
        // see https://github.com/neo4j/spatial
        SpringRestGraphDatabase database = new SpringRestGraphDatabase("http://localhost:7474/db/data/");
        return database;
    }
}
