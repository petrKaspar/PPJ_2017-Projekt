package cz.tul.data;

import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.Date;

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
    private Date created;
    private Date lastUpdate;

    private int nlike;
    private int ndislike;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "pictureId")
    @OnDelete(action = org.hibernate.annotations.OnDeleteAction.CASCADE)
    private Picture picture;

    public Comment(String commentText, String title, Date created) {

        this.commentText = commentText;
        this.title = title;
        this.created = created;
    }

    public Comment(Picture picture, Author author, String commentText, String title, Date created) {
        this.picture = picture;
        this.author = author;
        this.commentText = commentText;
        this.title = title;
        this.created = created;
    }

    public Comment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
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
