package cz.tul;

import cz.tul.data.*;
import cz.tul.service.AutorService;
import cz.tul.service.CommentService;
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
public class CommentDaoTests {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private CommentService commentService;

    @Test
    public void Test1_createComment() throws SQLException {

        OffsetDateTime odt = OffsetDateTime.now();
        //int picture_id, int autor_id, String text_comment, String title, String created
        Comment comment = new Comment(1, 2, "Hibernate text komentare", "Titulek Hibernate",odt.toEpochSecond()+"");
        Autor autor = autorService.getAutor(2);
        Picture p3 = pictureService.getPicture(5);

        comment.setAutor(autor);
        comment.setPicture(p3);
//        assertTrue("Offer creation should return true", commentService.createBool(comment));

        List<Comment> comments = commentService.getAllComments();
        assertEquals("Should be one Comment in database.", comments.size(), commentService.create(comment) - 1);


    }

    @Test
    public void Test_addLikeDislike() {

        List<Comment> comments = commentService.getAllComments();

        int lastComment = comments.get(comments.size()-1).getcomment_id();
        System.out.println(lastComment+"aaaaaaaaaaaaaaa");
        int nLike = comments.get(comments.size()-1).getNlike();
        int nDislike = comments.get(comments.size()-1).getNdislike();
        String lastUpdate = comments.get(comments.size()-1).getLastUpdate();

        System.out.println(commentService.incrementNLike(lastComment));;
        commentService.incrementNDislike(lastComment);

        Comment c = commentService.getComment(lastComment);

        assertEquals("Comment.getNlike() Should be nLike + 1.", nLike + 1, c.getNlike());
        assertEquals("Comment.getNdislike() Should be nDislike + 1.", nDislike + 1, c.getNdislike());
        assertNotEquals("Comment.getNlike() Should be nLike + 1.", lastUpdate, c.getLastUpdate());

    }

}
