package cz.tul.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({
        PropertiesLoaderConfiguration.class,
        PersistenceConfiguration.class,
        SpringRestConfiguration.class,
})

@ComponentScan("cz.tul.data")
public class MainSpringConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
