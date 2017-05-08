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

        OffsetDateTime odt = OffsetDateTime.now();
        Picture picture = new Picture("http://url.cz", "pokus 1", odt.toEpochSecond()+"");
        Author author = authorDao.getAuthor(2);
        picture.setAuthor(author);

        List<Picture> pictures = pictureDao.getAllPictures();
        assertEquals("Should be one offer in database.", pictures.size(), pictureDao.create(picture) - 1);

    }

    @Test
    public void Test_addLikeDislike() {

        List<Picture> pictures = pictureDao.getAllPictures();

        int lastImage = pictures.get(pictures.size()-1).getPictureId();
        int nLike = pictures.get(pictures.size()-1).getNlike();
        int nDislike = pictures.get(pictures.size()-1).getNdislike();
        String lastUpdate = pictures.get(pictures.size()-1).getLastUpdate();

        pictureDao.incrementNLike(lastImage);
        pictureDao.incrementNDislike(lastImage);

        Picture obr = pictureDao.getPicture(lastImage);

        assertEquals("obr.getNlike() Should be nLike + 1.", nLike + 1, obr.getNlike());
        assertEquals("obr.getNdislike() Should be nDislike + 1.", nDislike + 1, obr.getNdislike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, obr.getLastUpdate());

    }

    @Test
    public void Test2_listOffers() {

        List<Picture> pictures = pictureDao.getAllPictures();
        System.out.println("pictures.size() = " + pictures.size());

        OffsetDateTime odt = OffsetDateTime.now();;
        Picture picture = new Picture("http://url.cz", "pokus 1", odt.toEpochSecond()+"");
        Author author = authorDao.getAuthor(2);
        picture.setAuthor(author);

        assertEquals("Should be one offer in database.", pictures.size(), pictures.size());

    }


}
