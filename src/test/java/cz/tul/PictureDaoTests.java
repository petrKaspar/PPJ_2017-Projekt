package cz.tul;

import cz.tul.data.*;
import cz.tul.service.AutorService;
import cz.tul.service.PictureService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
public class PictureDaoTests {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private AutorService autorService;

    @Test
    public void Test1_createPicture() throws SQLException {

//        pictureService.create(picture);

//        assertTrue("User creation should return true", usersDao.create(user));

//        Offer offer = new Offer(user, "This is a test offer.");

        OffsetDateTime odt = OffsetDateTime.now();
        Picture picture = new Picture(2, "http://url.cz", "pokus 1", odt.toEpochSecond()+"");
        Autor autor = autorService.getAutor(2);
        picture.setAutor(autor);

        List<Picture> pictures = pictureService.getAllPictures();
        assertEquals("Should be one offer in database.", pictures.size(), pictureService.create(picture) - 1);

//        assertTrue("Offer creation should return true", pictureService.create(picture));

    }

    @Test
    public void Test_addLikeDislike() {

        List<Picture> pictures = pictureService.getAllPictures();

        int lastImage = pictures.get(pictures.size()-1).getPicture_id();
        int nLike = pictures.get(pictures.size()-1).getNlike();
        int nDislike = pictures.get(pictures.size()-1).getNdislike();
        String lastUpdate = pictures.get(pictures.size()-1).getLastUpdate();

        pictureService.incrementNLike(lastImage);
        pictureService.incrementNDislike(lastImage);

        Picture obr = pictureService.getPicture(lastImage);

        assertEquals("obr.getNlike() Should be nLike + 1.", nLike + 1, obr.getNlike());
        assertEquals("obr.getNdislike() Should be nDislike + 1.", nDislike + 1, obr.getNdislike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, obr.getLastUpdate());

    }

    @Test
    public void Test2_listOffers() {

        List<Picture> pictures = pictureService.getAllPictures();
        // Get the offer with ID filled in.
//        Picture picture = pictures.get(0);
        System.out.println("pictures.size() = " + pictures.size());

        OffsetDateTime odt = OffsetDateTime.now();;
        Picture picture = new Picture(2, "http://url.cz", "pokus 1", odt.toEpochSecond()+"");
        Autor autor = autorService.getAutor(2);
        picture.setAutor(autor);

//        System.out.println(picture.getAutor().getAutor_id());
//        System.out.println(pictures.get(pictures.size()-1).getAutor_id());
//        System.out.println(pictures.get(pictures.size()-1).getAutor().getAutor_id());
//        System.out.println(picture.getAutor().getname());
//        System.out.println(pictures.get(pictures.size()-1).getAutor().getname());
//        System.out.println(picture.getAutor().getRegistration());
//        System.out.println(pictures.get(pictures.size()-1).getAutor().getRegistration());

        assertEquals("Should be one offer in database.", pictures.size(), pictures.size());

//        assertEquals("Retrieved offer should match created offer.", picture,
//                pictures.get(pictures.size()-1));
    }


}
