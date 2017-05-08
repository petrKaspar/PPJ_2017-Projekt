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
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentDaoTests {

    @Autowired
    private PictureDao pictureDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private CommentDao commentDao;



    @Test
    public void testCreateComment() throws SQLException {

        commentDao.deleteComments();

        Author author = new Author("Franta", new Date());
        Picture picture = new Picture(2, "http://url.cz", "pokus 1", new Date());

        int authorKey = authorDao.create(author);
        int pictureKey = pictureDao.create(picture);

        Comment comment = new Comment(pictureKey, authorKey, "text komentare", "Titulek komentare", new Date());
        comment.setAuthor(author);
        comment.setPicture(picture);

        assertTrue("Comment creation should return true", commentDao.createBool(comment));

        List<Comment> comments = commentDao.getAllComments();
        assertEquals("Should be one comment in database.", 1, comments.size());
    }

    @Test
    public void testDeleteComment() throws SQLException {

        commentDao.deleteComments();
        Author author = new Author("Franta", new Date());
        Picture picture = new Picture(2, "http://url.cz", "pokus 1", new Date());

        int authorKey = authorDao.create(author);
        int pictureKey = pictureDao.create(picture);

        Comment comment = new Comment(pictureKey, authorKey, "text komentare", "Titulek komentare", new Date());
        Comment comment1 = new Comment(pictureKey, authorKey, "text komentare", "Titulek komentare", new Date());
        Comment comment2 = new Comment(pictureKey, authorKey, "text komentare", "Titulek komentare", new Date());
        Comment comment3 = new Comment(pictureKey, authorKey, "text komentare", "Titulek komentare", new Date());

        commentDao.createBool(comment);
        commentDao.createBool(comment1);
        commentDao.createBool(comment2);
        commentDao.createBool(comment3);

        List<Comment> comments = commentDao.getAllComments();
        assertEquals("Should be four comments.", 4, comments.size());

        commentDao.deleteComment(comments.get(0).getCommentId());
        commentDao.deleteComment(comments.get(1).getCommentId());

        comments = commentDao.getAllComments();
        assertEquals("Should be two commets.", 2, comments.size());

    }

    @Test
    public void testAddLike() throws SQLException {


        Author author = new Author("Franta", new Date());
        Picture picture = new Picture(2, "http://url.cz", "pokus 1", new Date());

        int authorKey = authorDao.create(author);
        int pictureKey = pictureDao.create(picture);

        Comment comment = new Comment(pictureKey, authorKey, "text komentare", "Titulek komentare", new Date());
        comment.setAuthor(author);
        comment.setPicture(picture);

        int key = commentDao.create(comment);

        List<Comment> comments = commentDao.getAllComments();

        int nLike = comments.get(comments.size()-1).getNlike();
        int nDislike = comments.get(comments.size()-1).getNdislike();
        Date lastUpdate = comments.get(comments.size()-1).getLastUpdate();

        commentDao.incrementNLike(key);

        Comment c = commentDao.getAllComments().get(comments.size()-1);

        assertEquals("obr.getNlike() Should be nLike + 1.", nLike + 1, c.getNlike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, c.getLastUpdate());

    }

    @Test
    public void testAddDislike() throws SQLException {


        Author author = new Author("Franta", new Date());
        Picture picture = new Picture(2, "http://url.cz", "pokus 1", new Date());

        int authorKey = authorDao.create(author);
        int pictureKey = pictureDao.create(picture);

        Comment comment = new Comment(pictureKey, authorKey, "text komentare", "Titulek komentare", new Date());
        comment.setAuthor(author);
        comment.setPicture(picture);

        int key = commentDao.create(comment);

        List<Comment> comments = commentDao.getAllComments();

        int nLike = comments.get(comments.size()-1).getNlike();
        int nDislike = comments.get(comments.size()-1).getNdislike();
        Date lastUpdate = comments.get(comments.size()-1).getLastUpdate();

        commentDao.incrementNDislike(key);

        Comment c = commentDao.getAllComments().get(comments.size()-1);

        assertEquals("obr.getNdislike() Should be nDislike + 1.", nDislike + 1, c.getNdislike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, c.getLastUpdate());

    }

}
