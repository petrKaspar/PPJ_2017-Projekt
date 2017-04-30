package cz.tul;

import cz.tul.repositories.AutorRepository;
import cz.tul.repositories.PictureRepository;
import cz.tul.repositories.TestovaciRepository;
import cz.tul.service.TestovaciService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

//@EnableTransactionManagement    // abych mohl vyuzivat nad entitama 2transactional
//@EntityScan("cz.tul.data")

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {


        SpringApplication application = new SpringApplication(Main.class);
        ConfigurableApplicationContext context = application.run(args);

        TestovaciRepository testovaciRepository = context.getBean(TestovaciRepository.class);

//        TestovaciService testovaciService= new TestovaciService(context.getBean(TestovaciRepository.class));

        context.getBean(TestovaciService.class).getAllTestovaci().forEach(System.out::println);

        AutorRepository autorRepository = context.getBean(AutorRepository.class);
        PictureRepository pictureRepository = context.getBean(PictureRepository.class);
        System.out.println("pictureRepository.findAll() = " + pictureRepository.findAll());
        System.out.println("pictureRepository.incrementNLike(12) = " + pictureRepository.incrementNLike(12,"lastUpdate"));


    }

}