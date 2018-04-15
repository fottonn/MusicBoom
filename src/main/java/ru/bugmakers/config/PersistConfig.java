package ru.bugmakers.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;
import java.util.Properties;

/**
 * Created by Ivan
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "ru.bugmakers.repository",
        entityManagerFactoryRef = "emf",
        transactionManagerRef = "jpaTransactionManager")
@EnableTransactionManagement
public class PersistConfig {

    @Value("${db.url}")
    private String dbUrl;
    @Value("${db.username}")
    private String dbUserName;
    @Value("${db.password}")
    private String dbPassword;
    @Value("${db.driver}")
    private String dbDriver;
    @Value("${db.dialect}")
    private String dbDialect;
    @Value("${db.pool.initial}")
    private Integer dbPoolInitial;
    @Value("${db.pool.min}")
    private Integer dbPoolMin;
    @Value("${db.pool.max}")
    private Integer dbPoolMax;
    @Value("${db.dbms}")
    private String dbms;
    @Value("${hibernate.jdbc.lob.non_contextual_creation}")
    private String lobNonContextualCreation;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Configuration
    @PropertySource("classpath:db.properties")
    @Profile("!dev")
    static class PromProps {

    }

    @Configuration
    @PropertySource("classpath:db_dev.properties")
    @Profile("dev")
    static class DevProps {
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean emf() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setPackagesToScan("ru.bugmakers.entity");
        Properties hibernateProps = new Properties();
        hibernateProps.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        hibernateProps.setProperty("hibernate.jdbc.lob.non_contextual_creation", lobNonContextualCreation);
        emf.setJpaProperties(hibernateProps);
        return emf;
    }

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(dbDriver);
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUser(dbUserName);
        dataSource.setPassword(dbPassword);
        dataSource.setInitialPoolSize(dbPoolInitial);
        dataSource.setMinPoolSize(dbPoolMin);
        dataSource.setMaxPoolSize(dbPoolMax);
        return dataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(Database.valueOf(dbms));
        jpaVendorAdapter.setDatabasePlatform(dbDialect);
        return jpaVendorAdapter;
    }

    @Bean
    @Primary
    public JpaTransactionManager jpaTransactionManager() throws PropertyVetoException {
        return new JpaTransactionManager(emf().getObject());
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}


