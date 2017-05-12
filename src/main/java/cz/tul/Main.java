package cz.tul;

import cz.tul.data.Author;
import cz.tul.provisioning.Provisioner;
import cz.tul.repositories.AuthorRepository;
import cz.tul.repositories.PictureRepository;
import cz.tul.service.AuthorService;
import cz.tul.service.CommentService;
import cz.tul.service.PictureService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//@SpringBootApplication
//@SpringBootApplication(exclude = {
//        HibernateJpaAutoConfiguration.class,
//        DataSourceAutoConfiguration.class,
//        JpaRepositoriesAutoConfiguration.class})
//@EnableTransactionManagement
//@EntityScan("cz.tul.data")
//@ComponentScan("cz.tul.service")
//@EnableJpaRepositories(basePackages = "cz.tul.repositories")
@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);




    @Bean
    public AuthorService authorService() {
        return new AuthorService();
    }

    @Bean
    public PictureService pictureService() {
        return new PictureService();
    }

    @Bean
    public CommentService commentService() {
        return new CommentService();
    }

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }



    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

//    @Bean
//    public SessionFactory sessionFactory() {return entityManagerFactory.unwrap(SessionFactory.class);}


    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

    public static void main(String[] args) throws Exception {


//        SpringApplication application = new SpringApplication(Main.class);
//        ConfigurableApplicationContext context = application.run(args);

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

//        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
//        PictureRepository pictureRepository = context.getBean(PictureRepository.class);

        AuthorService authorService = ctx.getBean(AuthorService.class);

        LocalDateTime localDateTime = LocalDateTime.now();
        Author a = new Author("Jarda JPA", LocalDateTime.now());

//        AuthorService authorService = new AuthorService();
        List<Author> authors = authorService.getAllAuthors();
        int nAuthors = authors.size();
        System.out.println("**************************");
        System.out.println("nAuthors = " + nAuthors);;
        int key = authorService.create(a);
        System.out.println("key = " + key);
        a.setAuthorId(key);
        System.out.println("**************************");
        authors = authorService.getAllAuthors();
        System.out.println("authors = " + authors.size());


    }

}