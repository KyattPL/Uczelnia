package uczelnia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

import porownywarki.*;

public class Lista5 {

    static ArrayList<Osoba> zbiorOsob = new ArrayList<Osoba>();
    static HashSet<Kurs> zbiorDostepnychKursow = new HashSet<Kurs>();

    static PorownajNazwisko porNazw = null;
    static PorownajImie porImie = null;
    static PorownajWiek porWiek = null;
    static PorownajNazwiskoKurs porNazwKurs = null;
    static PorownajECTS porECTS = null;

    public static void main(String[] args) {

        boolean czyWyjscie = false;
        Scanner scan = new Scanner(System.in);
        int wybor;
        OperacjeNaDanych.wczytajOsoby(zbiorOsob);
        OperacjeNaDanych.wczytajKursy(zbiorDostepnychKursow);
        wczytajPorownywarki();

        while (!czyWyjscie) {
            wyswietlMenu();
            wybor = OperacjeNaDanych.getInt(scan);
            wywolajOpcje(wybor, scan);
            if (wybor == 0) {
                czyWyjscie = true;
            }
        }
        scan.close();
        OperacjeNaDanych.zapiszOsoby(zbiorOsob);
        OperacjeNaDanych.zapiszKursy(zbiorDostepnychKursow);
    }

    public static void wczytajPorownywarki(){
        porNazw = new PorownajNazwisko();
        porImie = new PorownajImie();
        porWiek = new PorownajWiek();
        porNazwKurs = new PorownajNazwiskoKurs();
        porECTS = new PorownajECTS();
    }

    public static void wyswietlMenu() {
        System.out.println("1. Dodaj (Pracownika, Studenta, Kurs)");
        System.out.println("2. Wyszukaj po kryterium (Pracownika, Studenta, Kurs)");
        System.out.println("3. Wyswietl wszystkich (Pracownikow, Studentow, Kursy)");
        System.out.println("4. Wyczysc zbior studentow, pracownikow i kursow");
        System.out.println("5. Posortuj");
        System.out.println("0. Wyjdz");
    }

    public static void wywolajOpcje(int wybor, Scanner scan) {
        switch (wybor) {
        case 1:
            dodajDoKolekcji(scan);
            break;
        case 2:
            wyszukaj(scan);
            break;
        case 3:
            wyswietlWszystko(scan);
            break;
        case 4:
            zbiorOsob.clear();
            zbiorDostepnychKursow.clear();
            break;
        case 5:
            posortuj(scan);
            break;
        default:
            break;
        }
    }

    public static void dodajDoKolekcji(Scanner scan) {
        System.out.println("Dodaj: 1. Pracownika BD, 2. Pracownika Administracyjnego, 3. Studenta, 4. Kurs");
        int wybor = OperacjeNaDanych.getInt(scan);

        switch (wybor) {
        case 1:
            dodajPracownika(scan, 1);
            break;
        case 2:
            dodajPracownika(scan, 2);
            break;
        case 3:
            dodajStudenta(scan);
            break;
        case 4:
            dodajKurs(scan);
            break;
        default:
            break;
        }
    }

    public static void dodajPracownika(Scanner scan, int wybor) {
        String imie = OperacjeNaDanych.getString(scan);
        String nazwisko = OperacjeNaDanych.getString(scan);
        String PESEL = OperacjeNaDanych.getString(scan);
        int wiek = OperacjeNaDanych.getInt(scan);
        String plecStr = OperacjeNaDanych.getString(scan);
        Osoba.Plec plec;
        while (!plecStr.equals("M") && !plecStr.equals("K")) {
            plecStr = OperacjeNaDanych.getString(scan);
        }
        if (plecStr.equals("M")) {
            plec = Osoba.Plec.M;
        } else {
            plec = Osoba.Plec.K;
        }
        int staz = OperacjeNaDanych.getInt(scan);
        String stanowisko = OperacjeNaDanych.getString(scan);
        int pensja = OperacjeNaDanych.getInt(scan);
        int punktacjaLubNadgodziny = OperacjeNaDanych.getInt(scan);
        if (wybor == 1) {
            PracownikBD prac = new PracownikBD(imie, nazwisko, PESEL, wiek, plec, staz, stanowisko, pensja,
                    punktacjaLubNadgodziny);
            zbiorOsob.add(prac);
        } else {
            PracownikAdministracyjny prac = new PracownikAdministracyjny(imie, nazwisko, PESEL, wiek, plec, staz,
                    stanowisko, pensja, punktacjaLubNadgodziny);
            zbiorOsob.add(prac);
        }
    }

