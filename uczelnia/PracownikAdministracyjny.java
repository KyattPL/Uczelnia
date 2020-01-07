package uczelnia;

import java.io.Serializable;

public class PracownikAdministracyjny extends PracownikUczelni implements Serializable {
    
    static final long serialVersionUID = 420;

    private int liczbaNadgodzin;

    PracownikAdministracyjny(String imie, String nazwisko, String PESEL, int wiek, Plec plec, double staz,
            String stanowisko, int pensja, int liczbaNadgodzin) {
        super(imie, nazwisko, PESEL, wiek, plec, staz, stanowisko, pensja);
        this.liczbaNadgodzin = liczbaNadgodzin;
    }

    @Override
    public String toString() {
        String sup = super.toString();
        return sup + " liczba nadgodzin: " + liczbaNadgodzin;
    }

    public int getLiczbaNadgodzin() {
        return this.liczbaNadgodzin;
    }

    public void setLiczbaNadgodzin(int liczbaNadgodzin) {
        this.liczbaNadgodzin = liczbaNadgodzin;
    }
}