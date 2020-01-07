package porownywarki;

import java.util.Comparator;
import uczelnia.Kurs;

public class PorownajNazwiskoKurs implements Comparator<Kurs> {
    @Override
    public int compare(Kurs k1, Kurs k2) {
        return k1.getNazwiskoProwadzacego().compareTo(k2.getNazwiskoProwadzacego());
    }
}