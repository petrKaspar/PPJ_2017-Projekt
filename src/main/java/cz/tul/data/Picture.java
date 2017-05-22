package cz.tul.data;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * Created by Petr on 03.04.2017.
 */
@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue
    @Column(name = "pictureId")
    private int pictureId;

    private String url;
    private String title;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime created;

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime lastUpdate;

    private String tags;

    private int nlike;
    private int ndislike;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "authorId")
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Picture(Author author, String url, String title, LocalDateTime created){
        this.author = author;
        this.url = url;
        this.title = title;
        this.created = created;
    }

    public Picture(Author author, String url, String title){
        this.author = author;
        this.url = url;
        this.title = title;
    }

    public Picture() {
    }

    public Picture(String url, String title, LocalDateTime created) {
        this.url = url;
        this.title = title;
        this.created = created;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
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

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getLastUpdate() {
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
