package ru.bugmakers.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.beans.PropertyVetoException;

/**
 * Created by Ivan
 */
@Configuration
@PropertySource("classpath:db.properties")
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

    @Bean
    public LocalContainerEntityManagerFactoryBean emf() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setPackagesToScan("ru.bugmakers.entity");
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
        jpaVendorAdapter.setGenerateDdl(true);
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


