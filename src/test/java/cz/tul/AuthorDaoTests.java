package cz.tul;

import cz.tul.data.Author;
import cz.tul.data.AuthorDao;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorDaoTests {

    @Autowired
    private AuthorDao authorDao;

    @Before
    public void init() {
        authorDao.deleteAuthors();
    }

    @Test
    public void testCreateAuthor(){
        Author autor1 = new Author("Franta", new Date());

        authorDao.create(autor1);
        List<Author> authors = authorDao.getAllAuthors();

        assertEquals("Should be one author.", 1, authors.size());
        assertEquals("Retrieved author name should equal inserted author name.", autor1.getName(),
                authors.get(0).getName());

    }

    @Test
    public void testGetById(){
        Author autor1 = new Author("Petr", new Date());
        int key = authorDao.create(autor1);
        assertEquals("Retrieved author id should equal inserted author id.", key, authorDao.getAuthor(key).getAuthorId());
    }

    @Test
    public void testDeleteAuthor() {

        authorDao.deleteAuthors();

        Author autor1 = new Author("Franta", new Date());
        Author autor2 = new Author("Pepa", new Date());
        Author autor3 = new Author("Pavel", new Date());
        Author autor4 = new Author("Petr", new Date());

        authorDao.create(autor1);
        authorDao.create(autor2);
        authorDao.create(autor3);
        authorDao.create(autor4);

        List<Author> authors = authorDao.getAllAuthors();
        assertEquals("Should be four author.", 4, authors.size());

        authorDao.deleteAuthor(authors.get(0).getAuthorId());
        authorDao.deleteAuthor(authors.get(1).getAuthorId());

        authors = authorDao.getAllAuthors();
        assertEquals("Should be two author.", 2, authors.size());

    }


}
