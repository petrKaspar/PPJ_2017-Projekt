package cz.tul.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Petr on 03.04.2017.
 * Urceno pro me vlastni pokusy a zkouseni, jak co funguje. Ve finalni verzi semestralky nebude.
 */
@Entity
@Table(name = "testovaci")
public class Testovaci {

    @Id
    @GeneratedValue
    private int id_testovaci;
    
    private String title;
    private int pocet;
    private String name;

    public Testovaci() {
    }

    public Testovaci(String title, int pocet, String name) {
        this.title = title;
        this.pocet = pocet;
        this.name = name;
    }

    public int getId_testovaci() {
        return id_testovaci;
    }

    public void setId_testovaci(int id_testovaci) {
        this.id_testovaci = id_testovaci;
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
