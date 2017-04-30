package cz.tul.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Petr on 18.04.2017.
 */
//@Entity
//@Table(name = "author_comment")
public class Author_comment {

    @Id
    @GeneratedValue
    private int author_commentId;

    private int authorId;
    private int commentId;

    public Author_comment(int authorId, int commentId) {
        this.authorId = authorId;
        this.commentId = commentId;
    }

    public int getAuthor_commentId() {
        return author_commentId;
    }

    public void setAuthor_commentId(int author_commentId) {
        this.author_commentId = author_commentId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }



}
