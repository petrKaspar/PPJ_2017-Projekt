package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by Petr on 10.04.2017.
 */
public class CommentDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    private OffsetDateTime odt = OffsetDateTime.now();
    public int create(Comment comment) throws SQLException {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(comment);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean b = jdbc.update("insert into comment (picture_id, autor_id, text_comment, title, created) " +
                        "values (:picture_id, :autor_id, :text_comment, :title, :created)",
                params, keyHolder, new String[]{"ID"}) == 1;

        System.out.println("insert withou errors: " + b);
        System.out.println("keyHolder.getKey() = " + keyHolder.getKey());

        if (b == false) return 0;
        return keyHolder.getKey().intValue();

    }

    public boolean createBool(Comment comment) throws SQLException {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(comment);

        return jdbc.update("insert into comment (picture_id, autor_id, text_comment, title, created) " +
                        "values (:picture_id, :autor_id, :text_comment, :title, :created)", params) == 1;

    }

    public Comment getComment(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from comment, autor where comment.autor_id=autor.autor_id and comment_id=:id", params,
                new RowMapper<Comment>() {

                    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Autor autor = new Autor();
                        autor.setAutor_id(rs.getInt("autor_id"));
                        autor.setname(rs.getString("name"));
                        autor.setRegistration(rs.getString("registration"));

//                            Picture picture = new Picture();
//                            autor.setAutor_id(rs.getInt("autor_id"));
//                            autor.setname(rs.getString("name"));
//                            autor.setRegistration(rs.getString("registration"));

                        Comment comment = new Comment();
                        comment.setPicture_id(rs.getInt("picture_id"));
                        comment.setAutor_id(rs.getInt("autor_id"));
                        comment.setText_comment(rs.getString("text_comment"));
                        comment.settitle(rs.getString("title"));
                        comment.setCreated(rs.getString("created"));
                        comment.setLastUpdate(rs.getString("lastUpdate"));
                        comment.setNlike(rs.getInt("nlike"));
                        comment.setNdislike(rs.getInt("ndislike"));
                        comment.setAutor(autor);
//                            comment.setPicture(picture);

                        return comment;
                    }

                });
    }

    public List<Comment> getComments_innerjoin() {

        return jdbc
                .query("select * from comment join autor using (autor_id)",
                        (ResultSet rs, int rowNum) -> {
                            Autor autor = new Autor();
                            autor.setAutor_id(rs.getInt("autor_id"));
                            autor.setname(rs.getString("name"));
                            autor.setRegistration(rs.getString("registration"));

//                            Picture picture = new Picture();
//                            autor.setAutor_id(rs.getInt("autor_id"));
//                            autor.setname(rs.getString("name"));
//                            autor.setRegistration(rs.getString("registration"));

                            Comment comment = new Comment();
                            comment.setPicture_id(rs.getInt("picture_id"));
                            comment.setAutor_id(rs.getInt("autor_id"));
                            comment.setText_comment(rs.getString("text_comment"));
                            comment.settitle(rs.getString("title"));
                            comment.setCreated(rs.getString("created"));
                            comment.setLastUpdate(rs.getString("lastUpdate"));
                            comment.setNlike(rs.getInt("nlike"));
                            comment.setNdislike(rs.getInt("ndislike"));
                            comment.setAutor(autor);
//                            comment.setPicture(picture);

                            return comment;
                        }
                );
    }

    public boolean incrementNLike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", odt.toEpochSecond()+"");

        System.out.printf("update comment set nlike = nlike + 1 where comment_id=:id", params);
        return jdbc.update("update comment set nlike = nlike + 1, " +
                "lastUpdate = :lastUpdate where comment_id=:id", params) == 1;
    }

    public boolean incrementNDislike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", odt.toEpochSecond()+"");

        System.out.printf("update picture set ndislike = ndislike + 1lastUpdate = :lastUpdate where comment_id=:id", params);
        return jdbc.update("update comment set ndislike = ndislike + 1, " +
                "lastUpdate = :lastUpdate where comment_id=:id", params) == 1;
    }


}
