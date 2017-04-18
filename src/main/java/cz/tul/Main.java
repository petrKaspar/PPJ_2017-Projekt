package cz.tul;

import cz.tul.data.*;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.*;

import static org.junit.Assert.assertTrue;

@SpringBootApplication
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

    @Profile({"devel", "test"})
    @Bean(initMethod = "doProvision")
    public Provisioner provisioner() {
        return new Provisioner();
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
        OffsetDateTime odt = OffsetDateTime.now();;
        PictureDao pictureDao = ctx.getBean(PictureDao.class);
        Picture picture = new Picture(2, "http://url.cz", "pokus 1", odt.toEpochSecond()+"");
        pictureDao.create(picture);
        pictureDao.incrementNLike(1);
        pictureDao.incrementNDislike(1);
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