package cz.tul.data;

/**
 * Created by Petr on 03.04.2017.
 */
public class Picture {

    private int picture_id;
    private int autor_id;

    private String url;
    private String title;
    private String created;
    private String lastUpdate;
    private String tags;

    private int nlike;
    private int ndislike;

    private Autor autor;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Picture(Autor autor, String url, String title, String created){
        this.autor = autor;
        this.url = url;
        this.title = title;
        this.created = created;
    }

    public Picture() {
    }

    public Picture(int autor_id, String url, String title, String created) {
        this.autor_id = autor_id;
        this.url = url;
        this.title = title;
        this.created = created;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }

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

    public int getPicture_id() {
        return picture_id;
    }

    public int getAutor_id() {
        return autor_id;
    }

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
                "picture_id=" + picture_id +
                ", autor_id=" + autor_id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", created='" + created + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", tags='" + tags + '\'' +
                ", nlike=" + nlike +
                ", ndislike=" + ndislike +
                ", autor=" + autor +
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
