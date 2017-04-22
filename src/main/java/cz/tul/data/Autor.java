package cz.tul.data;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Petr on 09.04.2017.
 */
@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue
    @Column(name = "autor_id")
    private int autor_id;

    private String name;
    private String registration;

    public Autor(){    }

    public Autor(String name, String registration) {
        this.name = name;
        this.registration = registration;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
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
                ", name='" + name + '\'' +
                ", registration='" + Instant.ofEpochSecond(new Long(registration)) + '\'' +
                '}';
    }
}
