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
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PictureDaoTests {

    @Autowired
    private PictureDao pictureDao;

    @Autowired
    private AuthorDao authorDao;

    @Test
    public void Test1_createPicture() throws SQLException {

//        pictureDao.create(picture);

//        assertTrue("User creation should return true", usersDao.create(user));

//        Offer offer = new Offer(user, "This is a test offer.");

        OffsetDateTime odt = OffsetDateTime.now();
//        Picture picture = new Picture(2, "http://url.cz", "pokus 1", odt.toEpochSecond()+"");
        Picture picture = new Picture(2, "http://url.cz", "pokus 1", new Date());
        Author author = authorDao.getAuthor(2);
        picture.setAuthor(author);

        List<Picture> pictures = pictureDao.getPictures_innerjoin();
        assertEquals("Should be one offer in database.", pictures.size(), pictureDao.create(picture) - 1);

//        assertTrue("Offer creation should return true", pictureDao.create(picture));

    }

    @Test
    public void Test_addLikeDislike() {

        List<Picture> pictures = pictureDao.getPictures_innerjoin();

        int lastImage = pictures.get(pictures.size()-1).getPictureId();
        int nLike = pictures.get(pictures.size()-1).getNlike();
        int nDislike = pictures.get(pictures.size()-1).getNdislike();
        Date lastUpdate = pictures.get(pictures.size()-1).getLastUpdate();

        pictureDao.incrementNLike(lastImage);
        pictureDao.incrementNDislike(lastImage);

        Picture obr = pictureDao.getPicture(lastImage);

        assertEquals("obr.getNlike() Should be nLike + 1.", nLike + 1, obr.getNlike());
        assertEquals("obr.getNdislike() Should be nDislike + 1.", nDislike + 1, obr.getNdislike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, obr.getLastUpdate());

    }

    @Test
    public void Test2_listOffers() {

        List<Picture> pictures = pictureDao.getPictures_innerjoin();
        // Get the offer with ID filled in.
//        Picture picture = pictures.get(0);
        System.out.println("pictures.size() = " + pictures.size());

        OffsetDateTime odt = OffsetDateTime.now();;
//        Picture picture = new Picture(2, "http://url.cz", "pokus 1", odt.toEpochSecond()+"");
        Picture picture = new Picture(2, "http://url.cz", "pokus 1", new Date());
        Author author = authorDao.getAuthor(2);
        picture.setAuthor(author);

        System.out.println(picture.getAuthor().getAuthorId());
        System.out.println(pictures.get(pictures.size()-1).getAuthorId());
        System.out.println(pictures.get(pictures.size()-1).getAuthor().getAuthorId());
        System.out.println(picture.getAuthor().getname());
        System.out.println(pictures.get(pictures.size()-1).getAuthor().getname());
        System.out.println(picture.getAuthor().getRegistration());
        System.out.println(pictures.get(pictures.size()-1).getAuthor().getRegistration());

        assertEquals("Should be one offer in database.", pictures.size(), pictures.size());

//        assertEquals("Retrieved offer should match created offer.", picture,
//                pictures.get(pictures.size()-1));
    }


}
