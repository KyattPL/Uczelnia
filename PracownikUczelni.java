public abstract class PracownikUczelni extends Osoba {
    protected int staz;
    protected String stanowisko;
    protected int pensja;

    PracownikUczelni(String imie, String nazwisko, String PESEL, int wiek, Plec plec, int staz, String stanowisko,
            int pensja) {
        super(imie, nazwisko, PESEL, wiek, plec);
        this.staz = staz;
        this.stanowisko = stanowisko;
        this.pensja = pensja;
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

    public int getStaz() {
        return this.staz;
    }

    public String getStanowisko() {
        return this.stanowisko;
    }

    public int getPensja() {
        return this.pensja;
    }

    public void setStaz(int staz) {
        this.staz = staz;
    }

    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;
    }
}