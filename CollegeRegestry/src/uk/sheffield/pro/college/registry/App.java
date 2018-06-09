package uk.sheffield.pro.college.registry;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

public class App {
    private JPanel panelMain;
    private JTextField surnameTexField;
    private JTextField genderTextField;
    private JTextField programmeTextField;
    private JLabel AppLabel;
    private JTextField courseTextField;
    private JTextField pathwayTextField;
    private JTextField nationalityTextField;
    private JButton addStudentButton;
    private JTextField nameTextField;
    private JRadioButton pathwayRadioButton;
    private JRadioButton nameRadioButton;
    private JRadioButton programmeRadioButton;
    private JRadioButton courseRadioButton;
    private JRadioButton genderRadioButton;
    private JRadioButton surnameRadioButton;
    private JRadioButton nationalityRadioButton;
    private JTextArea LogArea;
    private JButton findButton;
    private JTextField searchTextField;
    private JTextField URLtextField1;
    private JTextField PorttextField;
    private JTextField UserField;
    private JPasswordField PassField;
    private JButton LOGINButton;
    private JButton Nextbutton;
    private JButton previousButton;
    private JLabel NationalityRadio;
    private JButton reportButton;
    private JTextField reportPath;
    private Connection conn;
    public int radio;

    private static College college;
    public ResultSet resultSet;

