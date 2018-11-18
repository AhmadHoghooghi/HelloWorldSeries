package caveatemptor.configs;

import caveatemptor.configs.databasequalifiers.InMemoryMode;
import caveatemptor.configs.databasequalifiers.ServerMode;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class RepositoryConfig {

    //ServerMode Persistence Unit Bootstrapping
    @Bean
    @Profile(value = "prod")
    public LocalContainerEntityManagerFactoryBean localContainerServerModeEntityManagerFactoryBean(
            @ServerMode DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource);
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcemfb.setJpaProperties(serverModeProperties());
        lcemfb.setPackagesToScan("caveatemptor.entities");
        lcemfb.setPersistenceUnitName("ServerModePersistenceUnit");
        return lcemfb;
    }
    private Properties serverModeProperties() {
        Properties properties = new Properties();
        // properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        // properties.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");
        properties.setProperty("javax.persistence.schema-generation.database.action", "validate");
        properties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return properties;
    }

    @Bean
    @ServerMode
    public DataSource serverDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/h2dbs/CaveatEmptorJavaBootstrapped");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }

    //InMemory Persistence Unit Bootstrapping
    @Bean
    @Profile(value = "test")
    public LocalContainerEntityManagerFactoryBean localContainerInMemoryEntityManagerFactoryBean(
            @InMemoryMode DataSource dataSource,
            JpaVendorAdapter jpaVendorAdapter
    ) {
        LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
        lcemfb.setDataSource(dataSource);
        lcemfb.setJpaVendorAdapter(jpaVendorAdapter);
        lcemfb.setJpaProperties(inMemoryProperties());
        lcemfb.setPackagesToScan("caveatemptor.entities");
        lcemfb.setPersistenceUnitName("InMemoryPersistenceUnit");
        return lcemfb;
    }

    private Properties inMemoryProperties() {
        Properties properties = new Properties();
        // properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create");
        properties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return properties;
    }

    @Bean
    @InMemoryMode
    public DataSource inMemoryDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/h2dbs/caveatEmptorEmbeddedDB");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
    // Shared Beans
    @Bean
    public BeanPostProcessor persistenceTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.H2);
        return hibernateJpaVendorAdapter;
    }
    @Bean
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory emf){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;
    }
    @Bean
    public PersistenceAnnotationBeanPostProcessor paPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }
}
