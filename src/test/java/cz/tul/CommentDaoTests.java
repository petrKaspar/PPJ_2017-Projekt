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
    public void Test1_createComment() throws SQLException {

        OffsetDateTime odt = OffsetDateTime.now();
        Comment comment = new Comment("Hibernate text komentare", "Titulek Hibernate",odt.toEpochSecond()+"");
        Author author = authorDao.getAuthor(2);
        Picture p3 = pictureDao.getPicture(5);

        comment.setAuthor(author);
        comment.setPicture(p3);

        List<Comment> comments = commentDao.getAllComments();
        assertEquals("Should be one Comment in database.", comments.size(), commentDao.create(comment) - 1);


    }

    @Test
    public void Test_addLikeDislike() {

        List<Comment> comments = commentDao.getAllComments();

        int lastComment = comments.get(comments.size()-1).getcommentId();
        System.out.println(lastComment+"aaaaaaaaaaaaaaa");
        int nLike = comments.get(comments.size()-1).getNlike();
        int nDislike = comments.get(comments.size()-1).getNdislike();
        String lastUpdate = comments.get(comments.size()-1).getLastUpdate();

        commentDao.incrementNDislike(lastComment);

        Comment c = commentDao.getComment(lastComment);

        assertEquals("Comment.getNlike() Should be nLike + 1.", nLike + 1, c.getNlike());
        assertEquals("Comment.getNdislike() Should be nDislike + 1.", nDislike + 1, c.getNdislike());
        assertNotEquals("Comment.getNlike() Should be nLike + 1.", lastUpdate, c.getLastUpdate());

    }

}
