package porownywarki;

import uczelnia.Kurs;
import java.util.Comparator;

public class PorownajECTS implements Comparator<Kurs> {
    @Override
    public int compare(Kurs k1, Kurs k2) {
        if (k1.getNazwiskoProwadzacego().equals(k2.getNazwiskoProwadzacego())
                && k1.getPunktyECTS() > k2.getPunktyECTS()) {
            return 1;
        } else if (k1.getNazwiskoProwadzacego().equals(k2.getNazwiskoProwadzacego())
                && k1.getPunktyECTS() < k2.getPunktyECTS()) {
            return -1;
        }
        return 0;
    }
}