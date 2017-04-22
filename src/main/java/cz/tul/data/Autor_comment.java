package cz.tul.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Petr on 18.04.2017.
 */
//@Entity
//@Table(name = "autor_comment")
public class Autor_comment {

    @Id
    @GeneratedValue
    private int autor_comment_id;

    private int autor_id;
    private int comment_id;

    public Autor_comment(int autor_id, int comment_id) {
        this.autor_id = autor_id;
        this.comment_id = comment_id;
    }

    public int getAutor_comment_id() {
        return autor_comment_id;
    }

    public void setAutor_comment_id(int autor_comment_id) {
        this.autor_comment_id = autor_comment_id;
    }

    public int getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }



}
