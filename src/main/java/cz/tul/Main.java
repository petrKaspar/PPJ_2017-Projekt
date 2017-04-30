package cz.tul;

import cz.tul.repositories.AuthorRepository;
import cz.tul.repositories.PictureRepository;
import cz.tul.repositories.TestovaciRepository;
import cz.tul.service.TestovaciService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

@SpringBootApplication
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {


        SpringApplication application = new SpringApplication(Main.class);
        ConfigurableApplicationContext context = application.run(args);

        TestovaciRepository testovaciRepository = context.getBean(TestovaciRepository.class);

//        TestovaciService testovaciService= new TestovaciService(context.getBean(TestovaciRepository.class));

        context.getBean(TestovaciService.class).getAllTestovaci().forEach(System.out::println);

        AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
        PictureRepository pictureRepository = context.getBean(PictureRepository.class);

        log.info("pictureRepository.findAll() = " + pictureRepository.findAll());
        log.info("pictureRepository.incrementNLike(12) = " + pictureRepository.incrementNLike(12,"lastUpdate"));

    }

}