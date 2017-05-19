package cz.tul;

import cz.tul.service.AuthorService;
import cz.tul.service.PictureService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
@ComponentScan("cz.tul.service")
@EnableJpaRepositories(basePackages = "cz.tul.repositories")
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    public static void main(String[] args) throws Exception {

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        AuthorService authorService = ctx.getBean(AuthorService.class);
        PictureService pictureService = ctx.getBean(PictureService.class);


    }

}