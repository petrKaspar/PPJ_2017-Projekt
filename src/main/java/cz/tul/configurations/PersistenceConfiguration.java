package cz.tul.configurations;

import cz.tul.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.Driver;
import java.util.Properties;


@Configuration
//@Component
@EnableJpaRepositories(basePackageClasses = {AuthorRepository.class})
@EnableTransactionManagement
public class PersistenceConfiguration {

    //Pro správnou funkci této anotace je nutné definovat Bean PropertySourcesPlaceholderConfigurer - viz třída MainSpringConfiguration
    @Value("${spring.datasource.driver}")
    private String connectionDriver;

    @Value("${spring.datasource.url}")
    private String connectionURL;

    @Value("${spring.datasource.username}")
    private String connectionUsername;

    @Value("${spring.datasource.password}")
    private String connectionPassword;

    @Autowired
    private Properties properties;

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations() throws ClassNotFoundException, NamingException {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() throws NamingException, ClassNotFoundException {
        SimpleDriverDataSource source = new SimpleDriverDataSource();
        source.setDriverClass((Class<? extends Driver>) Class.forName(connectionDriver));
        source.setUrl(connectionURL);
        source.setUsername(connectionUsername);
        source.setPassword(connectionPassword);

        return source;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.H2);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException, ClassNotFoundException {
        LocalContainerEntityManagerFactoryBean lemfb = new LocalContainerEntityManagerFactoryBean();
        lemfb.setDataSource(dataSource());
        lemfb.setJpaVendorAdapter(jpaVendorAdapter());
        lemfb.setPackagesToScan("cz.tul.data");
        lemfb.setJpaProperties(properties);
        return lemfb;
    }

}
