package cz.tul;

import cz.tul.data.Author;
import cz.tul.service.AuthorService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
//@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorDaoTests {

    @Autowired
    private AuthorService authorService;

    @Test
    public void testUsers() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Author a = new Author("Jarda JPA", localDateTime.getDayOfMonth()+"");

        List<Author> authors = authorService.getAllAuthors();
        int nAuthors = authors.size();
        int key = authorService.create(a);
        a.setAuthorId(key);
        authors = authorService.getAllAuthors();

        assertEquals("Created user should be identical to retrieved user", nAuthors + 1, authors.size());

        Author a1 = authorService.getAuthor(1);
        assertEquals("Created user ID should be identical to retrieved user ID", a1.getAuthorId(), 1);


    }

}