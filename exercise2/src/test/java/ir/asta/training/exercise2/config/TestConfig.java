package ir.asta.training.exercise2.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({TestDataSourceConfig.class,TestBeanConfig.class})
public class TestConfig {
}
