package by.receipt.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
@EnableTransactionManagement
@ComponentScan("by.receipt")
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("by.receipt.repository")
public class JavaConfig {


    private static final String PROP_DATABASE_DRIVER = "spring.datasource.driver-class-name";
    private static final String PROP_DATABASE_PASSWORD = "spring.datasource.password";
    private static final String PROP_DATABASE_URL = "spring.datasource.url";
    private static final String PROP_DATABASE_USERNAME = "spring.datasource.username";
    private static final String PROP_HIBERNATE_DIALECT = "spring.jpa.properties.hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "db.hibernate.show_sql";
    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "spring.entitymanager.packages.to.scan";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto";
    private static final String PROP_HIBERNATE_CONNECTION_POOL_SIZE = "db.hibernate.connection.pool_size";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROP_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setJpaProperties(additionalProperties());
        return entityManagerFactoryBean;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
        properties.setProperty("hibernate.dialect", env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.setProperty("hibernate.show_sql", env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
        properties.setProperty("hibernate.connection.pool_size", env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_POOL_SIZE));
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


}
