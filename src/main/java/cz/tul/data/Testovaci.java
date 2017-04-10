package cz.tul.data;

/**
 * Created by Petr on 03.04.2017.
 * Urceno pro me vlastni pokusy a zkouseni, jak co funguje. Ve finalni verzi semestralky nebude.
 */
public class Testovaci {

    private int id_testovaci;
    private String nazev;
    private int pocet;
    private String jmeno;

    public Testovaci(String nazev, int pocet, String jmeno) {
        this.nazev = nazev;
        this.pocet = pocet;
        this.jmeno = jmeno;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    @Override
    public String toString() {
        return "Testovaci [id=" + id_testovaci + ", nazev=" + nazev + ", pocet=" + pocet+ ", jmeno=" + jmeno + "]";
    }

}
