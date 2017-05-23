package cz.tul;

import cz.tul.data.Author;
import cz.tul.data.Comment;
import cz.tul.data.Picture;
import cz.tul.services.AuthorService;
import cz.tul.services.CommentService;
import cz.tul.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpringBootRestApplication {

private static final Logger logger = LoggerFactory.getLogger(SpringBootRestApplication.class);

    public static void main(String[] args)  {

        SpringApplication.run(SpringBootRestApplication.class, args);

    }

    @Autowired
    private AuthorService authorService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private CommentService commentService;

    // Initialize Sample Data
    // Initializing our database with sample data using the interface CommandLineRunner.
    // This interface is used to indicate that a bean should run when it is contained within a SpringApplication.
    @Profile({"devel"})
    @Bean
    public CommandLineRunner setup() {
        return (args) -> {

            authorService.create(new Author("Petr", LocalDateTime.now()));
            authorService.create(new Author("Pavel", LocalDateTime.now()));
            authorService.create(new Author("Jirka", LocalDateTime.now()));
            authorService.create(new Author("Honza", LocalDateTime.now()));
            authorService.create(new Author("Franta", LocalDateTime.now()));
            authorService.create(new Author("Josef", LocalDateTime.now()));
            authorService.create(new Author("Kveta", LocalDateTime.now()));
            authorService.create(new Author("Jirina", LocalDateTime.now()));

            pictureService.create(new Picture(authorService.getAuthor(1), "http://url.cz", "Titulek Obrazku 1", LocalDateTime.now()));
            pictureService.create(new Picture(authorService.getAuthor(2), "http://url.cz", "Druhy titulek obrazku", LocalDateTime.now()));
            pictureService.create(new Picture(authorService.getAuthor(1), "http://url.cz", "Uzasny titulek 3", LocalDateTime.now()));
            pictureService.create(new Picture(authorService.getAuthor(1), "http://url.cz", "Dalsi titulek", LocalDateTime.now()));
            pictureService.create(new Picture(authorService.getAuthor(3), "http://url.cz", "Toto je obrazek", LocalDateTime.now()));
            pictureService.create(new Picture(authorService.getAuthor(2), "http://url.cz", "Krasny obrazek", LocalDateTime.now()));

            commentService.create(new Comment(pictureService.getPicture(1), authorService.getAuthor(1), "text komentare", "Titulek komentare", LocalDateTime.now()));
            commentService.create(new Comment(pictureService.getPicture(3), authorService.getAuthor(4), "text komentare", "Titulek komentare", LocalDateTime.now()));
            commentService.create(new Comment(pictureService.getPicture(3), authorService.getAuthor(3), "text komentare", "Titulek komentare", LocalDateTime.now()));
            commentService.create(new Comment(pictureService.getPicture(2), authorService.getAuthor(4), "text komentare", "Titulek komentare", LocalDateTime.now()));
            commentService.create(new Comment(pictureService.getPicture(4), authorService.getAuthor(2), "text komentare", "Titulek komentare", LocalDateTime.now()));

            logger.info("The sample data has been generated");
        };
    }


}