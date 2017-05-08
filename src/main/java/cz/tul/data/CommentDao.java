package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by Petr on 10.04.2017.
 */
@Transactional
public class CommentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }
    private OffsetDateTime odt;
    private LocalDateTime ldt = LocalDateTime.now();

    public int create(Comment comment){
        return (int) session().save(comment);
    }

    public boolean incrementNLike(int id) {
        odt = OffsetDateTime.now();
        Comment comment = (Comment) session().load(Comment.class, id);
        int nLike = comment.getNlike();

        comment.setNlike(nLike + 1);
        comment.setLastUpdate(odt.toEpochSecond()+"");

        int updatedKey = (int) session().save(comment);

        return true;
    }

    public boolean incrementNDislike(int id) {
        odt = OffsetDateTime.now();
        Comment comment = (Comment) session().load(Comment.class, id);
        int nDislike = comment.getNdislike();

        comment.setNdislike(nDislike + 1);
        comment.setLastUpdate(odt.toEpochSecond()+"");

        int updatedKey = (int) session().save(comment);

        return true;

    }

    @SuppressWarnings("unchecked")
    public List<Comment> getAllComments() {
        Criteria crit = session().createCriteria(Comment.class);
        return crit.list();
    }

    public Comment getComment(int id) {
        Criteria crit = session().createCriteria(Comment.class);
        crit.add(Restrictions.idEq(id));

        return (Comment) crit.uniqueResult();
    }

}
