package cz.tul;

import cz.tul.data.*;
import cz.tul.repositories.AutorRepository;
import cz.tul.repositories.PictureRepository;
import cz.tul.repositories.TestovaciRepository;
import cz.tul.service.TestovaciService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.*;

import static org.junit.Assert.assertTrue;

//@SpringBootApplication
//@EnableTransactionManagement    // abych mohl vyuzivat nad entitama 2transactional
//@EntityScan("cz.tul.data")

//@SpringBootApplication(exclude = {
//        MongoAutoConfiguration.class,
//        MongoDataAutoConfiguration.class,
//        MongoRepositoriesAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class,
//        DataSourceAutoConfiguration.class,
//        JpaRepositoriesAutoConfiguration.class})

@SpringBootApplication
public class Main {

//    @Bean
//    public TestovaciService testovaciDao() {
//        return new TestovaciService(testovaciRepository);
//    }
//
//    @Bean
//    public PictureService pictureDao() {
//        return new PictureService();
//    }
//
//    @Bean
//    public AutorService autorDao() {
//        return new AutorService();
//    }
//
//    @Bean
//    public CommentService commentDao() {
//        return new CommentService();
//    }

//    @Profile({"devel", "test"})
//    @Bean(initMethod = "doProvision")
//    public Provisioner provisioner() {
//        return new Provisioner();
//    }


//    @Autowired
//    EntityManagerFactory entityManagerFactory;
//
//    @Bean
//    public SessionFactory sessionFactory() {
//        return entityManagerFactory.unwrap(SessionFactory.class);
//    }
//
//    @Bean
//    public PlatformTransactionManager txManager() {
//        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
//    }

    public static void main(String[] args) throws Exception {

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        ConfigurableEnvironment environment = context.getEnvironment();
//
//        environment.setActiveProfiles("PROD");
//        context.register(MainSpringConfiguration.class);
//        context.refresh();


        SpringApplication application = new SpringApplication(Main.class);
        ConfigurableApplicationContext context = application.run(args);

//        BaseBillRepository billRepository = context.getBean(BaseBillRepository.class);
//        BaseUserRepository userRepository = context.getBean(BaseUserRepository.class);
        TestovaciRepository testovaciRepository = context.getBean(TestovaciRepository.class);

//        TestovaciService testovaciService= new TestovaciService(context.getBean(TestovaciRepository.class));

        Iterable<Testovaci> testovacis = testovaciRepository.findAll();
        System.out.println(testovacis);
        System.out.println("---------------------------------------");
        System.out.println(testovaciRepository.exists(4));
        System.out.println("---------------------------------------");
        Testovaci t = new Testovaci("Test JPA", 12345, "Petr JPA");
//        <Testovaci extends>
//        testovaciRepository.save(t);
                System.out.println(testovaciRepository.save(t));
                System.out.println(testovaciRepository.save(t).gettitle());
                System.out.println(testovaciRepository.save(t).getId_testovaci());
//        List<Testovaci> testovacis1 = testovaciService.getAllTestovaci();
//        System.out.println(testovacis1);
        System.out.println("---------------------------------------");
        context.getBean(TestovaciService.class).getAllTestovaci().forEach(System.out::println);

        System.out.println("---------------------------------------");
        System.out.println("testovaciRepository.findTitleById(26) = " + testovaciRepository.findTitleById(26));
        OffsetTime ot1 = OffsetTime.now();
        OffsetDateTime a = OffsetDateTime.now();


        System.out.println("========================== Autor =======================");
        AutorRepository autorRepository = context.getBean(AutorRepository.class);



        System.out.println("========================== Picture =======================");
        PictureRepository pictureRepository = context.getBean(PictureRepository.class);
        System.out.println("pictureRepository.findAll() = " + pictureRepository.findAll());
        System.out.println("pictureRepository.incrementNLike(12) = " + pictureRepository.incrementNLike(12,"lastUpdate"));
        //
// pictureRepository.incrementNLike()


//        System.out.println("Current  offset  time: " + a);
//        System.out.println("Current  offset  time: " + a.toEpochSecond());
//
//        System.out.println(Instant.now().toEpochMilli()+ot1.getSecond());
//
////        Provisioner p = new Provisioner();
////        p.doProvision();
//
//        SpringApplication app = new SpringApplication(Main.class);
//        ApplicationContext ctx = app.run(args);
//
////        TestovaciService testovaciDao = ctx.getBean(TestovaciService.class);
////        testovaciDao.incrementNLike(9);
//        OffsetDateTime odt = OffsetDateTime.now();
//
//        PictureService pictureService = ctx.getBean(PictureService.class);
//        AutorService autorService = ctx.getBean(AutorService.class);
//        CommentService commentService = ctx.getBean(CommentService.class);
//        pictureService.incrementNLike(5);
//
//        List<Picture> pictures = pictureService.getAllPictures();
//
//        Picture p1 = pictures.get(5);
//        System.out.println(p1.toString());
//        System.out.println(p1.getAutor().toString());
//
//        Picture p2 = pictureService.getPicture(6);
//        System.out.println(p2.toString());
//        System.out.println(p2.getAutor().toString());

//        Picture p3 = pictureService.getPicture(5);
//        Autor a1 = autorService.getAutor(2);
//
//        Comment comment = new Comment(1, 2, "Hibernate text komentare", "Titulek Hibernate",odt.toEpochSecond()+"");
//        comment.setPicture(p3);
//        comment.setAutor(a1);
//        commentService.create(comment);

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    }

}