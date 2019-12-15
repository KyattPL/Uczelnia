import java.util.ArrayList;

public class Lista5{
    public static void main(String[] args){
        ArrayList<Osoba> zbiorOsob = new ArrayList<Osoba>();
        ArrayList<Kurs> zbiorKursow = new ArrayList<Kurs>();

        zbiorKursow.add(new Kurs("Algebra", "Krzysztof", "Michalik", 8));
        zbiorOsob.add(new Student("Kajetan", "Pynka", "00250907896", 19, Osoba.Plec.M, "254495", zbiorKursow, false, true, true));

        System.out.println(zbiorOsob.get(0).toString());
    }
}