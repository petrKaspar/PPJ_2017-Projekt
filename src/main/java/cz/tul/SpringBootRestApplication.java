package cz.tul;

import cz.tul.data.*;
import cz.tul.services.AuthorService;
import cz.tul.services.CommentService;
import cz.tul.services.PictureService;
import cz.tul.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
    @Autowired
    private TagService tagService;

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

            Tag tag1 = new Tag("krajina");
            Tag tag2 = new Tag("budova");
            Tag tag3 = new Tag("osoba");
            Tag tag4 = new Tag("auto");
            Tag tag5 = new Tag("zvire");
            Tag tag6 = new Tag("mesto");
            Tag tag7 = new Tag("strom");
            Tag tag8 = new Tag("rostlina");

            tagService.create(tag1);
            tagService.create(tag2);
            tagService.create(tag3);
            tagService.create(tag4);
            tagService.create(tag5);
            tagService.create(tag6);
            tagService.create(tag7);
            tagService.create(tag8);

            Set<Tag> sss = new HashSet<Tag>(){{
                add(tag3);
                add(tag7);
            }};
            Set<Tag> sss1 = new HashSet<Tag>(){{
                add(tag1);
            }};
            Set<Tag> sss2 = new HashSet<Tag>(){{
                add(tag5);
                add(tag7);
            }};

            Picture picture1 = new Picture(authorService.getAuthor(1), "http://url.cz", "Titulek Obrazku 1", LocalDateTime.now(), sss);
            Picture picture2 = new Picture(authorService.getAuthor(2), "http://url.cz", "Druhy titulek obrazku", LocalDateTime.now(),sss1);
            Picture picture3 = new Picture(authorService.getAuthor(1), "http://url.cz", "Uzasny titulek 3", LocalDateTime.now(),sss2);

            pictureService.create(picture1);
            pictureService.create(picture2);
            pictureService.create(picture3);

            pictureService.create(new Picture(authorService.getAuthor(1), "http://url.cz", "Uzasny titulek 3", LocalDateTime.now(), new HashSet<Tag>(){{
                add(tag3);
                add(tag2);
            }}));
            pictureService.create(new Picture(authorService.getAuthor(1), "http://url.cz", "Dalsi titulek", LocalDateTime.now(), new HashSet<Tag>(){{
                add(tag6);
            }}));
            pictureService.create(new Picture(authorService.getAuthor(3), "http://url.cz", "Toto je obrazek", LocalDateTime.now(), new HashSet<Tag>(){{
                add(tag5);
                add(tag8);
            }}));
            pictureService.create(new Picture(authorService.getAuthor(2), "http://url.cz", "Krasny obrazek", LocalDateTime.now(), new HashSet<Tag>(){{
                add(tag1);
                add(tag5);
                add(tag8);
            }}));

            Picture picture8 = new Picture(authorService.getAuthor(4), "http://url.cz", "Titulek Obrazku 8", LocalDateTime.now());
            Picture picture9 = new Picture(authorService.getAuthor(2), "http://url.cz", "9. titulek obrazku", LocalDateTime.now());
            Picture picture10 = new Picture(authorService.getAuthor(3), "http://url.cz", "Uzasny titulek 10", LocalDateTime.now());

            picture8.getTagSet().add(tag1);
            picture8.getTagSet().add(tag3);
            picture8.getTagSet().add(tag5);
            picture8.getTagSet().add(tag7);

            picture9.getTagSet().add(tag6);

            Set<Tag> set3 = new HashSet<Tag>(){{
                add(tag1);
                add(tag2);
                add(tag3);
                add(tag4);
                add(tag5);
                add(tag6);
                add(tag7);
                add(tag8);
            }};
            picture10.setTagSet(set3);

            pictureService.create(picture8);
            pictureService.create(picture9);
            pictureService.create(picture10);

            commentService.create(new Comment(pictureService.getPicture(1), authorService.getAuthor(1), "text komentare", "Titulek komentare", LocalDateTime.now()));
            commentService.create(new Comment(pictureService.getPicture(3), authorService.getAuthor(4), "text komentare", "Titulek komentare", LocalDateTime.now()));
            commentService.create(new Comment(pictureService.getPicture(3), authorService.getAuthor(3), "text komentare", "Titulek komentare", LocalDateTime.now()));
            commentService.create(new Comment(pictureService.getPicture(2), authorService.getAuthor(4), "text komentare", "Titulek komentare", LocalDateTime.now()));
            commentService.create(new Comment(pictureService.getPicture(4), authorService.getAuthor(2), "text komentare", "Titulek komentare", LocalDateTime.now()));

            logger.info("The sample data has been generated");
        };
    }


}