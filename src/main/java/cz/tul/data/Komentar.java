package cz.tul.data;

/**
 * Created by Petr on 03.04.2017.
 */
public class Komentar {

    private int obrazek_id;
    private int komentar_id;
    private int autor_komentar_id;

    private String text_komentare;
    private String nazev;
    private String vytvoreni;
    private String aktualizace;

    private int nlike;
    private int ndislike;


    public int getKomentar_id() {
        return komentar_id;
    }

    public void setKomentar_id(int komentar_id) {
        this.komentar_id = komentar_id;
    }

    public int getAutor_komentar_id() {
        return autor_komentar_id;
    }

    public void setAutor_komentar_id(int autor_komentar_id) {
        this.autor_komentar_id = autor_komentar_id;
    }

    public String getText_komentare() {
        return text_komentare;
    }

    public void setText_komentare(String text_komentare) {
        this.text_komentare = text_komentare;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getVytvoreni() {
        return vytvoreni;
    }

    public void setVytvoreni(String vytvoreni) {
        this.vytvoreni = vytvoreni;
    }

    public String getAktualizace() {
        return aktualizace;
    }

    public void setAktualizace(String aktualizace) {
        this.aktualizace = aktualizace;
    }

    public int getNlike() {
        return nlike;
    }

    public void setNlike(int nlike) {
        this.nlike = nlike;
    }

    public int getNdislike() {
        return ndislike;
    }

    public void setNdislike(int ndislike) {
        this.ndislike = ndislike;
    }


}
