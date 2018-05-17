package uk.sheffield.pro.college.registry;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // adding a college (just one)
        String collegeName = JOptionPane.showInputDialog(null, "Please input a name of college", "Name a college", JOptionPane.INFORMATION_MESSAGE);
        //College college = new College(collegeName);
        // perhaps we need to check IO for errors and exceptions
        JOptionPane.showMessageDialog(null,"The college " + collegeName + " was successfully added!","College has been added", JOptionPane.INFORMATION_MESSAGE);
        boolean flagAdd = true;         // flag indicating the point when we need to stop adding students to the college
        do {
            String name = JOptionPane.showInputDialog(null, "Please input a student first name", "Enter student's details", JOptionPane.INFORMATION_MESSAGE);
            String surname = JOptionPane.showInputDialog(null, "Please input a student family name", "Enter student's details", JOptionPane.INFORMATION_MESSAGE);

        } while (flagAdd);
    }
}
