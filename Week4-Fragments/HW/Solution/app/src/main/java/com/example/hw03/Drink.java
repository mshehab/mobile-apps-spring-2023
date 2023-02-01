package com.example.hw03;

import java.util.Date;

public class Drink {
    double alcohol, size;
    Date addedOn;

    public Drink(double alcohol, double size) {
        this.alcohol = alcohol;
        this.size = size;
        addedOn = new Date();
    }

    public Drink() {
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