    public static void dodajStudenta(Scanner scan) {
        String imie = OperacjeNaDanych.getString(scan);
        String nazwisko = OperacjeNaDanych.getString(scan);
        String PESEL = OperacjeNaDanych.getString(scan);
        int wiek = OperacjeNaDanych.getInt(scan);
        String plecStr = OperacjeNaDanych.getString(scan);
        Osoba.Plec plec;
        while (!plecStr.equals("M") && !plecStr.equals("K")) {
            plecStr = OperacjeNaDanych.getString(scan);
        }
        if (plecStr.equals("M")) {
            plec = Osoba.Plec.M;
        } else {
            plec = Osoba.Plec.K;
        }
        String nrIndeksu = OperacjeNaDanych.getString(scan);
        HashSet<Kurs> kursyStudenta = new HashSet<Kurs>();
        przydzielKursy(kursyStudenta, scan);
        boolean czyERASMUS = OperacjeNaDanych.getBoolean(scan);
        boolean czy1Stopien = OperacjeNaDanych.getBoolean(scan);
        boolean czyStacjonarne = OperacjeNaDanych.getBoolean(scan);
        Student stud = new Student(imie, nazwisko, PESEL, wiek, plec, nrIndeksu, kursyStudenta, czyERASMUS, czy1Stopien,
                czyStacjonarne);
        zbiorOsob.add(stud);
    }

    public static void przydzielKursy(HashSet<Kurs> kursyStudenta, Scanner scan) {
        int i = 1;
        Kurs[] zbior = zbiorDostepnychKursow.toArray(new Kurs[zbiorDostepnychKursow.size()]);
        for (Kurs k : zbior) {
            System.out.println(i + ". " + k.getNazwa());
            i++;
        }
        System.out.println("Dodaj wszystkie kursy studenta (Podaj liczbe). '0' zeby wyjsc.");
        int input = OperacjeNaDanych.getInt(scan);
        while (input != 0) {
            kursyStudenta.add(zbior[input - 1]);
            input = OperacjeNaDanych.getInt(scan);
        }
    }

    public static void dodajKurs(Scanner scan) {
        String nazwa = OperacjeNaDanych.getString(scan);
        String imieProwadzacego = OperacjeNaDanych.getString(scan);
        String nazwiskoProwadzacego = OperacjeNaDanych.getString(scan);
        int punktyECTS = OperacjeNaDanych.getInt(scan);
        Kurs k = new Kurs(nazwa, imieProwadzacego, nazwiskoProwadzacego, punktyECTS);
        zbiorDostepnychKursow.add(k);
    }

    public static void wyszukaj(Scanner scan) {
        System.out.println("Wyszukaj: 1. Pracownika, 2. Studenta, 3. Kurs");
        int x = OperacjeNaDanych.getInt(scan);
        switch (x) {
        case 1:
            wyszukajPracownika(scan);
            break;
        case 2:
            wyszukajStudenta(scan);
            break;
        case 3:
            wyszukajKurs(scan);
            break;
        default:
            break;
        }
    }

    public static void wyszukajPracownika(Scanner scan) {
        System.out.println("Wyszukaj po: 1.Nazwisko, 2.Staz");
        int x = OperacjeNaDanych.getInt(scan);

        if (x == 1) {
            String zapytanieStr = OperacjeNaDanych.getString(scan);
            for (Osoba k : zbiorOsob) {
                if (k instanceof PracownikUczelni) {
                    if (k.getNazwisko().equals(zapytanieStr)) {
                        System.out.println(k.toString());
                    }
                }
            }
        } else if (x == 2) {
            int zapytanie = OperacjeNaDanych.getInt(scan);
            for (Osoba k : zbiorOsob) {
                if (k instanceof PracownikUczelni) {
                    PracownikUczelni prac = (PracownikUczelni) k;
                    if (prac.getStaz() == zapytanie) {
                        System.out.println(k.toString());
                    }
                }
            }
        }
    }

