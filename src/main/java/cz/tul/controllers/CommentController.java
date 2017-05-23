package cz.tul.controllers;

import cz.tul.client.ServerApi;
import cz.tul.data.Comment;
import cz.tul.services.AuthorService;
import cz.tul.services.CommentService;
import cz.tul.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Petr on 22.05.2017.
 */
@RestController
public class CommentController {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = ServerApi.COMMENTS_PATH, method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> showComments() {
        List<Comment> comments = commentService.getAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = ServerApi.COMMENTS_PATH, method = RequestMethod.POST)
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        if (authorService.exists(comment.getAuthor().getAuthorId()) &&
                pictureService.exists(comment.getPicture().getPictureId())) {

            commentService.create(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = ServerApi.COMMENT_PATH, method = RequestMethod.GET)
    public ResponseEntity<Comment> getComment(@PathVariable("id") int id) {
        if (commentService.exists(id)) {
            Comment comment = commentService.getComment(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.COMMENT_LIKE_PATH, method = RequestMethod.GET)
    public ResponseEntity<Comment> addLike(@PathVariable("id") int id) {
        if (commentService.exists(id)) {
            commentService.incrementNLike(id);
            Comment comment = commentService.getComment(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.COMMENT_DISLIKE_PATH, method = RequestMethod.GET)
    public ResponseEntity<Comment> addDislike(@PathVariable("id") int id) {
        if (commentService.exists(id)) {
            commentService.incrementNDislike(id);
            Comment comment = commentService.getComment(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = ServerApi.COMMENT_PATH, method = RequestMethod.DELETE)
    public ResponseEntity deleteComment (@PathVariable("id") int id) {
        if (commentService.exists(id)) {
            commentService.deleteComment(commentService.getComment(id));
            return new ResponseEntity("The comment with id:"+id+" was successfully deleted!", HttpStatus.OK);
        } else {
            return new ResponseEntity("The comment with id:"+id+" does not exist!", HttpStatus.NOT_FOUND);
        }
    }

}
