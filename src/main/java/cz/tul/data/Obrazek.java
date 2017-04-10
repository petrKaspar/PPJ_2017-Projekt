package cz.tul.data;

/**
 * Created by Petr on 03.04.2017.
 */
public class Obrazek {

    private int obrazek_id;
    private int autor_id;

    private String url;
    private String nazev;
    private String vytvoreni;
    private String aktualizace;
    private String tags;

    private int nlike;
    private int ndislike;

    private Autor autor;

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Obrazek(Autor autor, String url, String nazev, String vytvoreni){
        this.autor = autor;
        this.url = url;
        this.nazev = nazev;
        this.vytvoreni = vytvoreni;
    }

    public Obrazek() {
    }

    public Obrazek(int autor_id, String url, String nazev, String vytvoreni) {
        this.autor_id = autor_id;
        this.url = url;
        this.nazev = nazev;
        this.vytvoreni = vytvoreni;
    }

    public void setObrazek_id(int obrazek_id) {
        this.obrazek_id = obrazek_id;
    }

    public void setAutor_id(int autor_id) {
        this.autor_id = autor_id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public void setVytvoreni(String vytvoreni) {
        this.vytvoreni = vytvoreni;
    }

    public void setAktualizace(String aktualizace) {
        this.aktualizace = aktualizace;
    }

    public void setNlike(int nlike) {
        this.nlike = nlike;
    }

    public void setNdislike(int ndislike) {
        this.ndislike = ndislike;
    }

    public int getObrazek_id() {
        return obrazek_id;
    }

    public int getAutor_id() {
        return autor_id;
    }

    public String getUrl() {
        return url;
    }

    public String getNazev() {
        return nazev;
    }

    public String getVytvoreni() {
        return vytvoreni;
    }

    public String getAktualizace() {
        return aktualizace;
    }

    public int getNlike() {
        return nlike;
    }

    public int getNdislike() {
        return ndislike;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Obrazek{" +
                "obrazek_id=" + obrazek_id +
                ", autor_id=" + autor_id +
                ", url='" + url + '\'' +
                ", nazev='" + nazev + '\'' +
                ", vytvoreni='" + vytvoreni + '\'' +
                ", aktualizace='" + aktualizace + '\'' +
                ", tags='" + tags + '\'' +
                ", nlike=" + nlike +
                ", ndislike=" + ndislike +
                ", autor=" + autor +
                '}';
    }

    //    private User user;
//
//    public Obrazek() {
//
//    }
//
//    public Obrazek(User user, String text) {
//        this.user = user;
//        this.text = text;
//    }
//
//    public Obrazek(int id, User user, String text) {
//        this.id = id;
//        this.user = user;
//        this.text = text;
//    }




}
