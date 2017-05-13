package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Petr on 09.04.2017.
 */
public class PictureDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public int create(Picture picture) throws SQLException {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                picture);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean b = jdbc.update("insert into picture (authorId, url, title, created) " +
                        "values (:authorId, :url, :title, :created)",
                params, keyHolder) == 1;


        if (b == false) return 0;
        return keyHolder.getKey().intValue();
    }

    public boolean incrementNLike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", new Date());

        return jdbc.update("update picture set nlike = nlike + 1, " +
                "lastUpdate = :lastUpdate where pictureId=:id", params) == 1;
    }

    public boolean incrementNDislike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", new Date());

        return jdbc.update("update picture set ndislike = ndislike + 1, " +
                "lastUpdate = :lastUpdate where pictureId=:id", params) == 1;
    }

    public boolean setLastUpdate(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", new Date());

        return jdbc.update("update picture set lastUpdate = :lastUpdate where pictureId=:id", params) == 1;
    }

    public List<Picture> getPictures_innerjoin() {

        return jdbc
                .query("select * from picture join author using (authorId)",
                        (ResultSet rs, int rowNum) -> {
                            Author author = new Author();
                            author.setAuthorId(rs.getInt("authorId"));
                            author.setName(rs.getString("name"));
                            author.setRegistration(rs.getDate("registration"));

                            Picture picture = new Picture();
                            picture.setPictureId(rs.getInt("pictureId"));
                            picture.setAuthorId(rs.getInt("authorId"));
                            picture.setUrl(rs.getString("url"));
                            picture.setTitle(rs.getString("title"));
                            picture.setCreated(rs.getDate("created"));
                            picture.setLastUpdate(rs.getDate("lastUpdate"));
                            picture.setNlike(rs.getInt("nlike"));
                            picture.setNdislike(rs.getInt("ndislike"));
                            picture.setAuthor(author);

                            return picture;
                        }
                );
    }


    public Picture getPicture(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from picture, author where picture.authorId=author.authorId and pictureId=:id", params,
                new RowMapper<Picture>() {

                    public Picture mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Author author = new Author();
                        author.setAuthorId(rs.getInt("authorId"));
                        author.setName(rs.getString("name"));
                        author.setRegistration(rs.getDate("registration"));

                        Picture picture = new Picture();
                        picture.setPictureId(rs.getInt("pictureId"));
                        picture.setAuthorId(rs.getInt("authorId"));
                        picture.setUrl(rs.getString("url"));
                        picture.setTitle(rs.getString("title"));
                        picture.setCreated(rs.getDate("created"));
                        picture.setLastUpdate(rs.getDate("lastUpdate"));
                        picture.setNlike(rs.getInt("nlike"));
                        picture.setNdislike(rs.getInt("ndislike"));
                        picture.setAuthor(author);

                        return picture;
                    }

                });
    }

    public List<Picture> getAllPictures() {
        return jdbc.query("select * from picture", BeanPropertyRowMapper.newInstance(Picture.class));
    }

    public void deletePicture(int id) {
        jdbc.getJdbcOperations().execute("DELETE FROM picture where pictureId="+id);
    }
    public void deletePictures() {
        jdbc.getJdbcOperations().execute("DELETE FROM picture");
    }

}
