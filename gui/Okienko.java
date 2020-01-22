package gui;

import javax.swing.*;

import uczelnia.Kurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Okienko {

    JButton przycisk1, przycisk2, przycisk3, przycisk4, przycisk5, przycisk6, przycisk7, przycisk8;
    JFrame ramka;
    JPanel container, panel1, panel2;
    JTextArea text;

    public Okienko() {
        ramka = new JFrame("Uczelnia");
        panel1 = new JPanel();
        panel2 = new JPanel();
        container = new JPanel();
        text = new JTextArea();
        przycisk1 = new JButton("1. Dodaj (Pracownika, Studenta, Kurs)");
        przycisk2 = new JButton("2. Wyszukaj po kryterium (Pracownika, Studenta, Kurs)");
        przycisk3 = new JButton("3. Wyswietl wszystkich (Pracownikow, Studentow, Kursy)");
        przycisk4 = new JButton("4. Wyczysc zbior studentow, pracownikow i kursow.");
        przycisk5 = new JButton("5. Posortuj");
        przycisk6 = new JButton("6. Usun po kluczu");
        przycisk7 = new JButton("7. Usun HashSetem");
        przycisk8 = new JButton("8. Wyjdz");

        przycisk1.addActionListener(new Przycisk1());
        przycisk3.addActionListener(new Przycisk3());
        przycisk8.addActionListener(new Przycisk8());

        ramka.getContentPane().add(panel1);
        ramka.getContentPane().add(panel2);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        container.setLayout(new GridLayout(1, 2));
        container.add(panel1);
        container.add(panel2);
        ramka.getContentPane().add(container);
        panel1.add(przycisk1);
        panel1.add(przycisk2);
        panel1.add(przycisk3);
        panel1.add(przycisk4);
        panel1.add(przycisk5);
        panel1.add(przycisk6);
        panel1.add(przycisk7);
        panel1.add(przycisk8);
        panel2.add(text);
        text.setEnabled(false);
        text.setLineWrap(true);
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.pack();
        ramka.setSize(1300, 300);
        ramka.setResizable(false);
        ramka.setVisible(true);
    }

    class Przycisk1 implements ActionListener {

        JPanel inputPanel;
        JTextField[] fields;

        @Override
        public void actionPerformed(ActionEvent e) {
            JList<String> possibleValues = new JList<String>(
                    new String[] { "Pracownika BD", "Pracownika Administracyjnego", "Studenta", "Kurs" });
            JOptionPane.showMessageDialog(null, possibleValues, "Dodaj", JOptionPane.PLAIN_MESSAGE);
            int index = possibleValues.getSelectedIndex() + 1;
            inputPanel = new JPanel();
            fields = new JTextField[numberOfInputs(index)];
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            for (int i = 0; i < fields.length; i++) {
                fields[i] = new JTextField();
                inputPanel.add(fields[i]);
            }
            uczelnia.Kurs[] k = (Kurs[]) uczelnia.Lista5.zbiorDostepnychKursow
                    .toArray(new Kurs[uczelnia.Lista5.zbiorDostepnychKursow.size()]);
            JList<uczelnia.Kurs> kursy = new JList<uczelnia.Kurs>(k);
            if (index == 3) {
                inputPanel.add(kursy);
            }
            JOptionPane.showMessageDialog(null, inputPanel, "Dodaj", JOptionPane.PLAIN_MESSAGE);
            ArrayList<String> inputList = new ArrayList<String>();
            for (JTextField f : fields) {
                inputList.add(f.getText());
            }
            switch (index) {
            case 1:
            case 2:
                uczelnia.Lista5.dodajPracownika(index, inputList);
                break;
            case 3:
                int[] temp = kursy.getSelectedIndices();
                Kurs[] emptyKursy = new Kurs[temp.length];
                for (int i = 0; i < temp.length; i++) {
                    emptyKursy[i] = kursy.getModel().getElementAt(temp[i]);
                }
                uczelnia.Lista5.dodajStudenta(inputList, emptyKursy);
                break;
            case 4:
                uczelnia.Lista5.dodajKurs(inputList);
                break;
            default:
                break;
            }
        }

        public int numberOfInputs(int choice) {
            if (choice == 1 || choice == 2) {
                return 9;
            } else if (choice == 3) {
                return 10;
            } else if (choice == 4) {
                return 4;
            } else {
                return 0;
            }
        }
    }

    class Przycisk3 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JList<String> possibleValues = new JList<String>(new String[] { "Pracownikow", "Studentow", "Kursy" });
            JOptionPane.showMessageDialog(null, possibleValues, "Wybierz", JOptionPane.PLAIN_MESSAGE);
            int index = possibleValues.getSelectedIndex() + 1;
            text.setText("");
            text.append(uczelnia.Lista5.wyswietlWszystko(index));
        }
    }

    class Przycisk8 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == przycisk8) {
                ramka.dispatchEvent(new WindowEvent(ramka, WindowEvent.WINDOW_CLOSING));
            }
        }
    }
}