package cz.tul.data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Petr on 03.04.2017.
 */
public class Picture {

    private int pictureId;
    private int authorId;

    private String url;
    private String title;
    private Date created;
    private Date lastUpdate;
    private String tags;

    private int nlike;
    private int ndislike;

    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Picture(Author author, String url, String title, Date created){
        this.author = author;
        this.url = url;
        this.title = title;
        this.created = created;
    }

    public Picture() {
    }

    public Picture(int authorId, String url, String title, Date created) {
        this.authorId = authorId;
        this.url = url;
        this.title = title;
        this.created = created;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setLastUpdate(Date lastUpdate) {
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

    public int getAuthorId() {
        return authorId;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreated() {
        return created;
    }

    public Date getLastUpdate() {
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
                ", authorId=" + authorId +
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

}
