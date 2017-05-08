package cz.tul;

import cz.tul.data.Author;
import cz.tul.data.AuthorDao;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorDaoTests {


    @Autowired
    private AuthorDao authorDao;

    @Test
    public void testUsers() {
        OffsetDateTime odt = OffsetDateTime.now();;

        Author a = new Author("Franta hibernate", odt.toEpochSecond()+"");

        List<Author> authors = authorDao.getAllAuthors();
        int nAuthors = authors.size();

        int key = authorDao.create(a);
        a.setAuthorId(key);
        authors = authorDao.getAllAuthors();

        assertEquals("Created user should be identical to retrieved user", nAuthors + 1, authors.size());

        Author a1 = authorDao.getAuthor(1);
        assertEquals("Created user ID should be identical to retrieved user ID", a1.getAuthorId(), 1);

    }

}
