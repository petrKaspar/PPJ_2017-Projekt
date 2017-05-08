package cz.tul.data;

import javax.persistence.*;

/**
 * Created by Petr on 03.04.2017.
 */
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private int commentId;

    @Column(name = "text_comment")
    private String textComment;
    private String title;
    private String created;
    private String lastUpdate;

    private int nlike;
    private int ndislike;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "picture_id")
    private Picture picture;

    public Comment(String textComment, String title, String created) {
        this.textComment = textComment;
        this.title = title;
        this.created = created;
    }

    public Comment(Picture picture, Author author, String textComment, String title, String created) {
        this.picture = picture;
        this.author = author;
        this.textComment = textComment;
        this.title = title;
        this.created = created;
    }

    public Comment() {
    }

    public int getcommentId() {
        return commentId;
    }

    public void setcommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getTextComment() {
        return textComment;
    }

    public void setTextComment(String textComment) {
        this.textComment = textComment;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getNlike() {
        return nlike;
    }

    public void setNlike(int nlike) {
        this.nlike = nlike;
    }

    public int getNdislike() {
        return ndislike;
    }

    public void setNdislike(int ndislike) {
        this.ndislike = ndislike;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