    public App() {
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    // adding a student to the College
                // if at least one field is empty, show the error message
                if(nameTextField.getText().isEmpty() || surnameTexField.getText().isEmpty() ||
                        genderTextField.getText().isEmpty() || programmeTextField.getText().isEmpty() ||
                        nationalityTextField.getText().isEmpty() || courseTextField.getText().isEmpty() ||
                        pathwayTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "One or more fields are empty. Each field should be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    try {
                        Statement statement = conn.createStatement();
                        String sql = "INSERT INTO `student` (`id`, `Name`, `Surname`, `Gender`, `Nationality`, `Programme`, `Course`, `Pathway`) VALUES (NULL, '"+nameTextField.getText()+"', '"+surnameTexField.getText()+"', '"+genderTextField.getText()+"', '"+nationalityTextField.getText()+"', '"+programmeTextField.getText()+"', '"+courseTextField.getText()+"', '"+pathwayTextField.getText()+"');";
                        //INSERT INTO `student` (`id`, `Name`, `Surname`, `Gender`, `Nationality`, `Programme`, `Course`, `Pathway`) VALUES (NULL, 'Test', 'TestSurname', 'M', 'UK', 'Engineer', 'SPPM01', 'M&S')
                        //JOptionPane.showMessageDialog(null, sql, "Test", JOptionPane.ERROR_MESSAGE);
                        statement.executeUpdate(sql);
                        JOptionPane.showMessageDialog(null,"Completed Adding!","Result",JOptionPane.INFORMATION_MESSAGE);

                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        });
        searchTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                searchTextField.setText("");
            }
        });
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    URLtextField1.getText();

                    String passText = new String(PassField.getPassword());
                    String username = UserField.getText();
                    String url = "jdbc:mysql://"+URLtextField1.getText()+":"+PorttextField.getText()+"/college?useSSL=false&user="+username+"&password="+passText+"&serverTimezone=UTC";
                    //PassField.getPassword()
                    JOptionPane.showMessageDialog(null, "Connected!", "Connection", JOptionPane.INFORMATION_MESSAGE);


                    //String password = (PassField.getPassword());
                    //Class.forName(driver);
                    //DriverManager.setLoginTimeout(100000);
                    conn = DriverManager.getConnection(url,username,passText);//,username,PassField.getPassword());

                    Statement statement = conn.createStatement();

                    resultSet = statement.executeQuery("SELECT * FROM student");
                    resultSet.next();
                    printSqlTable(resultSet);
                    //LogArea.append(resultSet.getString(1)+":"+resultSet.getString(2));
                }
                catch (Exception ce){
                    JOptionPane.showMessageDialog(null, ce, "Connection", JOptionPane.INFORMATION_MESSAGE);
                    //System.out.println(ce);
                }

                //JOptionPane.showMessageDialog(null, "Connected!", "Connection", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (searchTextField.getText().isEmpty() || searchTextField.getText().equals("Select the criteria of a search and type the according information here")) {
                        JOptionPane.showMessageDialog(null, "You didn't type a search key!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    outInfo(radio,LogArea,searchTextField);
//                    if (nameRadioButton.isEnabled()) {
//                        outInfo(1, LogArea, searchTextField);
//                    } else if (surnameRadioButton.isEnabled()) {
//                        outInfo(2, LogArea, searchTextField);
//                    } else if (genderRadioButton.isEnabled()) {
//                        outInfo(3, LogArea, searchTextField);
//                    } else if (nationalityRadioButton.isEnabled()) {
//                        outInfo(4, LogArea, searchTextField);
//                    } else if (programmeRadioButton.isEnabled()) {
//                        outInfo(5, LogArea, searchTextField);
//                    } else if (courseRadioButton.isEnabled()) {
//                        outInfo(6, LogArea, searchTextField);
//                    } else if (pathwayRadioButton.isEnabled()) {
//                        outInfo(7, LogArea, searchTextField);
//                    }
                }
                catch (SQLException se){
                    JOptionPane.showMessageDialog(null, se, "Test", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        Nextbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //NextButton
                try{
                    if (resultSet.next())
                        printSqlTable(resultSet);
                    else {
                        JOptionPane.showMessageDialog(null, "This is the last result", "Result", JOptionPane.INFORMATION_MESSAGE);
                        resultSet.previous();
                    }

                }
                catch (SQLException se){
                    JOptionPane.showMessageDialog(null, se, "Test", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        nameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Radio Name
                radio = 1;
            }
        });
        surnameRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //Radio SurName
                radio = 2;
            }
        });
        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //previous button
                try{
                    if (resultSet.previous())
                        printSqlTable(resultSet);
                    else {
                        JOptionPane.showMessageDialog(null, "This is the last result", "Result", JOptionPane.INFORMATION_MESSAGE);
                        resultSet.next();}

                }
                catch (SQLException se){
                    JOptionPane.showMessageDialog(null, se, "Test", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        genderRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//gender
                radio = 3;
            }
        });
        NationalityRadio.addComponentListener(new ComponentAdapter() {
        });
        nationalityRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//Nationality
                radio = 4;
            }
        });
        programmeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//Programme
                radio = 5;
            }
        });
        courseRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//Course
                radio = 6;
            }
        });
        pathwayRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//Pathway
                radio = 7;
            }
        });
        reportPath.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                reportPath.setText("");
            }
        });
        reportButton.addActionListener(new ActionListener() { // Create a report
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = reportPath.getText();
                try {
                    path = path.trim();
                    path = path.toUpperCase();
                    if(path.equals("TYPE IN HERE THE FINAL PATH FOR THE REPORT") || path.equals("")) {
                        path = "C://StudentsRegistry";
                        File dir = new File(path);
                        if (!dir.exists()) {
                            dir.mkdir();
                            JOptionPane.showMessageDialog(null, "As there was no changes or the path was empty, the default directory " + path + " has been successfully created!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    else {
                        File dir = new File(path);
                        if (!dir.exists()) {
                            dir.mkdir();
                            JOptionPane.showMessageDialog(null, "The directory " + path + " has been successfully created!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
                catch (Exception we) {
                    JOptionPane.showMessageDialog(null, "Something went wrong when creating a directory! Please try again!", "Report creating error", JOptionPane.ERROR_MESSAGE);
                }
                File file = new File(path + "/StudentRegistry.txt");
                try {
                    if(file.exists()) {
                        file.delete();
                        file.createNewFile();
                    }
                }
                catch (IOException we) {
                    JOptionPane.showMessageDialog(null, "Something went wrong when creating a file! Please try again!", "Report creating error", JOptionPane.ERROR_MESSAGE);
                }
                StringBuilder sb2 = new StringBuilder("------------------------------------------------------------------------"+System.lineSeparator());
                try {
                    while (resultSet.next()) {
                        sb2.append("# " + resultSet.getString(1) + System.lineSeparator());
                        sb2.append("Name: " + resultSet.getString(2) + System.lineSeparator());
                        sb2.append("Surname: " + resultSet.getString(3) + System.lineSeparator());
                        sb2.append("GENDER: " + resultSet.getString(4) + System.lineSeparator());
                        sb2.append("Nationality: " + resultSet.getString(5) + System.lineSeparator());
                        sb2.append("Programme: " + resultSet.getString(6) + System.lineSeparator());
                        sb2.append("Course: " + resultSet.getString(7) + System.lineSeparator());
                        sb2.append("Pathway: " + resultSet.getString(8) + System.lineSeparator());
                        sb2.append("------------------------------------------------------------------------" + System.lineSeparator());
                    }
                }
                catch (SQLException we) {
                    JOptionPane.showMessageDialog(null, "Something went wrong when getting the information from the database! Please try again!", "Report creating error", JOptionPane.ERROR_MESSAGE);
                }
                String data = sb2.toString();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file));)
                {
                    writer.write(data);
                }
                catch (IOException we){
                    JOptionPane.showMessageDialog(null, "Something went wrong when writing into the file! Please try again!", "Report creating error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        college = new College();
        JFrame frame = new JFrame("College Registry System by SPPM01/SPPS01");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700, 700);
        frame.setResizable(false);

    }

    public void outInfo(int a, JTextArea LogArea, JTextField searchTextField) throws SQLException{
        try {
            int i = 1;
            String sqlcolumn;
            Statement statement = conn.createStatement();
            String text = searchTextField.getText();
            switch (a) {
                case 1:     // name
                    LogArea.setText("");
                    resultSet = statement.executeQuery("SELECT * FROM `student` WHERE Name LIKE \"%"+text+"%\"");
                    resultSet.next();
                    printSqlTable(resultSet);
                    //JOptionPane.showMessageDialog(null, "SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"", "Connection", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3:
                    LogArea.setText("");
                    text = searchTextField.getText();
                    //LogArea.append("SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"");
                    resultSet = statement.executeQuery("SELECT * FROM `student` WHERE GENDER LIKE \"%"+text+"%\"");
                    //JOptionPane.showMessageDialog(null, "SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"", "Connection", JOptionPane.INFORMATION_MESSAGE);
                    resultSet.next();
                    printSqlTable(resultSet);
                    break;
                case 2:     // surname
                    LogArea.setText("");
                    text = searchTextField.getText();
                    //LogArea.append("SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"");
                    resultSet = statement.executeQuery("SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"");
                    //JOptionPane.showMessageDialog(null, "SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"", "Connection", JOptionPane.INFORMATION_MESSAGE);
                    resultSet.next();
                    printSqlTable(resultSet);
                    break;
                case 4:     //nationality
                    LogArea.setText("");
                    text = searchTextField.getText();
                    //LogArea.append("SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"");
                    resultSet = statement.executeQuery("SELECT * FROM `student` WHERE NATIONALITY LIKE \"%"+text+"%\"");
                    //JOptionPane.showMessageDialog(null, "SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"", "Connection", JOptionPane.INFORMATION_MESSAGE);
                    resultSet.next();
                    printSqlTable(resultSet);
                    break;
                case 5:     // programme
                    LogArea.setText("");
                    text = searchTextField.getText();
                    //LogArea.append("SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"");
                    resultSet = statement.executeQuery("SELECT * FROM `student` WHERE Programme LIKE \"%"+text+"%\"");
                    //JOptionPane.showMessageDialog(null, "SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"", "Connection", JOptionPane.INFORMATION_MESSAGE);
                    resultSet.next();
                    printSqlTable(resultSet);
                    break;
                case 6:     // course
                    LogArea.setText("");
                    text = searchTextField.getText();
                    //LogArea.append("SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"");
                    resultSet = statement.executeQuery("SELECT * FROM `student` WHERE COURSE LIKE \"%"+text+"%\"");
                    //JOptionPane.showMessageDialog(null, "SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"", "Connection", JOptionPane.INFORMATION_MESSAGE);
                    resultSet.next();
                    printSqlTable(resultSet);
                    break;
                case 7:     // pathway
                    LogArea.setText("");
                    text = searchTextField.getText();
                    //LogArea.append("SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"");
                    resultSet = statement.executeQuery("SELECT * FROM `student` WHERE PATHWAY LIKE \"%"+text+"%\"");
                    //JOptionPane.showMessageDialog(null, "SELECT * FROM `student` WHERE SURNAME LIKE \"%"+text+"%\"", "Connection", JOptionPane.INFORMATION_MESSAGE);
                    resultSet.next();
                    printSqlTable(resultSet);
                    break;
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e, "Test", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void printTable(Student st, int i) {
        LogArea.append("# " + Integer.toString(i) + "\n");
        LogArea.append("Name: " + st.getName() + "\n");
        LogArea.append("Surname: " + st.getSurname() + "\n");
        LogArea.append("ID: " + Integer.toString(st.getId()) + "\n");
        LogArea.append("Nationality: " + st.getNationality() + "\n");
        LogArea.append("Programme: " + st.getProgramme() + "\n");
        LogArea.append("Course: " + st.getCourse() + "\n");
        LogArea.append("Pathway: " + st.getPathway() + "\n\n");
    }
    public void printSqlTable(ResultSet rs) {
        LogArea.setText("");
        try {
            LogArea.append("# " + rs.getString(1) + "\n");
            LogArea.append("Name: " + rs.getString(2) + "\n");
            LogArea.append("Surname: " + rs.getString(3) +"\n");
            LogArea.append("GENDER: " + rs.getString(4) + "\n");
            LogArea.append("Nationality: " + rs.getString(5) + "\n");
            LogArea.append("Programme: " + rs.getString(6) + "\n");
            LogArea.append("Course: " + rs.getString(7) + "\n");
            LogArea.append("Pathway: " + rs.getString(8) + "\n\n");
        }
        catch (SQLException SQLe)
        {
            JOptionPane.showMessageDialog(null, SQLe, "Connection", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
