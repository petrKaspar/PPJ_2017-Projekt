package cz.tul;

import cz.tul.data.Author;
import cz.tul.services.AuthorService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringBootRestApplication.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorDaoTests {

    private static final Logger log = LoggerFactory.getLogger(SpringBootRestApplication.class);

    @Autowired
    private AuthorService authorService;

    @Before
    public void init() {
        authorService.deleteAuthors();
    }

    @Test
    public void testCreateAuthor(){

        Author author1 = new Author("pepa JPA test", LocalDateTime.now());

        authorService.create(author1);
        List<Author> authors = authorService.getAllAuthors();

        assertEquals("Should be one author.", 1, authors.size());
        assertEquals("Retrieved author name should equal inserted author name.", author1.getName(),
                authors.get(0).getName());
    }

    @Test
    public void testGetById(){
        Author autor1 = new Author("Petr JPA", LocalDateTime.now());
        int key = authorService.create(autor1);
        assertEquals("Retrieved author id should equal inserted author id.", key, authorService.getAuthor(key).getAuthorId());
    }

    @Test
    public void testAuthorExists() {
        authorService.deleteAuthors();

        Author author1 = new Author("Pepek JPA test", LocalDateTime.now());
        int key = authorService.create(author1);

        assertTrue("Author should exist with id " + key, authorService.exists(author1.getAuthorId()));
        assertFalse("Author should not exist", authorService.exists(666));
    }

    @Test
    public void testDeleteAuthor() {

        authorService.deleteAuthors();

        Author autor1 = new Author("Franta", LocalDateTime.now());
        Author autor2 = new Author("Pepa", LocalDateTime.now());
        Author autor3 = new Author("Pavel", LocalDateTime.now());
        Author autor4 = new Author("Petr", LocalDateTime.now());

        authorService.create(autor1);
        authorService.create(autor2);
        authorService.create(autor3);
        authorService.create(autor4);

        List<Author> authors = authorService.getAllAuthors();
        assertEquals("Should be four author.", 4, authors.size());

        authorService.deleteAuthor(authors.get(0));
        authorService.deleteAuthor(authors.get(1));

        authors = authorService.getAllAuthors();
        assertEquals("Should be two author.", 2, authors.size());

    }

}
