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
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    public void Test1_createComment() throws SQLException {

        OffsetDateTime odt = OffsetDateTime.now();
        Comment comment = new Comment("Hibernate text komentare", "Titulek Hibernate",odt.toEpochSecond()+"");
        Author author = authorService.getAuthor(2);
        Picture p3 = pictureService.getPicture(5);

        comment.setAuthor(author);
        comment.setPicture(p3);

        List<Comment> comments = commentService.getAllComments();
        assertEquals("Should be one Comment in database.", comments.size(), commentService.create(comment) - 1);

    }

    @Test
    public void Test_addLike() {

        List<Comment> comments = commentService.getAllComments();

        int lastComment = comments.get(comments.size()-1).getcommentId();
        int nLike = comments.get(comments.size()-1).getNlike();
        String lastUpdate = comments.get(comments.size()-1).getLastUpdate();

        commentService.incrementNLike(lastComment);

        Comment c = commentService.getComment(lastComment);

        assertEquals("Comment.getNlike() Should be nLike + 1.", nLike + 1, c.getNlike());
        assertNotEquals("Comment.getNlike() Should be nLike + 1.", lastUpdate, c.getLastUpdate());

    }

    @Test
    public void Test_addDislike() {

        List<Comment> comments = commentService.getAllComments();

        int lastComment = comments.get(comments.size()-1).getcommentId();
        int nDislike = comments.get(comments.size()-1).getNdislike();
        String lastUpdate = comments.get(comments.size()-1).getLastUpdate();

        commentService.incrementNDislike(lastComment);

        Comment c = commentService.getComment(lastComment);

        assertEquals("Comment.getNdislike() Should be nDislike + 1.", nDislike + 1, c.getNdislike());
        assertNotEquals("Comment.getNlike() Should be nLike + 1.", lastUpdate, c.getLastUpdate());

    }

}
