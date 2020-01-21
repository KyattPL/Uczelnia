package uczelnia;

import java.io.Serializable;
import java.util.HashSet;

public class Student extends Osoba implements Serializable{

    static final long serialVersionUID = 420;

    private String nrIndeksu;
    private HashSet<Kurs> kursy;
    private boolean czyERASMUS;
    private boolean czy1Stopien;
    private boolean czyStacjonarne;

    Student(String imie, String nazwisko, String PESEL, int wiek, Plec plec, String nrIndeksu, HashSet<Kurs> kursy,
            boolean czyERASMUS, boolean czy1Stopien, boolean czyStacjonarne) {
        super(imie, nazwisko, PESEL, wiek, plec);
        this.nrIndeksu = nrIndeksu;
        this.kursy = kursy;
        this.czyERASMUS = czyERASMUS;
        this.czy1Stopien = czy1Stopien;
        this.czyStacjonarne = czyStacjonarne;
    }
    
    Student(){
        super("dumb", "test", "43242342", 19, Plec.M);
        this.nrIndeksu = "123123";
        this.kursy = null;
        this.czyERASMUS = false;
        this.czy1Stopien = false;
        this.czyStacjonarne = false;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(getNrIndeksu());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Student)){
            return false;
        } else if (obj == this) {
            return true;
        }
        return this.getNrIndeksu().equals(((Student) obj).getNrIndeksu());
    }

    @Override
    public String toString() {
        String stopien;
        String rodzaj;

        if (czy1Stopien) {
            stopien = "Pierwszy";
        } else {
            stopien = "Drugi";
        }

        if (czyStacjonarne) {
            rodzaj = "Stacjonarne";
        } else {
            rodzaj = "Niestacjonarne";
        }

        return (imie + " " + nazwisko + " " + PESEL + "   wiek: " + wiek + " plec: " + plec.toString() + " nrIndeksu: "
                + nrIndeksu + " ERASMUS: " + czyERASMUS + " Stopien: " + stopien + " Rodzaj: " + rodzaj);
    }

    public String getNrIndeksu() {
        return this.nrIndeksu;
    }

    public HashSet<Kurs> getKursy() {
        return this.kursy;
    }

    public boolean getCzyERASMUS() {
        return this.czyERASMUS;
    }

    public boolean getCzy1Stopien() {
        return this.czy1Stopien;
    }

    public boolean getCzyStacjonarne() {
        return this.czyStacjonarne;
    }

    public void setNrIndeksu(String nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

    public void setKursy(HashSet<Kurs> kursy) {
        this.kursy = kursy;
    }

    public void setCzyERASMUS(boolean czyERASMUS) {
        this.czyERASMUS = czyERASMUS;
    }

    public void setCzy1Stopien(boolean czy1Stopien) {
        this.czy1Stopien = czy1Stopien;
    }

    public void setCzyStacjonarne(boolean czyStacjonarne) {
        this.czyStacjonarne = czyStacjonarne;
    }
}