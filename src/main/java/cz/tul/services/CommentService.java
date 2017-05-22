package cz.tul.service;

import cz.tul.data.Comment;
import cz.tul.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public int incrementNLike(int id) {
        return commentRepository.incrementNLike(id, LocalDateTime.now());
    }

    public int incrementNDislike(int id) {
        return commentRepository.incrementNDisLike(id, LocalDateTime.now());
    }

    public void deleteComments(){
        commentRepository.deleteAll();
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

}
