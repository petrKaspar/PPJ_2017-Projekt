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
    @Column(name = "commentId")
    private int commentId;

    private String commentText;
    private String title;
    private String created;
    private String lastUpdate;

    private int nlike;
    private int ndislike;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "pictureId")
    private Picture picture;

    public Comment(String commentText, String title, String created) {

        this.commentText = commentText;
        this.title = title;
        this.created = created;
    }

    public Comment(Picture picture, Author author, String commentText, String title, String created) {
        this.picture = picture;
        this.author = author;
        this.commentText = commentText;
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

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
