package uk.sheffield.pro.college.registry;

import java.util.ArrayList;

public class College {
    private String collegeName;             // college name; the college should have a name, shouldn't it?
    public ArrayList<Student> students;    // a "container" for college students

    public College() {
        this.students = new ArrayList<>();
    }

    // add a student to the college
    public void addStudent(Student std) {
        this.students.add(std);
    }
}
