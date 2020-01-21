package uczelnia;

import java.io.Serializable;

public class PracownikBD extends PracownikUczelni implements Serializable {

    static final long serialVersionUID = 420;

    private int punktacja;

    PracownikBD(String imie, String nazwisko, String PESEL, int wiek, Plec plec, double staz, String stanowisko,
            int pensja, int punktacja) {
        super(imie, nazwisko, PESEL, wiek, plec, staz, stanowisko, pensja);
        this.punktacja = punktacja;
    }

    @Override
    public String toString() {
        String sup = super.toString();
        return sup + " punktacja: " + punktacja;
    }

    public int getPunktacja() {
        return this.punktacja;
    }

    public void setPunktacja(int punktacja) {
        this.punktacja = punktacja;
    }
}