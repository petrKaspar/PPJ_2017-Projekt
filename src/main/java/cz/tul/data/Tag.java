package cz.tul.data;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Petr on 23.05.2017.
 */
@Entity
@Table(name = "tag")
public class Tag implements java.io.Serializable {

    @Id
    @Column(name="tagId")
    private String tag;

    @ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "picturesTags",
            joinColumns = @JoinColumn(name = "tagId"),
            inverseJoinColumns = @JoinColumn(name = "pictureId"))
    @JsonBackReference
    private Set<Picture> pictureSet = new HashSet<Picture>();

    public Tag() {}

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag(String tag, Set<Picture> pictureSet) {
        this.tag = tag;
        this.pictureSet = pictureSet;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public Set<Picture> getPictureSet() {
        return pictureSet;
    }

}
