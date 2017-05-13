package cz.tul.service;

import cz.tul.data.Comment;
import cz.tul.data.Picture;
import cz.tul.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by Petr on 10.04.2017.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public int create(Comment comment){
        Comment newComment = commentRepository.save(comment);
        return newComment.getCommentId();
    }

    public boolean exists(int id){
        return commentRepository.exists(id);
    }

    public List<Comment> getAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

    public Comment getComment(int id) {
        return commentRepository.findOne(id);
    }

    public boolean incrementNLike(int id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        commentRepository.incrementNLike(id, LocalDateTime.now());
        return true;
    }

    public boolean incrementNDislike(int id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        commentRepository.incrementNDisLike(id, LocalDateTime.now());
        return true;
    }

    public void deleteComments(){
        commentRepository.deleteAll();
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

}
