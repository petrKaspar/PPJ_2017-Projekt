package cz.tul;

import cz.tul.data.*;
import cz.tul.provisioning.Provisioner;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.time.*;
import java.util.List;

import static org.junit.Assert.assertTrue;

@SpringBootApplication
@EnableTransactionManagement    // abych mohl vyuzivat nad entitama 2transactional
@EntityScan("cz.tul.data")
public class Main {

    @Bean
    public TestovaciDao testovaciDao() {
        return new TestovaciDao();
    }

    @Bean
    public PictureDao pictureDao() {
        return new PictureDao();
    }

    @Bean
    public AutorDao autorDao() {
        return new AutorDao();
    }

    @Bean
    public CommentDao commentDao() {
        return new CommentDao();
    }

//    @Profile({"devel", "test"})
//    @Bean(initMethod = "doProvision")
//    public Provisioner provisioner() {
//        return new Provisioner();
//    }


    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new HibernateTransactionManager(entityManagerFactory.unwrap(SessionFactory.class));
    }

    public static void main(String[] args) throws Exception {

        OffsetTime ot1 = OffsetTime.now();
        OffsetDateTime a = OffsetDateTime.now();
        System.out.println("Current  offset  time: " + a);
        System.out.println("Current  offset  time: " + a.toEpochSecond());

        System.out.println(Instant.now().toEpochMilli()+ot1.getSecond());

//        Provisioner p = new Provisioner();
//        p.doProvision();

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

//        TestovaciDao testovaciDao = ctx.getBean(TestovaciDao.class);
//        testovaciDao.incrementNLike(9);

        PictureDao pictureDao = ctx.getBean(PictureDao.class);
        pictureDao.incrementNLike(5);

        List<Picture> pictures = pictureDao.getAllPictures();

        Picture p1 = pictures.get(5);
        System.out.println(p1.toString());
        System.out.println(p1.getAutor().toString());

        Picture p2 = pictureDao.getPicture(6);
        System.out.println(p2.toString());
        System.out.println(p2.getAutor().toString());

//        UsersDao usersDao = ctx.getBean(UsersDao.class);
//
//        List<User> users = usersDao.getAllUsers();
//        System.out.println("users " + users);

//        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//       TestovaciDao testovaciDao = ctx.getBean(TestovaciDao.class);
//       Testovaci testovaci = new Testovaci("ahoj", 7, "Petr");
//       testovaciDao.create(testovaci);
//       testovaciDao.incrementPpocet(5);
//        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        OffsetDateTime odt = OffsetDateTime.now();;
//        PictureDao pictureDao = ctx.getBean(PictureDao.class);
//        Picture picture = new Picture(2, "http://url.cz", "pokus 1", odt.toEpochSecond()+"");
//        pictureDao.create(picture);
//        pictureDao.incrementNLike(1);
//        pictureDao.incrementNDislike(1);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        PictureDao pictureDao = ctx.getBean(PictureDao.class);
//        Picture picture = pictureDao.getPicture(2);
//        System.out.println(picture.toString());
//        System.out.println(picture.getAutor().getname());

//        List...pictureDao.getPictures_innerjoin();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    }

}