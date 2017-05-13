package cz.tul;

import cz.tul.data.*;
import cz.tul.service.AuthorService;
import cz.tul.service.CommentService;
import cz.tul.service.PictureService;
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
@SpringApplicationConfiguration(classes = {Main.class})
@ActiveProfiles({"test"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CommentDaoTests {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CommentService commentService;




    @Test
    public void testCreateComment() throws SQLException {

        commentService.deleteComments();

        Author author = new Author("Franta JPA testC", LocalDateTime.now());
        Picture picture = new Picture(author, "http://url.cz", "pokus 1", LocalDateTime.now());

        int authorKey = authorService.create(author);
        int pictureKey = pictureService.create(picture);
        picture.setPictureId(pictureKey);
        author.setAuthorId(authorKey);

        Comment comment = new Comment(picture, author, "text komentare", "Titulek komentare", LocalDateTime.now());
        comment.setAuthor(author);
        comment.setPicture(picture);

        assertTrue("Comment creation should return true", commentService.create(comment) > 0 );

        List<Comment> comments = commentService.getAllComments();
        assertEquals("Should be one comment in database.", 1, comments.size());
    }

    @Test
    public void testCommentExists() {
        commentService.deleteComments();

        Author author = new Author("Franta JPA testP", LocalDateTime.now());
        author.setAuthorId(authorService.create(author));

        Picture picture = new Picture(author, "http://url.cz", "test JPA", LocalDateTime.now());
        picture.setPictureId(pictureService.create(picture));

        Comment comment = new Comment(picture, author, "text komentare", "Titulek komentare", LocalDateTime.now());
        commentService.create(comment);

        assertTrue("Comment should exist with id 1", commentService.exists(comment.getCommentId()));
        assertFalse("Comment should not exist", commentService.exists(666));
    }

    @Test
    public void testDeleteComment() throws SQLException {

        commentService.deleteComments();
        Author author = new Author("Franta JPA testC", LocalDateTime.now());
        Picture picture = new Picture(author, "http://url.cz", "pokus 1", LocalDateTime.now());

        int authorKey = authorService.create(author);
        int pictureKey = pictureService.create(picture);

        Comment comment = new Comment(picture, author, "text komentare", "Titulek komentare", LocalDateTime.now());
        Comment comment1 = new Comment(picture, author, "text komentare", "Titulek komentare", LocalDateTime.now());
        Comment comment2 = new Comment(picture, author, "text komentare", "Titulek komentare", LocalDateTime.now());
        Comment comment3 = new Comment(picture, author, "text komentare", "Titulek komentare", LocalDateTime.now());

        commentService.create(comment);
        commentService.create(comment1);
        commentService.create(comment2);
        commentService.create(comment3);

        List<Comment> comments = commentService.getAllComments();
        assertEquals("Should be four comments.", 4, comments.size());

        commentService.deleteComment(comments.get(0));
        commentService.deleteComment(comments.get(1));

        comments = commentService.getAllComments();
        assertEquals("Should be two commets.", 2, comments.size());

    }

    @Test
    public void testAddLike() throws SQLException {


        Author author = new Author("Franta JPA testC", LocalDateTime.now());
        Picture picture = new Picture(author, "http://url.cz", "pokus 1", LocalDateTime.now());

        int authorKey = authorService.create(author);
        int pictureKey = pictureService.create(picture);

        Comment comment = new Comment(picture, author, "text komentare JPA", "Titulek komentare", LocalDateTime.now());
        comment.setAuthor(author);
        comment.setPicture(picture);

        int key = commentService.create(comment);

        List<Comment> comments = commentService.getAllComments();

        int nLike = comments.get(comments.size()-1).getNlike();
        int nDislike = comments.get(comments.size()-1).getNdislike();
        LocalDateTime lastUpdate = comments.get(comments.size()-1).getLastUpdate();

        commentService.incrementNLike(key);

        Comment c = commentService.getAllComments().get(comments.size()-1);

        assertEquals("obr.getNlike() Should be nLike + 1.", nLike + 1, c.getNlike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, c.getLastUpdate());

    }

    @Test
    public void testAddDislike() throws SQLException {


        Author author = new Author("Franta JPA testC", LocalDateTime.now());
        Picture picture = new Picture(author, "http://url.cz", "pokus 1", LocalDateTime.now());

        int authorKey = authorService.create(author);
        int pictureKey = pictureService.create(picture);

        Comment comment = new Comment(picture, author, "text komentare", "Titulek komentare", LocalDateTime.now());
        comment.setAuthor(author);
        comment.setPicture(picture);

        int key = commentService.create(comment);

        List<Comment> comments = commentService.getAllComments();

        int nLike = comments.get(comments.size()-1).getNlike();
        int nDislike = comments.get(comments.size()-1).getNdislike();
        LocalDateTime lastUpdate = comments.get(comments.size()-1).getLastUpdate();

        commentService.incrementNDislike(key);

        Comment c = commentService.getAllComments().get(comments.size()-1);

        assertEquals("obr.getNdislike() Should be nDislike + 1.", nDislike + 1, c.getNdislike());
        assertNotEquals("obr.getNlike() Should be nLike + 1.", lastUpdate, c.getLastUpdate());

    }

}