    public static void wyszukajStudenta(Scanner scan) {
        System.out.println("Wyszukaj po: 1.Nazwisko, 2.Czy 1 stopien");
        int x = OperacjeNaDanych.getInt(scan);
        if (x == 1) {
            String zapytanieStr = OperacjeNaDanych.getString(scan);
            for (Osoba k : zbiorOsob) {
                if (k instanceof Student) {
                    Student stud = (Student) k;
                    if (stud.getNazwisko().equals(zapytanieStr)) {
                        System.out.println(k.toString());
                    }
                }
            }
        } else if (x == 2) {
            boolean zapytanieBool = OperacjeNaDanych.getBoolean(scan);
            for (Osoba k : zbiorOsob) {
                if (k instanceof Student) {
                    Student stud = (Student) k;
                    if (stud.getCzy1Stopien() == zapytanieBool) {
                        System.out.println(k.toString());
                    }
                }
            }
        }
    }

    public static void wyszukajKurs(Scanner scan) {
        System.out.println("Wyszukaj po: 1.Nazwisko prowadzacego, 2.Punkty ECTS");
        int x = OperacjeNaDanych.getInt(scan);
        if (x == 1) {
            String zapytanieStr = OperacjeNaDanych.getString(scan);
            for (Kurs k : zbiorDostepnychKursow) {
                if (k.getNazwiskoProwadzacego().equals(zapytanieStr)) {
                    System.out.println(k.toString());
                }
            }
        } else if (x == 2) {
            int zapytanie = OperacjeNaDanych.getInt(scan);
            for (Kurs k : zbiorDostepnychKursow) {
                if (k.getPunktyECTS() == zapytanie) {
                    System.out.println(k.toString());
                }
            }
        }
    }

    public static void posortuj(Scanner scan) {
        System.out.println("Posortuj 1. Pracownikow i studentow, 2. Kursy");
        int wybor = OperacjeNaDanych.getInt(scan);
        switch (wybor) {
            case 1:
                System.out.println("Sortuj po: 1. Nazwisko, 2. Nazwisko i Imie, 3. Nazwisko i Wiek");
                int input = OperacjeNaDanych.getInt(scan);
                posortujOsoby(input);
                break;
            case 2:
                System.out.println("Sortuj po: 1. Nazwisko prowadzacego, 2. Liczba pkt ECTS");
                int input2 = OperacjeNaDanych.getInt(scan);
                posortujKursy(input2);
                break;
            default:
                break;
        }
    }
    
    public static void posortujOsoby(int input){
        switch (input) {
            case 1:
                Collections.sort(zbiorOsob, porNazw);
                break;
            case 2:
                Collections.sort(zbiorOsob, porNazw);
                Collections.sort(zbiorOsob, porImie);
                break;
            case 3:
                Collections.sort(zbiorOsob, porNazw);
                Collections.sort(zbiorOsob, porWiek);
                break;
            default:
                posortujOsoby(1);
                break;
        }
    }

    public static void posortujKursy(int input){
        ArrayList<Kurs> temp = new ArrayList<Kurs>(zbiorDostepnychKursow);
        switch (input) {
            case 1:
                Collections.sort(temp, porNazwKurs);
                for (Kurs k : temp) {
                    System.out.println(k.toString());
                }
                break;
            case 2:
                Collections.sort(temp, porECTS);
                for (Kurs k : temp) {
                    System.out.println(k.toString());
                }
                break;
            default:
                posortujKursy(1);
                break;
        }
    }

    public static void wyswietlWszystko(Scanner scan) {
        System.out.println("Wyswietl wszystkich: 1.Pracownikow, 2.Studentow, 3.Kursy");
        int x = OperacjeNaDanych.getInt(scan);
        switch (x) {
        case 1:
            wyswietlPracownikow();
            break;
        case 2:
            wyswietlStudentow();
            break;
        case 3:
            wyswietlKursy();
            break;
        default:
            break;
        }
    }

    public static void wyswietlPracownikow() {
        for (Osoba k : zbiorOsob) {
            if (k instanceof PracownikBD) {
                PracownikBD temp = (PracownikBD) k;
                System.out.println(temp.toString());
            } else if (k instanceof PracownikAdministracyjny) {
                PracownikAdministracyjny temp = (PracownikAdministracyjny) k;
                System.out.println(temp.toString());
            }
        }
    }

    public static void wyswietlStudentow() {
        for (Osoba k : zbiorOsob) {
            if (k instanceof Student) {
                Student temp = (Student) k;
                System.out.println(temp.toString());
            }
        }
    }

    public static void wyswietlKursy() {
        for (Kurs k : zbiorDostepnychKursow) {
            System.out.println(k.toString());
        }
    }
}