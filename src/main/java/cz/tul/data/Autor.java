package cz.tul.data;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Petr on 09.04.2017.
 */
public class Autor {

    private int autor_id;

    private String jmeno;
    private String registrace;

    public Autor(){    }

    public Autor(String jmeno, String registrace) {
        this.jmeno = jmeno;
        this.registrace = registrace;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getRegistrace() {
        return registrace;
    }

    public void setRegistrace(String registrace) {
        this.registrace = registrace;
    }


    public int getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "autor_id=" + autor_id +
                ", jmeno='" + jmeno + '\'' +
                ", registrace='" + Instant.ofEpochSecond(new Long(registrace)) + '\'' +
                '}';
    }
}
