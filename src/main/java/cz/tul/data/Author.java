package cz.tul.data;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Petr on 09.04.2017.
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue
    @Column(name = "authorId")
    private int authorId;

    private String name;
    private String registration;

    public Author(){    }

    public Author(String name, String registration) {
        this.name = name;
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }


    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", registration='" + Instant.ofEpochSecond(new Long(registration)) + '\'' +
                '}';
    }
}
