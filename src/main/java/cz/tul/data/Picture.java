package cz.tul.data;

import javax.persistence.*;

/**
 * Created by Petr on 03.04.2017.
 */
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue
    @Column(name = "picture_id")
    private int pictureId;

//    private int authorId;

    private String url;
    private String title;
    private String created;

    @Column(name="lastUpdate")
    private String lastUpdate;

    private String tags;

    private int nlike;
    private int ndislike;


    //@Column(name = "author")
    @OneToOne
    @JoinColumn(name = "author_id")
    private Author author;

//    @OneToOne
//    @JoinColumn(name = "commentId")
//    private Comment comment;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Picture(Author author, String url, String title, String created){
        this.author = author;
        this.url = url;
        this.title = title;
        this.created = created;
    }

    public Picture() {
    }

    public Picture(int authorId, String url, String title, String created) {
//        this.authorId = authorId;
        this.url = url;
        this.title = title;
        this.created = created;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

//    public void setAuthorId(int authorId) {
//        this.authorId = authorId;
//    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setNlike(int nlike) {
        this.nlike = nlike;
    }

    public void setNdislike(int ndislike) {
        this.ndislike = ndislike;
    }

    public int getPictureId() {
        return pictureId;
    }

//    public int getAuthorId() {
//        return authorId;
//    }

    public String getUrl() {
        return url;
    }

    public String gettitle() {
        return title;
    }

    public String getCreated() {
        return created;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public int getNlike() {
        return nlike;
    }

    public int getNdislike() {
        return ndislike;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureId=" + pictureId +
//                ", authorId=" + authorId +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", created='" + created + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", tags='" + tags + '\'' +
                ", nlike=" + nlike +
                ", ndislike=" + ndislike +
                ", author=" + author +
                '}';
    }

    //    private User user;
//
//    public Picture() {
//
//    }
//
//    public Picture(User user, String text) {
//        this.user = user;
//        this.text = text;
//    }
//
//    public Picture(int id, User user, String text) {
//        this.id = id;
//        this.user = user;
//        this.text = text;
//    }




}
