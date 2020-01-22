package uczelnia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import porownywarki.*;
import gui.*;

public class Lista5 {

    static ArrayList<Osoba> zbiorOsob = new ArrayList<Osoba>();
    public static HashSet<Kurs> zbiorDostepnychKursow = new HashSet<Kurs>();

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
        Okienko okno = new Okienko();

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

    public static void wczytajPorownywarki() {
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
        System.out.println("6. Usun po kluczu");
        System.out.println("7. Usun HashSetem");
        System.out.println("0. Wyjdz");
    }

    public static void wywolajOpcje(int wybor, Scanner scan) {
        switch (wybor) {
        case 1:
            // dodajDoKolekcji(0);
            break;
        case 2:
            wyszukaj(scan);
            break;
        case 3:
            wyswietlWszystko(0);
            break;
        case 4:
            zbiorOsob.clear();
            zbiorDostepnychKursow.clear();
            break;
        case 5:
            posortuj(scan);
            break;
        case 6:
            usun(scan);
            break;
        case 7:
            usunHashSetem();
            break;
        default:
            break;
        }
    }

    /*
     * public static void dodajDoKolekcji(int wybor) { System.out.
     * println("Dodaj: 1. Pracownika BD, 2. Pracownika Administracyjnego, 3. Studenta, 4. Kurs"
     * ); String s = ""; switch (wybor) { case 1: s = dodajPracownika(1); break;
     * case 2: s = dodajPracownika(2); break; case 3: dodajStudenta(); break; case
     * 4: dodajKurs(new ArrayList<String>()); break; default: break; } }
     */

