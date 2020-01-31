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

    public static ArrayList<Osoba> zbiorOsob = new ArrayList<Osoba>();
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
            wybor = OperacjeNaDanych.getInt(scan);
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

    public static void dodajKurs(ArrayList<String> lista) {
        String nazwa = lista.get(0);
        String imieProwadzacego = lista.get(1);
        String nazwiskoProwadzacego = lista.get(2);
        int punktyECTS = Integer.parseInt(lista.get(3));
        Kurs k = new Kurs(nazwa, imieProwadzacego, nazwiskoProwadzacego, punktyECTS);
        zbiorDostepnychKursow.add(k);
    }

    public static void usunOsobe(int choice, String input) {
        Iterator<Osoba> it = zbiorOsob.iterator();
        System.out.println("Usun po: 1. Imieniu, 2. Nazwisku, 3. Wieku");
        switch (choice) {
        case 1:
            System.out.print("Podaj imie: ");
            String temp = input;
            while (it.hasNext()) {
                if (it.next().getImie().equals(temp)) {
                    it.remove();
                }
            }
            break;
        case 2:
            System.out.print("Podaj nazwisko: ");
            String temp2 = input;
            while (it.hasNext()) {
                if (it.next().getNazwisko().equals(temp2)) {
                    it.remove();
                }
            }
            break;
        case 3:
            System.out.print("Podaj wiek: ");
            int temp3 = Integer.parseInt(input);
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

    public static void usunKurs(int choice, String input) {
        Iterator<Kurs> it = zbiorDostepnychKursow.iterator();
        System.out.println("Usun po: 1. Nazwisku prowadzacego, 2. Punktach ECTS");
        switch (choice) {
        case 1:
            System.out.print("Podaj nazwisko: ");
            String temp = input;
            while (it.hasNext()) {
                if (it.next().getNazwiskoProwadzacego().equals(temp)) {
                    it.remove();
                }
            }
            break;
        case 2:
            System.out.print("Podaj ECTS: ");
            int temp2 = Integer.parseInt(input);
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

    public static String wyszukajPracownika(int x, String input) {
        System.out.println("Wyszukaj po: 1.Nazwisko, 2.Staz");
        String suma = "";
        if (x == 1) {
            String zapytanieStr = input;
            for (Osoba k : zbiorOsob) {
                if (k instanceof PracownikUczelni) {
                    if (k.getNazwisko().equals(zapytanieStr)) {
                        suma += k.toString();
                    }
                }
                suma += "\n";
            }
        } else if (x == 2) {
            int zapytanie = Integer.parseInt(input);
            for (Osoba k : zbiorOsob) {
                if (k instanceof PracownikUczelni) {
                    PracownikUczelni prac = (PracownikUczelni) k;
                    if (prac.getStaz() == zapytanie) {
                        suma += k.toString();
                    }
                }
                suma += "\n";
            }
        }
        return suma;
    }

    public static String wyszukajStudenta(int x, String input) {
        System.out.println("Wyszukaj po: 1.Nazwisko, 2.Czy 1 stopien");
        String suma = "";
        if (x == 1) {
            String zapytanieStr = input;
            for (Osoba k : zbiorOsob) {
                if (k instanceof Student) {
                    Student stud = (Student) k;
                    if (stud.getNazwisko().equals(zapytanieStr)) {
                        suma += k.toString();
                    }
                }
                suma += "\n";
            }
        } else if (x == 2) {
            boolean zapytanieBool = Boolean.parseBoolean(input);
            for (Osoba k : zbiorOsob) {
                if (k instanceof Student) {
                    Student stud = (Student) k;
                    if (stud.getCzy1Stopien() == zapytanieBool) {
                        suma += k.toString();
                    }
                }
                suma += "\n";
            }
        }
        return suma;
    }

    public static String wyszukajKurs(int x, String input) {
        System.out.println("Wyszukaj po: 1.Nazwisko prowadzacego, 2.Punkty ECTS");
        String suma = "";
        if (x == 1) {
            String zapytanieStr = input;
            for (Kurs k : zbiorDostepnychKursow) {
                if (k.getNazwiskoProwadzacego().equals(zapytanieStr)) {
                    suma += k.toString() + "\n";
                }
                System.out.println(k.getNazwiskoProwadzacego());
                System.out.println(zapytanieStr);
            }
        } else if (x == 2) {
            int zapytanie = Integer.parseInt(input);
            for (Kurs k : zbiorDostepnychKursow) {
                if (k.getPunktyECTS() == zapytanie) {
                    suma += k.toString() + "\n";
                }
            }
        }
        return suma;
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

    public static String posortujKursy(int input) {
        ArrayList<Kurs> temp = new ArrayList<Kurs>(zbiorDostepnychKursow);
        String suma = "";
        switch (input) {
        case 1:
            Collections.sort(temp, porNazwKurs);
            for (Kurs k : temp) {
                suma += k.toString();
                suma += "\n";
            }
            break;
        case 2:
            Collections.sort(temp, porECTS);
            for (Kurs k : temp) {
                suma += k.toString();
                suma += "\n";
            }
            break;
        default:
            posortujKursy(1);
            break;
        }
        return suma;
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
                suma += "\n";
            } else if (k instanceof PracownikAdministracyjny) {
                PracownikAdministracyjny temp = (PracownikAdministracyjny) k;
                suma += temp.toString();
                suma += "\n";
            }
        }
        return suma;
    }

    public static String wyswietlStudentow() {
        String suma = "";
        for (Osoba k : zbiorOsob) {
            if (k instanceof Student) {
                Student temp = (Student) k;
                suma += temp.toString();
                suma += "\n";
            }
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

    public static String usunHashSetem() {
        HashSet<Student> setStudentow = new HashSet<Student>();
        HashSet<PracownikUczelni> setPracownikow = new HashSet<PracownikUczelni>();
        String suma = "";

        for (Osoba o : zbiorOsob) {
            if (o instanceof Student) {
                setStudentow.add((Student) o);
            } else if (o instanceof PracownikUczelni) {
                setPracownikow.add((PracownikUczelni) o);
            }
        }

        for (Student s : setStudentow) {
            suma += s.toString();
            suma += "\n";
        }
        for (PracownikUczelni p : setPracownikow) {
            suma += p.toString();
            suma += "\n";
        }
        return suma;
    }
}