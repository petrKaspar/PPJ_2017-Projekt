package cz.tul;

import cz.tul.provisioning.Provisioner;
import cz.tul.repositories.AuthorRepository;
import cz.tul.repositories.PictureRepository;
import cz.tul.repositories.TestovaciRepository;
import cz.tul.service.TestovaciService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

//@SpringBootApplication
@SpringBootApplication(exclude = {
        HibernateJpaAutoConfiguration.class,
        DataSourceAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class})
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);


    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
    }

    public static void main(String[] args) throws Exception {


        SpringApplication application = new SpringApplication(Main.class);
        ConfigurableApplicationContext context = application.run(args);

        TestovaciRepository testovaciRepository = context.getBean(TestovaciRepository.class);


        context.getBean(TestovaciService.class).getAllTestovaci().forEach(System.out::println);

        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        PictureRepository pictureRepository = context.getBean(PictureRepository.class);

    }

}