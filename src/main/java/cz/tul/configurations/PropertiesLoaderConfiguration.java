package cz.tul.configurations;

import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.Properties;

@Configuration
@Import({
        PropertiesLoaderConfiguration.TestPropertiesLoaderConfiguration.class,
        PropertiesLoaderConfiguration.DevelPropertiesLoaderConfiguration.class,
        PropertiesLoaderConfiguration.ProductionPropertiesLoaderConfiguration.class
})
public class PropertiesLoaderConfiguration {


    @Configuration
    @PropertySource("classpath:application-test.properties")
    @Profile("test")
    public static class TestPropertiesLoaderConfiguration {
        @Bean
        public Properties properties() throws NamingException, IOException {
            return PropertiesLoaderUtils.loadAllProperties("application-test.properties");
        }
    }

    @Configuration
    @PropertySource("classpath:application-devel.properties")
    @Profile("devel")
    public static class DevelPropertiesLoaderConfiguration {
        @Bean
        public Properties properties() throws NamingException, IOException {
            return PropertiesLoaderUtils.loadAllProperties("application-devel.properties");
        }
    }

    @Configuration
    @Profile("PROD")
    @PropertySource("classpath:application.properties")
    public static class ProductionPropertiesLoaderConfiguration {
        @Bean
        public Properties properties() throws NamingException, IOException {
            return PropertiesLoaderUtils.loadAllProperties("application.properties");
        }
    }
}
