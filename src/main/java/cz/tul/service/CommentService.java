package cz.tul.service;

import cz.tul.data.Comment;
import cz.tul.data.Picture;
import cz.tul.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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
        return newComment.getcommentId();
    }

    public List<Comment> getAllComments() {
        return (List<Comment>) commentRepository.findAll();
    }

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


}
