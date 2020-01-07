package porownywarki;

import java.util.Comparator;
import uczelnia.Osoba;

public class PorownajNazwisko implements Comparator<Osoba> {

    @Override
    public int compare(Osoba o1, Osoba o2) {
        return o1.getNazwisko().compareTo(o2.getNazwisko());
    }
}