package com.example.vjezba6;

public class Subject {
    Integer year;
    String name;
    String teacher;

    public Subject() {}

    public Subject(Integer year, String name, String teacher) {
        this.year = year;
        this.name = name;
        this.teacher = teacher;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
