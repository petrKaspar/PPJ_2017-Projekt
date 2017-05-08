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

@SpringBootApplication
@EnableTransactionManagement    // abych mohl vyuzivat nad entitama 2transactional
@EntityScan("cz.tul.data")
public class Main {

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
        SpringApplication app = new SpringApplication(Main.class);
        ApplicationContext ctx = app.run(args);

    }

}