package com.example.assessment4.Models;

import java.io.Serializable;

public class CourseHistory implements Serializable {
    String name, number, letterGrade;
    Double hours;

    public CourseHistory() {
    }

    public CourseHistory(String name, String number, String letterGrade, Double hours) {
        this.name = name;
        this.number = number;
        this.letterGrade = letterGrade;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    @Override
    public String toString() {
        return "CourseHistory{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", letterGrade='" + letterGrade + '\'' +
                ", hours=" + hours +
                '}';
    }
}
