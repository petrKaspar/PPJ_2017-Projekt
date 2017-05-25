package cz.tul;

import cz.tul.data.*;
import cz.tul.services.AuthorService;
import cz.tul.services.PictureService;
import cz.tul.services.TagService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private TagService tagService;

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
        Author author1 = new Author("Franta", LocalDateTime.now());
        Author author2 = new Author("Pepa", LocalDateTime.now());
        Author author3 = new Author("Pavel", LocalDateTime.now());
        Author author4 = new Author("Petr", LocalDateTime.now());
        authorService.create(author1);
        authorService.create(author2);
        authorService.create(author3);
        authorService.create(author4);

        Picture picture  = new Picture(author1, "http://url.cz", "pokus 1", LocalDateTime.now());
        Picture picture1 = new Picture(author2, "http://url.cz", "pokus 2", LocalDateTime.now());
        Picture picture2 = new Picture(author3, "http://url.cz", "pokus 3", LocalDateTime.now());
        Picture picture3 = new Picture(author4, "http://url.cz", "pokus 4", LocalDateTime.now());
        pictureService.create(picture);
        pictureService.create(picture1);
        pictureService.create(picture2);
        pictureService.create(picture3);

        List<Picture> pictures = pictureService.getAllPictures();
        assertEquals("Should be four pictures.", 4, pictures.size());

        pictureService.deletePicture(pictures.get(0));
        pictureService.deletePicture(pictures.get(1));

        pictures = pictureService.getAllPictures();
        assertEquals("Should be two pictures.", 2, pictures.size());

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

    @Test
    public void testFindPictureByTitle() throws SQLException {

        pictureService.deletePictures();
        Author author = new Author("Franta JPA testP", LocalDateTime.now());
        authorService.create(author);

        Picture picture  = new Picture(author, "http://url.cz", "titulek 1", LocalDateTime.now());
        Picture picture1 = new Picture(author, "http://url.cz", "titulek 2", LocalDateTime.now());
        Picture picture2 = new Picture(author, "http://url.cz", "titulek 3", LocalDateTime.now());
        Picture picture3 = new Picture(author, "http://url.cz", "pokus 4", LocalDateTime.now());
        pictureService.create(picture);
        pictureService.create(picture1);
        pictureService.create(picture2);
        pictureService.create(picture3);

        List<Picture> pictures1 = pictureService.getPicturesByTitle("tul");
        List<Picture> pictures2 = pictureService.getPicturesByTitle("3");
        List<Picture> pictures3 = pictureService.getPicturesByTitle("titulek 2");

        assertEquals("Should be three pictures.", 3, pictures1.size());
        assertEquals("Should be one pictures.", 1, pictures2.size());
        assertEquals("Pictures title should be 'titulek 2'.", "titulek 2", pictures3.get(0).getTitle());

    }

    @Test
    public void testFindPictureByAuthor() throws SQLException {

        pictureService.deletePictures();
        Author author1 = new Author("Franta", LocalDateTime.now());
        Author author2 = new Author("Pepa", LocalDateTime.now());
        Author author3 = new Author("Pavel", LocalDateTime.now());
        authorService.create(author1);
        authorService.create(author2);
        authorService.create(author3);

        Picture picture  = new Picture(author1, "http://url.cz", "pokus 1", LocalDateTime.now());
        Picture picture1 = new Picture(author2, "http://url.cz", "pokus 2", LocalDateTime.now());
        Picture picture2 = new Picture(author3, "http://url.cz", "pokus 3", LocalDateTime.now());
        Picture picture3 = new Picture(author2, "http://url.cz", "pokus 4", LocalDateTime.now());
        pictureService.create(picture);
        pictureService.create(picture1);
        pictureService.create(picture2);
        pictureService.create(picture3);

        List<Picture> pictures1 = pictureService.getPicturesByAuthor(author1);
        List<Picture> pictures2 = pictureService.getPicturesByAuthor(author2);

        assertEquals("Should be one pictures.", 1, pictures1.size());
        assertEquals("Should be two pictures.", 2, pictures2.size());

    }

    @Test
    public void testFindPictureByTag() throws SQLException {
        pictureService.deletePictures();

        Author author1 = new Author("Franta", LocalDateTime.now());
        authorService.create(author1);

        Tag tag1 = new Tag("krajina");
        Tag tag2 = new Tag("budova");
        Tag tag3 = new Tag("osoba");
        Tag tag4 = new Tag("auto");
        Tag tag5 = new Tag("zvire");
        tagService.create(tag1);
        tagService.create(tag2);
        tagService.create(tag3);
        tagService.create(tag4);
        tagService.create(tag5);

        Set<Tag> sss = new HashSet<Tag>(){{
            add(tag3);
            add(tag1);
        }};
        Set<Tag> sss1 = new HashSet<Tag>(){{
            add(tag1);
        }};
        Set<Tag> sss2 = new HashSet<Tag>(){{
            add(tag5);
            add(tag2);
        }};
        Set<Tag> sss3 = new HashSet<Tag>(){{
            add(tag1);
            add(tag2);
            add(tag3);
            add(tag4);
        }};

        Picture picture  = new Picture(author1, "http://url.cz", "pokus 1", LocalDateTime.now(), sss);
        Picture picture1 = new Picture(author1, "http://url.cz", "pokus 2", LocalDateTime.now(), sss1);
        Picture picture2 = new Picture(author1, "http://url.cz", "pokus 3", LocalDateTime.now(), sss2);
        Picture picture3 = new Picture(author1, "http://url.cz", "pokus 4", LocalDateTime.now(), sss3);
        pictureService.create(picture);
        pictureService.create(picture1);
        pictureService.create(picture2);
        pictureService.create(picture3);

        List<Picture> pictures1 = pictureService.getPicturesByTag(tag1);
        List<Picture> pictures2 = pictureService.getPicturesByTag(tag5);

        assertEquals("Should be three pictures in list.", 3, pictures1.size());
        assertEquals("Should be one pictures in list.", 1, pictures2.size());

    }

}
