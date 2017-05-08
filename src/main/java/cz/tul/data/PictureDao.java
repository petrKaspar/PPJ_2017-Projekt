package cz.tul.data;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Petr on 09.04.2017.
 */
@Transactional
public class PictureDao {


    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    private OffsetDateTime odt;

    public int create(Picture picture){
        return (int) session().save(picture);
    }

    public boolean incrementNLike(int id) {
        odt = OffsetDateTime.now();
        Picture picture = (Picture) session().load(Picture.class, id);
        int nLike = picture.getNlike();

        picture.setNlike(nLike + 1);
        picture.setLastUpdate(new Date());

        int updatedKey = (int) session().save(picture);

        return true;
    }

    public boolean incrementNDislike(int id) {
        odt = OffsetDateTime.now();
        Picture picture = (Picture) session().load(Picture.class, id);
        int nDislike = picture.getNdislike();

        picture.setNdislike(nDislike + 1);
        picture.setLastUpdate(new Date());

        int updatedKey = (int) session().save(picture);

        return true;

    }

    @SuppressWarnings("unchecked")
    public List<Picture> getAllPictures() {
        Criteria crit = session().createCriteria(Picture.class);
        return crit.list();
    }

    public Picture getPicture(int id) {
        Criteria crit = session().createCriteria(Picture.class);
        crit.add(Restrictions.idEq(id));

        return (Picture) crit.uniqueResult();
    }

    public boolean deletePicture(int id) {
        Query query = session().createQuery("delete from Picture where pictureId=:id");
        query.setInteger("id", id);
        return query.executeUpdate() == 1;
    }

    public void deletePictures() {
        session().createQuery("delete from Picture").executeUpdate();
    }


}
