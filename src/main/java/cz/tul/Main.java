package cz.tul;

import cz.tul.data.*;
import cz.tul.provisioning.Provisioner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

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


    }

}