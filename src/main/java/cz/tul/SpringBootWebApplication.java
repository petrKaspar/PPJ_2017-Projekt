package cz.tul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("cz.tul.services")
public class SpringBootWebApplication {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(SpringBootWebApplication.class, args);

    }

}