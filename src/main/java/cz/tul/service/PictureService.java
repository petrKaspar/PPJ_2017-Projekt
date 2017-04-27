package cz.tul.service;

import cz.tul.data.Picture;
import org.hibernate.Criteria;
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
import java.util.List;

/**
 * Created by Petr on 09.04.2017.
 */
@Transactional
public class PictureService {


    @Autowired
    private SessionFactory sessionFactory;

    public Session session() {
        return sessionFactory.getCurrentSession();
    }

    private OffsetDateTime odt = OffsetDateTime.now();

    public int create(Picture picture){
        return (int) session().save(picture);
    }

    public boolean incrementNLike(int id) {
        Picture picture = (Picture) session().load(Picture.class, id);
        int nLike = picture.getNlike();
        System.out.println("nLike = "+nLike);

        picture.setNlike(nLike + 1);
        picture.setLastUpdate(odt.toEpochSecond()+"");

        int updatedKey = (int) session().save(picture);

        System.out.println(updatedKey);
        return true;
    }

    public boolean incrementNDislike(int id) {
        Picture picture = (Picture) session().load(Picture.class, id);
        int nDislike = picture.getNdislike();
        System.out.println("nDislike = "+nDislike);

        picture.setNdislike(nDislike + 1);
        picture.setLastUpdate(odt.toEpochSecond()+"");

        int updatedKey = (int) session().save(picture);

        System.out.println(updatedKey);
        return true;

    }

    @SuppressWarnings("unchecked")
    public List<Picture> getAllPictures() {
//        return session().createQuery("from autor").list();
        Criteria crit = session().createCriteria(Picture.class);
        return crit.list();
    }

    public Picture getPicture(int id) {
        Criteria crit = session().createCriteria(Picture.class);
        crit.add(Restrictions.idEq(id));

        return (Picture) crit.uniqueResult();
    }

    /*
    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private OffsetDateTime odt = OffsetDateTime.now();;
    public int create(Picture picture) throws SQLException {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                picture);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean b = jdbc.update("insert into picture (autor_id, url, title, created) " +
                        "values (:autor_id, :url, :title, :created)",
                params, keyHolder, new String[]{"ID"}) == 1;

        System.out.println("b = " + b);
        System.out.println("keyHolder.getKey() = " + keyHolder.getKey());
        
        if (b == false) return 0;
        return keyHolder.getKey().intValue();

//        System.out.printf("insert into picture (autor_id, url, title, created) values (:autor_id, :url, :title, :created)",params);
//        return jdbc
//                .update("insert into picture (autor_id, url, title, created) values (:autor_id, :url, :title, :created)",
//                        params) == 1;
    }

    public boolean incrementNLike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", odt.toEpochSecond()+"");

        System.out.printf("update picture set nlike = nlike + 1 where picture_id=:id", params);
        return jdbc.update("update picture set nlike = nlike + 1, " +
                "lastUpdate = :lastUpdate where picture_id=:id", params) == 1;
    }

    public boolean incrementNDislike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", odt.toEpochSecond()+"");

        System.out.printf("update picture set ndislike = ndislike + 1lastUpdate = :lastUpdate where picture_id=:id", params);
        return jdbc.update("update picture set ndislike = ndislike + 1, " +
                "lastUpdate = :lastUpdate where picture_id=:id", params) == 1;
    }

    public boolean setLastUpdate(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", odt.toEpochSecond()+"");

        System.out.printf("update picture set nlike = nlike + 1 where picture_id=:id", params);
        return jdbc.update("update picture set lastUpdate = :lastUpdate where picture_id=:id", params) == 1;
    }

    public List<Picture> getPictures_innerjoin() {

        return jdbc
                .query("select * from picture join autor using (autor_id)",
                        (ResultSet rs, int rowNum) -> {
                            Autor autor = new Autor();
                            autor.setAutor_id(rs.getInt("autor_id"));
                            autor.setname(rs.getString("name"));
                            autor.setRegistration(rs.getString("registration"));

                            Picture picture = new Picture();
                            picture.setPicture_id(rs.getInt("picture_id"));
                            picture.setAutor_id(rs.getInt("autor_id"));
                            picture.setUrl(rs.getString("url"));
                            picture.settitle(rs.getString("title"));
                            picture.setCreated(rs.getString("created"));
                            picture.setLastUpdate(rs.getString("lastUpdate"));
                            picture.setNlike(rs.getInt("nlike"));
                            picture.setNdislike(rs.getInt("ndislike"));
                            picture.setAutor(autor);

                            return picture;
                        }
                );
    }


    public Picture getPicture(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from picture, autor where picture.autor_id=autor.autor_id and picture_id=:id", params,
                new RowMapper<Picture>() {

                    public Picture mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Autor autor = new Autor();
                        autor.setAutor_id(rs.getInt("autor_id"));
                        autor.setname(rs.getString("name"));
                        autor.setRegistration(rs.getString("registration"));

                        Picture picture = new Picture();
                        picture.setPicture_id(rs.getInt("picture_id"));
                        picture.setAutor_id(rs.getInt("autor_id"));
                        picture.setUrl(rs.getString("url"));
                        picture.settitle(rs.getString("title"));
                        picture.setCreated(rs.getString("created"));
                        picture.setLastUpdate(rs.getString("lastUpdate"));
                        picture.setNlike(rs.getInt("nlike"));
                        picture.setNdislike(rs.getInt("ndislike"));
                        picture.setAutor(autor);

                        return picture;
                    }

                });
    }
*/
}
