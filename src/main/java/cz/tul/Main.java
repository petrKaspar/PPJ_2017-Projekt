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
    public ObrazekDao obrazekDao() {
        return new ObrazekDao();
    }

    @Bean
    public AutorDao autorDao() {
        return new AutorDao();
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
//        OffsetDateTime odt = OffsetDateTime.now();;
//        ObrazekDao obrazekDao = ctx.getBean(ObrazekDao.class);
//        Obrazek obrazek = new Obrazek(2, "http://url.cz", "pokus 1", odt.toEpochSecond()+"");
//        obrazekDao.create(obrazek);
//        obrazekDao.incrementNLike(5);
//        obrazekDao.incrementNDislike(5);
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        ObrazekDao obrazekDao = ctx.getBean(ObrazekDao.class);
//        Obrazek obrazek = obrazekDao.getObrazek(2);
//        System.out.println(obrazek.toString());
//        System.out.println(obrazek.getAutor().getJmeno());

//        List...obrazekDao.getObrazky_innerjoin();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    }

}