package cz.tul.data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Petr on 03.04.2017.
 * Urceno pro me vlastni pokusy a zkouseni, jak co funguje. Ve finalni verzi semestralky nebude.
 */
@Entity
@Table(name = "testovaci")
public class Testovaci {

    @Id
    @GeneratedValue
    @Column(name = "id_testovaci")
    private int id_testovaci;
    
    private String title;
    private int pocet;
    private String name;

    @Column(name = "local_date_time")
    private LocalDateTime local_date_time;

    public LocalDateTime getLocal_date_time() {
        return local_date_time;
    }

    public void setLocal_date_time(LocalDateTime local_date_time) {
        this.local_date_time = local_date_time;
    }

    public Testovaci(){

    }

    public Testovaci(String title, int pocet, String name) {
        this.title = title;
        this.pocet = pocet;
        this.name = name;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Testovaci [id=" + id_testovaci + ", title=" + title + ", pocet=" + pocet+ ", name=" + name + "]";
    }

}
