package com.example.hw04;

public class Profile {
    double weight;
    String gender;

    public Profile(double weight, String gender) {
        this.weight = weight;
        this.gender = gender;
    }

    public Profile() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
