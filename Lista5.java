import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import Osoba.Plec;

public class Lista5{

    static ArrayList<Osoba> zbiorOsob = new ArrayList<Osoba>();
    static HashSet<Kurs> zbiorDostepnychKursow = new HashSet<Kurs>();
    public static void main(String[] args){

        boolean czyWyjscie = false;
        Scanner scan = new Scanner(System.in);
        int wybor;

        while (!czyWyjscie){
            wyswietlMenu();
            wybor = getInt(scan);
            wywolajOpcje(wybor, scan);
            if (wybor == 0){
                czyWyjscie = true;
            }
        }
        scan.close();
    }

    public static void wyswietlMenu(){
        System.out.println("1. Dodaj (Pracownika, Studenta, Kurs)");
        System.out.println("2. Wyszukaj po kryterium (Pracownika, Studenta, Kurs)");
        System.out.println("3. Wyswietl wszystkich (Pracownikow, Studentow, Kursy)");
        System.out.println("0. Wyjdz");
    }

    public static void wywolajOpcje(int wybor, Scanner scan){
        switch (wybor) {
            case 1: dodajDoKolekcji(scan); break;
            case 2: wyszukaj(scan); break;
            default: break;
        }
    }

    public static void dodajDoKolekcji(Scanner scan){
        System.out.println("Dodaj: 1. Pracownika BD, 2. Pracownika Administracyjnego, 3. Studenta, 4. Kurs");
        int wybor = getInt(scan);

        switch (wybor){
            case 1: dodajPracownika(scan, 1); break;
            case 2: dodajPracownika(scan, 2); break;
            case 3: dodajStudenta(scan); break;
            case 4: dodajKurs(scan); break;
            default: break;
        }
    }

    public static void dodajPracownika(Scanner scan, int wybor){
        String imie = getString(scan);
        String nazwisko = getString(scan);
        String PESEL = getString(scan);
        int wiek = getInt(scan);
        String plecStr = getString(scan);
        Plec plec;
        while (!plecStr.equals("M") && !plecStr.equals("K")){
            plecStr = getString(scan);
        }
        if (plecStr.equals("M")){
            plec = Plec.M;
        } else {
            plec = Plec.K;
        }
        int staz = getInt(scan);
        String stanowisko = getString(scan);
        int pensja = getInt(scan);
        int punktacjaLubNadgodziny = getInt(scan);
        if (wybor == 1){
            PracownikBD prac = new PracownikBD(imie, nazwisko, PESEL, wiek, plec, staz, stanowisko, pensja, punktacjaLubNadgodziny);
        } else {
            PracownikAdministracyjny prac = new PracownikAdministracyjny(imie, nazwisko, PESEL, wiek, plec, staz, stanowisko, pensja, punktacjaLubNadgodziny);
        }
        zbiorOsob.add(prac);
    }

    public static void dodajStudenta(Scanner scan){
        String imie = getString(scan);
        String nazwisko = getString(scan);
        String PESEL = getString(scan);
        int wiek = getInt(scan);
        String plecStr = getString(scan);
        Plec plec;
        while (!plecStr.equals("M") && !plecStr.equals("K")){
            plecStr = getString(scan);
        }
        if (plecStr.equals("M")){
            plec = Plec.M;
        } else {
            plec = Plec.K;
        }
        int nrIndeksu = getInt(scan);
        HashSet<Kurs> kursyStudenta = new HashSet<Kurs>();
        przydzielKursy(kursyStudenta, scan);
        boolean czyERASMUS = getBoolean(scan);
        boolean czy1Stopien = getBoolean(scan);
        boolean czyStacjonarne = getBoolean(scan);
        Student stud = new Student(imie, nazwisko, PESEL, wiek, plec, nrIndeksu, kursy, czyERASMUS, czy1Stopien, czyStacjonarne);
        zbiorOsob.add(stud);
    }

    public static void przydzielKursy(HashSet<Kurs> kursyStudenta, Scanner scan){
        int i = 1;
        ArrayList<Kurs> zbior = zbiorDostepnychKursow.toArray();
        for (Kurs k : zbior){
            System.out.println(i + ". " + k.getNazwa());
            i++;
        }
        System.out.println("Dodaj wszystkie kursy studenta (Podaj liczbe). '0' zeby wyjsc.");
        int input = getInt(scan);
        while (input != 0){
            kursyStudenta.add(zbior.get(input-1));
            input = getInt(scan);
        }
    }

    public static void dodajKurs(Scanner scan){
        String nazwa = getString(scan);
        String imieProwadzacego = getString(scan);
        String nazwiskoProwadzacego = getString(scan);
        int punktyECTS = getInt(scan);
        Kurs k = new Kurs(nazwa, imieProwadzacego, nazwiskoProwadzacego, punktyECTS);
        zbiorDostepnychKursow.add(k);
    }

    public static void wyszukaj(Scanner scan){
        System.out.println("Wyszukaj: 1. Pracownika, 2. Studenta, 3. Kurs");
        int x = getInt(scan);
        switch(x){
            case 1: wyszukajPracownika(scan); break;
            case 2: wyszukajStudenta(scan); break;
            case 3: wyszukajKurs(scan); break;
            default: break;
        }
    }

    public static void wyszukajPracownika(Scanner scan){
        System.out.println("Wyszukaj po: 1.Nazwisko");
        //int x = getInt(scan);
        //String zapytanie = getString(scan);
    }

    public static String getString(Scanner scan){
        String x;
        try {
            x = scan.nextLine();
        } catch (Exception e){
            System.out.println("Podaj poprawny tekst: ");
            x = getString(scan);
        }
        return x;
    } 

    public static int getInt(Scanner scan){
        int x;
        try {
            x = scan.nextInt();
            scan.nextLine();
        } catch (Exception e){
            System.out.println("Podaj poprawna liczbe: ");
            scan.nextLine();
            x = getInt(scan);
        }
        return x;
    }

    public static boolean getBoolean(Scanner scan){
        boolean x;
        try {
            x = scan.nextBoolean();
        } catch (Exception e) {
            System.out.println("Podaj poprawny boolean: ");
            x = getBoolean(scan);
        }
        return x;
    }
}