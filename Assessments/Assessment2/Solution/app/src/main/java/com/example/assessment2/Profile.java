package com.example.assessment2;

import java.io.Serializable;

public class Profile implements Serializable {
    String gender;
    Double weight;

    public Profile(String gender, Double weight) {
        this.gender = gender;
        this.weight = weight;
    }

    public Profile() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
