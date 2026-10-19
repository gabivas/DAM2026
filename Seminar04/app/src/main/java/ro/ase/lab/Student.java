package ro.ase.lab;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Student implements Serializable {

    private final String name;
    private final String faculty;
    private final int year;

    public Student(String name, String faculty, int year) {
        this.name = name;
        this.faculty = faculty;
        this.year = year;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " · " + faculty + ", anul " + year;
    }
}
