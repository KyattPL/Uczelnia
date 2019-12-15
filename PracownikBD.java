public class PracownikBD extends PracownikUczelni {
    private int punktacja;

    PracownikBD(String imie, String nazwisko, String PESEL, int wiek, Plec plec, int staz, String stanowisko, int pensja, int punktacja){
        super(imie, nazwisko, PESEL, wiek, plec, staz, stanowisko, pensja);
        this.punktacja = punktacja;
    }

    public int getPunktacja(){
        return this.punktacja;
    }

    public void setPunktacja(int punktacja){
        this.punktacja = punktacja;
    }
}