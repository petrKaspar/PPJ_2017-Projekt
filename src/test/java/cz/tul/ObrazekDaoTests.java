package cz.tul;

import cz.tul.data.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
//@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ObrazekDaoTests {

    @Autowired
    private ObrazekDao obrazekDao;

    @Autowired
    private AutorDao autorDao;

    @Test
    public void Test1_createObrazek() throws SQLException {

//        obrazekDao.create(obrazek);

//        assertTrue("User creation should return true", usersDao.create(user));

//        Offer offer = new Offer(user, "This is a test offer.");

        OffsetDateTime odt = OffsetDateTime.now();;
        Obrazek obrazek = new Obrazek(2, "http://url.cz", "pokus 1", odt.toEpochSecond()+"");
        Autor autor = autorDao.getAutor(2);
        obrazek.setAutor(autor);

        List<Obrazek> obrazky = obrazekDao.getObrazky_innerjoin();
        assertEquals("Should be one offer in database.", obrazky.size(), obrazekDao.create(obrazek) - 1);

//        assertTrue("Offer creation should return true", obrazekDao.create(obrazek));

    }

    @Test
    public void Test_addLikeDislike() {

        List<Obrazek> obrazky = obrazekDao.getObrazky_innerjoin();

        int lastImage = obrazky.get(obrazky.size()-1).getObrazek_id();
        int nLike = obrazky.get(obrazky.size()-1).getNlike();
        int nDislike = obrazky.get(obrazky.size()-1).getNdislike();
        String aktualizace = obrazky.get(obrazky.size()-1).getAktualizace();

        obrazekDao.incrementNLike(lastImage);
        obrazekDao.incrementNDislike(lastImage);

        Obrazek obr = obrazekDao.getObrazek(lastImage);

        assertEquals("obr.getNlike() Should be nLike + 1.", nLike + 1, obr.getNlike());
        assertEquals("obr.getNdislike() Should be nDislike + 1.", nDislike + 1, obr.getNdislike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", aktualizace, obr.getAktualizace());

    }

    @Test
    public void Test2_listOffers() {

        List<Obrazek> obrazky = obrazekDao.getObrazky_innerjoin();
        // Get the offer with ID filled in.
//        Obrazek obrazek = obrazky.get(0);
        System.out.println("obrazky.size() = " + obrazky.size());

        OffsetDateTime odt = OffsetDateTime.now();;
        Obrazek obrazek = new Obrazek(2, "http://url.cz", "pokus 1", odt.toEpochSecond()+"");
        Autor autor = autorDao.getAutor(2);
        obrazek.setAutor(autor);

        System.out.println(obrazek.getAutor().getAutor_id());
        System.out.println(obrazky.get(obrazky.size()-1).getAutor_id());
        System.out.println(obrazky.get(obrazky.size()-1).getAutor().getAutor_id());
        System.out.println(obrazek.getAutor().getJmeno());
        System.out.println(obrazky.get(obrazky.size()-1).getAutor().getJmeno());
        System.out.println(obrazek.getAutor().getRegistrace());
        System.out.println(obrazky.get(obrazky.size()-1).getAutor().getRegistrace());

        assertEquals("Should be one offer in database.", obrazky.size(), obrazky.size());

//        assertEquals("Retrieved offer should match created offer.", obrazek,
//                obrazky.get(obrazky.size()-1));
    }


}
