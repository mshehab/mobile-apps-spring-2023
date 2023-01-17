package com.example.baccalculator;

public class Drink {
    double size, percentage;

    public Drink(double size, double percentage) {
        this.size = size;
        this.percentage = percentage;
    }

    public Drink() {
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
