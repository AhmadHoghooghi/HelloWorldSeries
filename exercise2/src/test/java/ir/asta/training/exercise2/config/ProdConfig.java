package ir.asta.training.exercise2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

// @Configuration
// @EnableTransactionManagement
// @ComponentScan(basePackages = {"ir.asta.training.exercise2"})
public class ProdConfig {

    // @Bean(name = "propertyConfigurer")
    // public PropertyPlaceholderConfigurer propertyConfigurer(){
    //     PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
    //     propertyPlaceholderConfigurer.setLocation(new ClassPathResource("config.properties"));
    //     propertyPlaceholderConfigurer.setSystemPropertiesMode(2);
    //     return propertyPlaceholderConfigurer;
    // }
    //
    // @Bean
    // public PersistenceExceptionTranslationPostProcessor exceptionTranslator(){
    //     PersistenceExceptionTranslationPostProcessor exceptionTranslator = new PersistenceExceptionTranslationPostProcessor();
    //     exceptionTranslator.setProxyTargetClass(true);
    //     return exceptionTranslator;
    // }
    //
    // @Bean("dataSource")
    // public JndiObjectFactoryBean dataSource(
    //         @Value("hibernate.datasource.jndi") String jndiName){
    //     JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
    //     jndiObjectFactoryBean.setJndiName(jndiName);
    //     jndiObjectFactoryBean.setLookupOnStartup(false);
    //     jndiObjectFactoryBean.setProxyInterface(DataSource.class);
    //     return jndiObjectFactoryBean;
    // }
    //
    // @Bean("entityManagerFactory")
    // public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    //         @Value("hibernate.hbm2ddl.auto") String hbm2ddl,
    //         @Value("hibernate.connection.show_sql") String showSql,
    //         @Value("hibernate.dialect") String hibernateDialect
    //
    // ){
    //     LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    //     emf.setDataSource(dataSource(null));
    //     emf.setPackagesToScan("ir.asta.training.exercise2.entities");
    //
    //     return emf;
    // }

}
