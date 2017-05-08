package cz.tul.data;

/**
 * Created by Petr on 03.04.2017.
 * Urceno pro me vlastni pokusy a zkouseni, jak co funguje. Ve finalni verzi semestralky nebude.
 */
public class Testovaci {

    private int id_testovaci;
    private String title;
    private int pocet;
    private String name;

    public Testovaci(String title, int pocet, String name) {
        this.title = title;
        this.pocet = pocet;
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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
