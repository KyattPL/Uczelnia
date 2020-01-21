package uczelnia;

import java.io.Serializable;

public abstract class PracownikUczelni extends Osoba implements Serializable {

    static final long serialVersionUID = 420;

    protected double staz;
    protected String stanowisko;
    protected int pensja;

    PracownikUczelni(String imie, String nazwisko, String PESEL, int wiek, Plec plec, double staz, String stanowisko,
            int pensja) {
        super(imie, nazwisko, PESEL, wiek, plec);
        this.staz = staz;
        this.stanowisko = stanowisko;
        this.pensja = pensja;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        } else if (!(obj instanceof PracownikUczelni)){
            return false;
        } else if (obj == this) {
            return true;
        }
        return getPESEL().equals(((PracownikUczelni) obj).getPESEL());
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(getPESEL());
    }

    @Override
    public String toString() {
        String tempPlec;
        if (plec == Plec.M) {
            tempPlec = "M";
        } else {
            tempPlec = "K";
        }
        return (imie + " " + nazwisko + " " + PESEL + " wiek: " + wiek + " plec: " + tempPlec + " staz: " + staz
                + " stanowisko: " + stanowisko + " pensja: " + pensja);
    }

    public double getStaz() {
        return this.staz;
    }

    public String getStanowisko() {
        return this.stanowisko;
    }

    public int getPensja() {
        return this.pensja;
    }

    public void setStaz(double staz) {
        this.staz = staz;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;
    }
}