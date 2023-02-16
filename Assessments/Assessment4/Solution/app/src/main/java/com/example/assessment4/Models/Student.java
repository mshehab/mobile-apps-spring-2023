package com.example.assessment4.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

    String name,gender, department, state, stateAbv;
    Integer age;
    ArrayList<CourseHistory> courses = new ArrayList<>();

    public Student() {
    }

    public Student(String name, Integer age, String gender, String department, String state, String stateAbv) {
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.state = state;
        this.age = age;
        this.stateAbv = stateAbv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ArrayList<CourseHistory> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseHistory> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return name;
    }
}
