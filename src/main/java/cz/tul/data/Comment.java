package cz.tul.data;

/**
 * Created by Petr on 03.04.2017.
 */
public class Comment {

    private int comment_id;
    private int picture_id;
    private int autor_id;
    private int autor_comment_id;

    private String text_comment;
    private String title;
    private String created;
    private String lastUpdate;

    private int nlike;
    private int ndislike;

    private Autor autor;
    private Picture picture;

    public Comment(int picture_id, int autor_id, String text_comment, String title, String created) {
        this.picture_id = picture_id;
        this.autor_id = autor_id;
        this.text_comment = text_comment;
        this.title = title;
        this.created = created;
    }

    public Comment(Picture picture, Autor autor, String text_comment, String title, String created) {
        this.picture = picture;
        this.autor = autor;
        this.text_comment = text_comment;
        this.title = title;
        this.created = created;
    }

    public Comment() {
    }

    public int getcomment_id() {
        return comment_id;
    }

    public void setcomment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getAutor_comment_id() {
        return autor_comment_id;
    }

    public void setAutor_comment_id(int autor_comment_id) {
        this.autor_comment_id = autor_comment_id;
    }

    public String getText_comment() {
        return text_comment;
    }

    public void setText_comment(String text_comment) {
        this.text_comment = text_comment;
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

    public int getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
