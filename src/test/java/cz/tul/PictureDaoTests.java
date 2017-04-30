package cz.tul;

import cz.tul.data.*;
import cz.tul.service.AuthorService;
import cz.tul.service.PictureService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.time.LocalDateTime;
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
    private AuthorService authorService;

    @Test
    public void Test1_createPicture() throws SQLException {

        LocalDateTime localDateTime = LocalDateTime.now();;

        Picture picture = new Picture(2, "http://url.cz", "pokus 1", localDateTime.toString());
        Author author = authorService.getAuthor(2);
        picture.setAuthor(author);

        List<Picture> pictures = pictureService.getAllPictures();
        assertEquals("Should be one offer in database.", pictures.size(), pictureService.create(picture) - 1);

    }

    @Test
    public void Test_addLike() {

        List<Picture> pictures = pictureService.getAllPictures();

        int lastImage = pictures.get(pictures.size()-1).getPictureId();
        int nLike = pictures.get(pictures.size()-1).getNlike();
        String lastUpdate = pictures.get(pictures.size()-1).getLastUpdate();

        pictureService.incrementNLike(lastImage);

        Picture obr = pictureService.getPicture(lastImage);

        assertEquals("obr.getNlike() Should be nLike + 1.", nLike + 1, obr.getNlike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, obr.getLastUpdate());

    }

    @Test
    public void Test_addDislike() {

        List<Picture> pictures = pictureService.getAllPictures();

        int lastImage = pictures.get(pictures.size()-1).getPictureId();
        int nDislike = pictures.get(pictures.size()-1).getNdislike();
        String lastUpdate = pictures.get(pictures.size()-1).getLastUpdate();

        pictureService.incrementNDislike(lastImage);

        Picture obr = pictureService.getPicture(lastImage);

        assertEquals("obr.getNdislike() Should be nDislike + 1.", nDislike + 1, obr.getNdislike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, obr.getLastUpdate());

    }

}
