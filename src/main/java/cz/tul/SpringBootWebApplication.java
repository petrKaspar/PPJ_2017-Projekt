package cz.tul;

import cz.tul.configurations.MainSpringConfiguration;
import cz.tul.data.Author;
import cz.tul.service.AuthorService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

//import javax.servlet.ServletContext;
//import javax.servlet.ServletRegistration;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
@ComponentScan("cz.tul.service")
@EnableJpaRepositories(basePackages = "cz.tul.repositories")
public class SpringBootWebApplication {

//    private static final Logger log = LoggerFactory.getLogger(Main.class);
//
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    public static void main(String[] args) throws Exception {

        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(MainSpringConfiguration.class);

        SpringApplication app = new SpringApplication(SpringBootWebApplication.class);
        ApplicationContext ctx = app.run(args);

        AuthorService authorService = ctx.getBean(AuthorService.class);
        Author author1 = new Author("pepa JPA test1");
        Author author2 = new Author("pepa JPA test2");
        Author author3 = new Author("pepa JPA test3");
        Author author4 = new Author("pepa JPA test4");
//        Author author1 = new Author("JPA rest test");
        authorService.create(author1);
        authorService.create(author2);
        authorService.create(author3);
        System.out.println(authorService.create(author4));
        System.out.println(authorService.create(author2));

        List<Author> authors = authorService.getAllAuthors();
        System.out.println("authors.size() = " + authors.size());
        System.out.println(authors);


        SpringApplication.run(SpringBootWebApplication.class, args);

//        PictureService pictureService = ctx.getBean(PictureService.class);


    }

}