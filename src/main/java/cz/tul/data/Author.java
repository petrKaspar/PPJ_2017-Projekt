package cz.tul.data;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;

/**
 * Created by Petr on 09.04.2017.
 */
public class Author {

    private int authorId;

    private String name;
    private Date registration;

    public Author(){    }

    public Author(String name, Date registration) {
        this.name = name;
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
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
                ", registration='" + (registration) + '\'' +
                '}';
    }
}
