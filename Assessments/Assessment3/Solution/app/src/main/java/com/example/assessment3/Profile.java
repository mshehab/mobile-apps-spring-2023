package com.example.assessment3;

import java.io.Serializable;

public class Profile implements Serializable {
    String name, gender;

    public Profile(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public Profile() {
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
}
