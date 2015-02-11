package ru.yesdo;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by lameroot on 08.02.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        GeneralTest.JpaConfigTest.class
})
public abstract class GeneralTest extends TestCase {

    @Configuration
    @EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
    @EnableJpaRepositories(basePackages = "ru.yesdo.repository")
    public static class JpaConfigTest {
        @Bean
        public EntityManagerFactory entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactory.setDataSource(datasource());
            entityManagerFactory.setPackagesToScan(new String[]{"ru.yesdo.model"});
            entityManagerFactory.setJpaVendorAdapter(jpaVendorAdapter());
            entityManagerFactory.getJpaPropertyMap().put("hibernate.hbm2ddl.auto","create");
            entityManagerFactory.getJpaPropertyMap().put("hibernate.cache.use_query_cache","false");
            entityManagerFactory.getJpaPropertyMap().put("hibernate.cache.use_second_level_cache","false");

            entityManagerFactory.afterPropertiesSet();
            return entityManagerFactory.getObject();
        }

        @Bean
        public PlatformTransactionManager transactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());
            transactionManager.setDataSource(datasource());
            transactionManager.setJpaDialect(new HibernateJpaDialect());
            return transactionManager;
        }

        @Bean
        public DataSource datasource() {
            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

            driverManagerDataSource.setDriverClassName("org.h2.Driver");
            //driverManagerDataSource.setUrl("jdbc:h2:~/yesdo");
            driverManagerDataSource.setUrl("jdbc:h2:tcp://localhost/server~/yesdo");
            driverManagerDataSource.setUsername("sa");

            return driverManagerDataSource;

/*
            EmbeddedDatabaseFactoryBean bean = new EmbeddedDatabaseFactoryBean();
//		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
//		databasePopulator.addScript(new ClassPathResource("hibernate/config/schema.sql"));
//		bean.setDatabasePopulator(databasePopulator);
            bean.setDatabaseType(EmbeddedDatabaseType.H2);

            bean.afterPropertiesSet();
            return bean.getObject();
            */
        }

        HibernateJpaVendorAdapter jpaVendorAdapter() {
            HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
            adapter.setShowSql(true);
            //adapter.setGenerateDdl(true);
            adapter.setDatabase(Database.H2);

            return adapter;
        }
    }
}