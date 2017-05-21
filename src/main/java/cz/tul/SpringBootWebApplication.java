package cz.tul;

import cz.tul.configurations.MainSpringConfiguration;
import cz.tul.data.Author;
import cz.tul.provisioning.Provisioner;
import cz.tul.repositories.AuthorRepository;
import cz.tul.service.AuthorService;
import cz.tul.service.PictureService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@EntityScan("cz.tul.data")
@ComponentScan("cz.tul.service")
public class SpringBootWebApplication {

    public static void main(String[] args) throws Exception {
//
//        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//        rootContext.register(MainSpringConfiguration.class);
//
//        SpringApplication app = new SpringApplication(SpringBootWebApplication.class);
//        ApplicationContext ctx = app.run(args);
//
//        AuthorService authorService = ctx.getBean(AuthorService.class);
//        Author author1 = new Author("pepa JPA test1");
//        Author author2 = new Author("pepa JPA test2");
//        Author author3 = new Author("pepa JPA test3");
//        Author author4 = new Author("pepa JPA test4");
////        Author author1 = new Author("JPA rest test");
//        authorService.create(author1);
//        authorService.create(author2);
//        authorService.create(author3);
//        System.out.println(authorService.create(author4));
//        System.out.println(authorService.create(author2));
//
//        List<Author> authors = authorService.getAllAuthors();
//        System.out.println("authors.size() = " + authors.size());
//        System.out.println(authors);
//
//        PictureService pictureService = ctx.getBean(PictureService.class);
//        System.out.println("pictureService.getAllPictures().size() = " + pictureService.getAllPictures().size());

        SpringApplication.run(SpringBootWebApplication.class, args);

    }

}