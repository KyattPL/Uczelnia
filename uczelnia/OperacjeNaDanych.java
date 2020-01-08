package uczelnia;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class OperacjeNaDanych {

    public static String getString(Scanner scan) {
        String x;
        try {
            x = scan.nextLine();
        } catch (Exception e) {
            System.out.println("Podaj poprawny tekst: ");
            x = getString(scan);
        }
        return x;
    }

    public static int getInt(Scanner scan) {
        int x;
        try {
            x = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            System.out.println("Podaj poprawna liczbe: ");
            scan.nextLine();
            x = getInt(scan);
        }
        return x;
    }

    public static boolean getBoolean(Scanner scan) {
        boolean x;
        try {
            x = scan.nextBoolean();
            scan.nextLine();
        } catch (Exception e) {
            System.out.println("Podaj poprawny boolean: ");
            scan.nextLine();
            x = getBoolean(scan);
        }
        return x;
    }

    public static void zapiszOsoby(ArrayList<Osoba> zbiorOsob) {
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream("daneOsob.ser"));
            for (Osoba o : zbiorOsob) {
                os.writeObject(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void wczytajOsoby(ArrayList<Osoba> zbiorOsob) {
        ObjectInputStream is = null;
        boolean isEmpty = false;
        try {
            is = new ObjectInputStream(new FileInputStream("daneOsob.ser"));
            while (!isEmpty) {
                Object obj = is.readObject();
                if (obj != null) {
                    if (obj instanceof PracownikAdministracyjny) {
                        PracownikAdministracyjny temp = (PracownikAdministracyjny) obj;
                        zbiorOsob.add(temp);
                    } else if (obj instanceof PracownikBD) {
                        PracownikBD temp = (PracownikBD) obj;
                        zbiorOsob.add(temp);
                    } else if (obj instanceof Student) {
                        Student temp = (Student) obj;
                        zbiorOsob.add(temp);
                    }
                } else {
                    isEmpty = true;
                }
            }
            is.close();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void zapiszKursy(HashSet<Kurs> kursy) {
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream("daneKursow.ser"));
            for (Kurs k : kursy) {
                os.writeObject(k);
            }
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void wczytajKursy(HashSet<Kurs> kursy) {
        ObjectInputStream is = null;
        boolean isEmpty = false;
        try {
            is = new ObjectInputStream(new FileInputStream("daneKursow.ser"));
            while (!isEmpty) {
                Object obj = is.readObject();
                if (obj != null) {
                    if (obj instanceof Kurs) {
                        Kurs temp = (Kurs) obj;
                        kursy.add(temp);
                    }
                } else {
                    isEmpty = false;
                }
            }
            is.close();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}