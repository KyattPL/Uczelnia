public class PracownikAdministracyjny extends PracownikUczelni {
    private int liczbaNadgodzin;

    PracownikAdministracyjny(String imie, String nazwisko, String PESEL, int wiek, Plec plec, int staz,
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