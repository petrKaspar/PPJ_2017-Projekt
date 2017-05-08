package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.Date;
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
        boolean b = jdbc.update("insert into comment (pictureId, authorId, commentText, title, created) " +
                        "values (:pictureId, :authorId, :commentText, :title, :created)",
                params, keyHolder) == 1;

        if (b == false) return 0;
        return keyHolder.getKey().intValue();

    }

    public boolean createBool(Comment comment) throws SQLException {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(comment);

        return jdbc.update("insert into comment (pictureId, authorId, commentText, title, created) " +
                        "values (:pictureId, :authorId, :commentText, :title, :created)", params) == 1;

    }

    public Comment getComment(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from comment, author where comment.authorId=author.authorId and commentId=:id", params,
                new RowMapper<Comment>() {

                    public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Author author = new Author();
                        author.setAuthorId(rs.getInt("authorId"));
                        author.setname(rs.getString("name"));
                        author.setRegistration(rs.getDate("registration"));

                        Comment comment = new Comment();
                        comment.setPictureId(rs.getInt("pictureId"));
                        comment.setAuthorId(rs.getInt("authorId"));
                        comment.setCommentText(rs.getString("commentText"));
                        comment.setTitle(rs.getString("title"));
                        comment.setCreated(rs.getDate("created"));
                        comment.setLastUpdate(rs.getDate("lastUpdate"));
                        comment.setNlike(rs.getInt("nlike"));
                        comment.setNdislike(rs.getInt("ndislike"));
                        comment.setAuthor(author);

                        return comment;
                    }

                });
    }

    public List<Comment> getComments_innerjoin() {

        return jdbc
                .query("select * from comment join author using (authorId)",
                        (ResultSet rs, int rowNum) -> {
                            Author author = new Author();
                            author.setAuthorId(rs.getInt("authorId"));
                            author.setname(rs.getString("name"));
                            author.setRegistration(rs.getDate("registration"));

                            Comment comment = new Comment();
                            comment.setPictureId(rs.getInt("pictureId"));
                            comment.setAuthorId(rs.getInt("authorId"));
                            comment.setCommentText(rs.getString("commentText"));
                            comment.setTitle(rs.getString("title"));
                            comment.setCreated(rs.getDate("created"));
                            comment.setLastUpdate(rs.getDate("lastUpdate"));
                            comment.setNlike(rs.getInt("nlike"));
                            comment.setNdislike(rs.getInt("ndislike"));
                            comment.setAuthor(author);

                            return comment;
                        }
                );
    }

    public List<Comment> getAllComments() {
        return jdbc.query("select * from comment", BeanPropertyRowMapper.newInstance(Comment.class));
    }

    public boolean incrementNLike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", new Date());

        return jdbc.update("update comment set nlike = nlike + 1, " +
                "lastUpdate = :lastUpdate where commentId=:id", params) == 1;
    }

    public boolean incrementNDislike(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        params.addValue("lastUpdate", new Date());

        return jdbc.update("update comment set ndislike = ndislike + 1, " +
                "lastUpdate = :lastUpdate where commentId=:id", params) == 1;
    }

    public void deleteComment(int id) {
        jdbc.getJdbcOperations().execute("DELETE FROM comment where commentId="+id);
    }
    public void deleteComments() {
        jdbc.getJdbcOperations().execute("DELETE FROM comment");
    }

}
