package cz.tul;

import cz.tul.data.*;
import cz.tul.services.AuthorService;
import cz.tul.services.PictureService;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {SpringBootRestApplication.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PictureServiceTests {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private AuthorService authorService;

    @Test
    public void testCreatePicture() throws SQLException {

        pictureService.deletePictures();

        Author author = new Author("Franta JPA testP", LocalDateTime.now());
        int authorKey = authorService.create(author);
        author.setAuthorId(authorKey);

        Picture picture = new Picture(author, "http://url.cz", "test JPA", LocalDateTime.now());
        pictureService.create(picture);

        List<Picture> pictures = pictureService.getAllPictures();
        assertEquals("Should be one comment in database.", 1, pictures.size());
    }

    @Test
    public void testPictureExists() {
        pictureService.deletePictures();

        Author author = new Author("Franta JPA testP", LocalDateTime.now());
        author.setAuthorId(authorService.create(author));

        Picture picture = new Picture(author, "http://url.cz", "test JPA", LocalDateTime.now());
        pictureService.create(picture);

        assertTrue("Picture should exist with id 1", pictureService.exists(picture.getPictureId()));
        assertFalse("Picture should not exist", pictureService.exists(666));
    }

    @Test
    public void testDeletePicture() throws SQLException {

        pictureService.deletePictures();
        Author author = new Author("Franta JPA testP", LocalDateTime.now());
        authorService.create(author);

        Picture picture  = new Picture(author, "http://url.cz", "pokus 1", LocalDateTime.now());
        Picture picture1 = new Picture(author, "http://url.cz", "pokus 2", LocalDateTime.now());
        Picture picture2 = new Picture(author, "http://url.cz", "pokus 3", LocalDateTime.now());
        Picture picture3 = new Picture(author, "http://url.cz", "pokus 4", LocalDateTime.now());
        pictureService.create(picture);
        pictureService.create(picture1);
        pictureService.create(picture2);
        pictureService.create(picture3);

        List<Picture> pictures = pictureService.getAllPictures();
        assertEquals("Should be four comments.", 4, pictures.size());

        pictureService.deletePicture(pictures.get(0));
        pictureService.deletePicture(pictures.get(1));

        pictures = pictureService.getAllPictures();
        assertEquals("Should be two commets.", 2, pictures.size());

    }

    @Test
    public void testAddLike() throws SQLException {
        pictureService.deletePictures();

        Author author = new Author("Franta JPA testP", LocalDateTime.now());
        authorService.create(author);

        Picture picture = new Picture(author, "http://url.cz", "pokus 1", LocalDateTime.now());
        int pictureKey = pictureService.create(picture);

        List<Picture> pictures = pictureService.getAllPictures();


        int nLike = pictures.get(pictures.size()-1).getNlike();
        LocalDateTime lastUpdate = pictures.get(pictures.size()-1).getLastUpdate();

        pictureService.incrementNLike(pictureKey);

        Picture p = pictureService.getAllPictures().get(pictures.size()-1);

        assertEquals("obr.getNlike() Should be nLike + 1.", nLike + 1, p.getNlike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, p.getLastUpdate());

    }

    @Test
    public void testAddDislike() throws SQLException {
        pictureService.deletePictures();

        Author author = new Author("Franta JPA testP", LocalDateTime.now());
        authorService.create(author);

        Picture picture = new Picture(author, "http://url.cz", "pokus 1", LocalDateTime.now());
        int pictureKey = pictureService.create(picture);

        List<Picture> pictures = pictureService.getAllPictures();


        int nDislike = pictures.get(pictures.size()-1).getNdislike();
        LocalDateTime lastUpdate = pictures.get(pictures.size()-1).getLastUpdate();

        pictureService.incrementNDislike(pictureKey);

        Picture p = pictureService.getAllPictures().get(pictures.size()-1);

        assertEquals("obr.getNlike() Should be nLike + 1.", nDislike + 1, p.getNdislike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, p.getLastUpdate());

    }

}
