package ru.bugmakers.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by Ivan
 */
@Configuration
public class PersistConfig {

    private static final String LOC = "loc";

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

    private Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    @Configuration
    @PropertySource("classpath:db.properties")
    @Profile("!" + LOC)
    static class DevProps {

    }

    @Configuration
    @PropertySource("classpath:db_loc.properties")
    @Profile(LOC)
    static class LocProps {
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf() throws PropertyVetoException {

        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setPackagesToScan("ru.bugmakers.entity");
//        emf.setJpaProperties(getAdditionalProperties());
        return emf;
    }

    private Properties getAdditionalProperties() {
        Properties props = null;
        for (String profile : env.getActiveProfiles()) {
            if (LOC.equalsIgnoreCase(profile)) {
                props = new Properties();
                props.setProperty("javax.persistence.schema-generation.database.action", "create");
                props.setProperty("javax.persistence.sql-load-script-source", "2.sql");
                break;
            }
        }
        return props;
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
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
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


