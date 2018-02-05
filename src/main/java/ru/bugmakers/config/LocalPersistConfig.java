package ru.bugmakers.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.beans.PropertyVetoException;

/**
 * Created by Ivan
 */
@Configuration
@PropertySource("classpath:db_loc.properties")
@EnableJpaRepositories(
        basePackages = "ru.bugmakers.localpers",
        entityManagerFactoryRef = "localEmf",
        transactionManagerRef = "jpaLocalTransactionManager")
public class LocalPersistConfig {

    @Value("${loc.db.url}")
    private String dbUrl;
    @Value("${loc.db.username}")
    private String dbUserName;
    @Value("${loc.db.password}")
    private String dbPassword;
    @Value("${loc.db.driver}")
    private String dbDriver;
    @Value("${loc.db.dialect}")
    private String dbDialect;
    @Value("${loc.db.pool.initial}")
    private Integer dbPoolInitial;
    @Value("${loc.db.pool.min}")
    private Integer dbPoolMin;
    @Value("${loc.db.pool.max}")
    private Integer dbPoolMax;
    @Value("${loc.db.dbms}")
    private String dbms;

    @Bean
    public LocalContainerEntityManagerFactoryBean localEmf() throws PropertyVetoException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(localDataSource());
        emf.setJpaVendorAdapter(jpaLocalVendorAdapter());
        emf.setPackagesToScan("ru.bugmakers.localpers");
        return emf;
    }

    @Bean(destroyMethod = "close")
    public ComboPooledDataSource localDataSource() throws PropertyVetoException {
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
    public HibernateJpaVendorAdapter jpaLocalVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(Database.valueOf(dbms));
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setDatabasePlatform(dbDialect);
        return jpaVendorAdapter;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDevLocal() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JpaTransactionManager jpaLocalTransactionManager() throws PropertyVetoException {
        return new JpaTransactionManager(localEmf().getObject());
    }
}