    public static void dodajPracownika(int wybor, ArrayList<String> inputList) {

        String imie = inputList.get(0);
        String nazwisko = inputList.get(1);
        String PESEL = inputList.get(2);
        int wiek = Integer.parseInt(inputList.get(3));
        String plecStr = inputList.get(4);
        Osoba.Plec plec;
        if (plecStr.equals("M")) {
            plec = Osoba.Plec.M;
        } else {
            plec = Osoba.Plec.K;
        }
        int staz = Integer.parseInt(inputList.get(5));
        String stanowisko = inputList.get(6);
        int pensja = Integer.parseInt(inputList.get(7));
        int punktacjaLubNadgodziny = Integer.parseInt(inputList.get(8));
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

    public static void dodajStudenta(ArrayList<String> inputList, Kurs[] emptyKursy) {
        String imie = inputList.get(0);
        String nazwisko = inputList.get(1);
        String PESEL = inputList.get(2);
        int wiek = Integer.parseInt(inputList.get(3));
        String plecStr = inputList.get(4);
        Osoba.Plec plec;
        if (plecStr.equals("M")) {
            plec = Osoba.Plec.M;
        } else {
            plec = Osoba.Plec.K;
        }
        String nrIndeksu = inputList.get(5);
        HashSet<Kurs> kursyStudenta = new HashSet<Kurs>(Arrays.asList(emptyKursy));
        boolean czyERASMUS = Boolean.parseBoolean(inputList.get(6));
        boolean czy1Stopien = Boolean.parseBoolean(inputList.get(7));
        boolean czyStacjonarne = Boolean.parseBoolean(inputList.get(8));
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

    public static void dodajKurs(ArrayList<String> lista) {
        String nazwa = lista.get(0);
        String imieProwadzacego = lista.get(1);
        String nazwiskoProwadzacego = lista.get(2);
        int punktyECTS = Integer.parseInt(lista.get(3));
        Kurs k = new Kurs(nazwa, imieProwadzacego, nazwiskoProwadzacego, punktyECTS);
        zbiorDostepnychKursow.add(k);
    }

    public static void usun(Scanner scan) {
        System.out.println("Co chcesz usunac: 1. Osobe, 2. Kurs");
        int input = OperacjeNaDanych.getInt(scan);
        switch (input) {
        case 1:
            usunOsobe(scan);
            break;
        case 2:
            usunKurs(scan);
            break;
        default:
            break;
        }
    }

    public static void usunOsobe(Scanner scan) {
        Iterator<Osoba> it = zbiorOsob.iterator();
        System.out.println("Usun po: 1. Imieniu, 2. Nazwisku, 3. Wieku");
        int input = OperacjeNaDanych.getInt(scan);
        switch (input) {
        case 1:
            System.out.print("Podaj imie: ");
            String temp = OperacjeNaDanych.getString(scan);
            while (it.hasNext()) {
                if (it.next().getImie().equals(temp)) {
                    it.remove();
                }
            }
            break;
        case 2:
            System.out.print("Podaj nazwisko: ");
            String temp2 = OperacjeNaDanych.getString(scan);
            while (it.hasNext()) {
                if (it.next().getNazwisko().equals(temp2)) {
                    it.remove();
                }
            }
            break;
        case 3:
            System.out.print("Podaj wiek: ");
            int temp3 = OperacjeNaDanych.getInt(scan);
            while (it.hasNext()) {
                if (it.next().getWiek() == temp3) {
                    it.remove();
                }
            }
            break;
        default:
            break;
        }
    }

    public static void usunKurs(Scanner scan) {
        Iterator<Kurs> it = zbiorDostepnychKursow.iterator();
        System.out.println("Usun po: 1. Nazwisku prowadzacego, 2. Punktach ECTS");
        int input = OperacjeNaDanych.getInt(scan);
        switch (input) {
        case 1:
            System.out.print("Podaj nazwisko: ");
            String temp = OperacjeNaDanych.getString(scan);
            while (it.hasNext()) {
                if (it.next().getNazwiskoProwadzacego().equals(temp)) {
                    it.remove();
                }
            }
            break;
        case 2:
            System.out.print("Podaj ECTS: ");
            int temp2 = OperacjeNaDanych.getInt(scan);
            while (it.hasNext()) {
                if (it.next().getPunktyECTS() == temp2) {
                    it.remove();
                }
            }
            break;
        default:
            break;
        }
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

    public static void posortujOsoby(int input) {
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

    public static void posortujKursy(int input) {
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

    public static String wyswietlWszystko(int wybor) {
        System.out.println("Wyswietl wszystkich: 1.Pracownikow, 2.Studentow, 3.Kursy");
        String s = "";
        switch (wybor) {
        case 1:
            s = wyswietlPracownikow();
            break;
        case 2:
            s = wyswietlStudentow();
            break;
        case 3:
            s = wyswietlKursy();
            break;
        default:
            break;
        }
        return s;
    }

    public static String wyswietlPracownikow() {
        String suma = "";
        for (Osoba k : zbiorOsob) {
            if (k instanceof PracownikBD) {
                PracownikBD temp = (PracownikBD) k;
                suma += temp.toString();
            } else if (k instanceof PracownikAdministracyjny) {
                PracownikAdministracyjny temp = (PracownikAdministracyjny) k;
                suma += temp.toString();
            }
            suma += "\n";
        }
        return suma;
    }

    public static String wyswietlStudentow() {
        String suma = "";
        for (Osoba k : zbiorOsob) {
            if (k instanceof Student) {
                Student temp = (Student) k;
                suma += temp.toString();
            }
            suma += "\n";
        }
        return suma;
    }

    public static String wyswietlKursy() {
        String suma = "";
        for (Kurs k : zbiorDostepnychKursow) {
            suma += k.toString();
            suma += "\n";
        }
        return suma;
    }

    public static void usunHashSetem() {
        HashSet<Student> setStudentow = new HashSet<Student>();
        HashSet<PracownikUczelni> setPracownikow = new HashSet<PracownikUczelni>();

        for (Osoba o : zbiorOsob) {
            if (o instanceof Student) {
                System.out.println(o.hashCode());
                setStudentow.add((Student) o);
            } else if (o instanceof PracownikUczelni) {
                setPracownikow.add((PracownikUczelni) o);
            }
        }

        for (Student s : setStudentow) {
            System.out.println(s.toString());
        }
        for (PracownikUczelni p : setPracownikow) {
            System.out.println(p.toString());
        }
    }
}