package cz.tul.service;

import cz.tul.data.Comment;
import cz.tul.data.Picture;
import cz.tul.repositories.CommentRepository;
import cz.tul.repositories.PictureRepository;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by Petr on 10.04.2017.
 */
@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public int create(Comment comment){
        Comment newComment = commentRepository.save(comment);
        return newComment.getcomment_id();
    }

    public List<Comment> getAllComments() {
        return StreamSupport.stream(commentRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    //
    public Comment getComment(int id) {
        return commentRepository.findOne(id);
    }

    public boolean incrementNLike(int id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        commentRepository.incrementNLike(id, localDateTime.toString());
        return true;
    }

    public boolean incrementNDislike(int id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        commentRepository.incrementNDisLike(id, localDateTime.toString());
        return true;
    }

//
//    @SuppressWarnings("unchecked")
//    public List<Comment> getAllComments() {
////        return session().createQuery("from autor").list();
//        Criteria crit = session().createCriteria(Comment.class);
//        return crit.list();
//    }
//
//    public Comment getComment(int id) {
//        Criteria crit = session().createCriteria(Comment.class);
//        crit.add(Restrictions.idEq(id));
//
//        return (Comment) crit.uniqueResult();
//    }
//////*******************************************************************************
    /*
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
                        comment.setcomment_id(rs.getInt("comment_id"));
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
*/

}
