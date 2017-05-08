package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Petr on 09.04.2017.
 */
@Transactional
public class AuthorDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    public int create(Author author) {
        int newKey = (int) session().save(author);
        return newKey;
    }

    @SuppressWarnings("unchecked")
    public List<Author> getAllAuthors() {
        Criteria crit = session().createCriteria(Author.class);
        return crit.list();
    }

    public Author getAuthor(int id) {
        Criteria crit = session().createCriteria(Author.class);

        crit.add(Restrictions.idEq(id));

        return (Author) crit.uniqueResult();
    }

}
