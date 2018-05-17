package uk.sheffield.pro.college.registry;

import java.util.Random;

public class Student {
    private String firstName;   // student name
    private String familyName;  // student surname
    private int id;             // student id
    private String programme;   // either FIY or PMP
    private String course;      // either SE or BSSH
    private String pathway;     // student's pathway
    private double gpa;         // student's GPA
    private String nationality; // student's nationality
    private String gender;      // student's gender

    // constructor
    public Student(String name, String surname, String gender, String nationality, String programme, String course, String pathway) {
        this.firstName = name;
        this.familyName = surname;
        this.gender = gender;
        this.nationality = nationality;
        this.programme = programme;
        this.course = course;
        this.pathway = pathway;
        Random id = new Random();
        this.setId(id.nextInt((999999-100000)+1)+100000);  //setting a random number from 100000 to 999999 to make it more unique
    }

    /////////////////////////////////////////////////
    //                   GETTERS                   //
    /////////////////////////////////////////////////

    // get full student's name (both first name and family name)
    public String getFullName() {
        return (this.firstName + " " + this.familyName);
    }

    // get student's name
    public String getName() {
        return this.firstName;
    }

    // get student's surname
    public String getSurname() {
        return this.familyName;
    }

    // get student's programme
    public String getProgramme() {
        return this.programme;
    }

    // get student's id
    public int getId() {
        return this.id;
    }

    // get student's GPA
    public double getGpa() {
        return gpa;
    }

    // get student's gender
    public String getGender() {
        return gender;
    }

    // get student's nationality
    public String getNationality() {
        return nationality;
    }

    // get student's course
    public String getCourse() {
        return course;
    }

    // get student's pathway
    public String getPathway() {
        return pathway;
    }

    /////////////////////////////////////////////////
    //                   SETTERS                   //
    /////////////////////////////////////////////////

    // set student's first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // set student's family name
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    // set student's full name (both first and family names); just in case
    public void setFullName(String name, String surname) {
        this.firstName = name;
        this.familyName = surname;
    }

    // set student's id
    public void setId(int id) {
        this.id = id;
    }

    // set student's GPA
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    // set student's programme (either FIY or PMP)
    public void setProgramme(String programme) {
        this.programme = programme;
    }

    // set student's gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // set student's nationality
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    // set student's pathway
    public void setPathway(String pathway) {
        this.pathway = pathway;
    }

    // set student's course
    public void setCourse(String course) {
        this.course = course;
    }
}
