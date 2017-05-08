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
    public void testCreatePicture() throws SQLException {

        pictureDao.deletePictures();

        Author author = new Author("Franta", new Date());
        int authorKey = authorDao.create(author);

        Picture picture = new Picture(authorKey, "http://url.cz", "test JDBC", new Date());
        int pictureKey = pictureDao.create(picture);

        List<Picture> pictures = pictureDao.getAllPictures();
        assertEquals("Should be one comment in database.", 1, pictures.size());
    }

    @Test
    public void testDeletePicture() throws SQLException {

        pictureDao.deletePictures();
        Author author = new Author("Franta", new Date());
        int authorKey = authorDao.create(author);

        Picture picture = new Picture(authorKey, "http://url.cz", "pokus 1", new Date());
        Picture picture1 = new Picture(authorKey, "http://url.cz", "pokus 1", new Date());
        Picture picture2 = new Picture(authorKey, "http://url.cz", "pokus 1", new Date());
        Picture picture3 = new Picture(authorKey, "http://url.cz", "pokus 1", new Date());
        pictureDao.create(picture);
        pictureDao.create(picture1);
        pictureDao.create(picture2);
        pictureDao.create(picture3);

        List<Picture> pictures = pictureDao.getAllPictures();
        assertEquals("Should be four comments.", 4, pictures.size());

        pictureDao.deletePicture(pictures.get(0).getPictureId());
        pictureDao.deletePicture(pictures.get(1).getPictureId());

        pictures = pictureDao.getAllPictures();
        assertEquals("Should be two commets.", 2, pictures.size());

    }

    @Test
    public void testAddLike() throws SQLException {
        pictureDao.deletePictures();

        Author author = new Author("Franta", new Date());
        int authorKey = authorDao.create(author);

        Picture picture = new Picture(authorKey, "http://url.cz", "pokus 1", new Date());
        int pictureKey = pictureDao.create(picture);

        List<Picture> pictures = pictureDao.getAllPictures();


        int nLike = pictures.get(pictures.size()-1).getNlike();
        int nDislike = pictures.get(pictures.size()-1).getNdislike();
        Date lastUpdate = pictures.get(pictures.size()-1).getLastUpdate();

        pictureDao.incrementNLike(pictureKey);

        Picture p = pictureDao.getAllPictures().get(pictures.size()-1);

        assertEquals("obr.getNlike() Should be nLike + 1.", nLike + 1, p.getNlike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, p.getLastUpdate());

    }

    @Test
    public void testAddDislike() throws SQLException {
        pictureDao.deletePictures();

        Author author = new Author("Franta", new Date());
        int authorKey = authorDao.create(author);

        Picture picture = new Picture(authorKey, "http://url.cz", "pokus 1", new Date());
        int pictureKey = pictureDao.create(picture);

        List<Picture> pictures = pictureDao.getAllPictures();


        int nDislike = pictures.get(pictures.size()-1).getNdislike();
        Date lastUpdate = pictures.get(pictures.size()-1).getLastUpdate();

        pictureDao.incrementNLike(pictureKey);

        Picture p = pictureDao.getAllPictures().get(pictures.size()-1);

        assertEquals("obr.getNlike() Should be nLike + 1.", nDislike + 1, p.getNdislike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, p.getLastUpdate());

    }


}
