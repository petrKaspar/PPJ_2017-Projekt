package cz.tul.data;

import java.util.Date;

/**
 * Created by Petr on 03.04.2017.
 */
public class Comment {

    private int commentId;
    private int pictureId;
    private int authorId;
    private int authorCommentId;

    private String commentText;
    private String title;
    private Date created;
    private Date lastUpdate;

    private int nlike;
    private int ndislike;

    private Author author;
    private Picture picture;

    public Comment(int pictureId, int authorId, String commentText, String title, Date created) {
        this.pictureId = pictureId;
        this.authorId = authorId;
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

    public int getcommentId() {
        return commentId;
    }

    public void setcommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getAuthorCommentId() {
        return authorCommentId;
    }

    public void setAuthorCommentId(int authorCommentId) {
        this.authorCommentId = authorCommentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
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
