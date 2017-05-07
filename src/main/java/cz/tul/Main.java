package cz.tul;

import cz.tul.data.*;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.*;
import java.util.Date;
import java.util.List;

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
    public AuthorDao authorDao() {
        return new AuthorDao();
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

        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

        OffsetTime ot1 = OffsetTime.now();
//        OffsetDateTime a = OffsetDateTime.now();
//        System.out.println("Current  offset  time: " + a);
//        System.out.println("Current  offset  time: " + a.toEpochSecond());

        System.out.println(Instant.now().toEpochMilli()+ot1.getSecond());

//        Provisioner p = new Provisioner();
//        p.doProvision();



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
        OffsetDateTime odt = OffsetDateTime.now();

        AuthorDao authorDao = ctx.getBean(AuthorDao.class);
        Author a = new Author("Franta", new Date());
        int key = authorDao.create(a);
        key = authorDao.create(a);
        key = authorDao.create(a);
        key = authorDao.create(a);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(key);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(authorDao.getAuthor(key).toString());


        PictureDao pictureDao = ctx.getBean(PictureDao.class);
        Picture picture = new Picture(key, "http://url.cz", "pokus 1", new Date());
        int keyPicture = pictureDao.create(picture);
        pictureDao.incrementNLike(keyPicture);
        pictureDao.incrementNDislike(keyPicture);
        System.out.println("-----------------------------------------");
        List<Picture> pictures = pictureDao.getPictures_innerjoin();
        // Get the offer with ID filled in.
//        Picture picture = pictures.get(0);
        System.out.println("pictures.size() = " + pictures.size());
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        PictureDao pictureDao = ctx.getBean(PictureDao.class);
//        Picture picture = pictureDao.getPicture(2);
//        System.out.println(picture.toString());
//        System.out.println(picture.getAuthor().getname());

//        List...pictureDao.getPictures_innerjoin();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    }

}