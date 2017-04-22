package cz.tul;

import cz.tul.data.Autor;
import cz.tul.data.AutorDao;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
//@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutorDaoTests {


    @Autowired
    private AutorDao autorDao;

    @Test
    public void testUsers() {
        OffsetDateTime odt = OffsetDateTime.now();;

        Autor a = new Autor("Franta hibernate", odt.toEpochSecond()+"");

        List<Autor> autors = autorDao.getAllAutors();
        int nAutors = autors.size();

        int key = autorDao.create(a);
        a.setAutor_id(key);
        autors = autorDao.getAllAutors();

        System.out.println(a.toString());
        System.out.println(autors.get(key-1).toString());

        assertEquals("Created user should be identical to retrieved user", nAutors + 1, autors.size());

        Autor a1 = autorDao.getAutor(1);
        assertEquals("Created user ID should be identical to retrieved user ID", a1.getAutor_id(), 1);

//        assertEquals("Created user should be identical to retrieved user", a, autors.get(key-1));
//        assertSame("Created user should be identical to retrieved user", a, autors.get(key-1));



    }

    /*
    @Test
    public void testUsers() {

        List<Autor> autors = autorDao.getAllAutors();
        int nAutors = autors.size();
        OffsetDateTime odt = OffsetDateTime.now();;
        Autor a = new Autor("Franta", odt.toEpochSecond()+"");

        int key = autorDao.create(a);
        a.setAutor_id(key);
        autors = autorDao.getAllAutors();

        System.out.println(a.toString());
        System.out.println(autors.get(key-1).toString());

        assertEquals("Created user should be identical to retrieved user", nAutors + 1, autors.size());

        Autor a1 = autorDao.getAutor(1);
        assertEquals("Created user ID should be identical to retrieved user ID", a1.getAutor_id(), 1);

//        assertEquals("Created user should be identical to retrieved user", a, autors.get(key-1));
//        assertSame("Created user should be identical to retrieved user", a, autors.get(key-1));



    }
*/

}
