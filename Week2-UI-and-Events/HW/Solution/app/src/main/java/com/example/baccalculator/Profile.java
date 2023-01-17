package com.example.baccalculator;

public class Profile {
    String gender;
    double weight;

    public Profile() {
    }

    public Profile(String gender, double weight) {
        this.gender = gender;
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
