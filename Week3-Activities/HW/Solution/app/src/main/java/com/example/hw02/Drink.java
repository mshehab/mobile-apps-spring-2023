package com.example.hw02;

import java.io.Serializable;
import java.util.Date;

public class Drink implements Serializable {
    double alcohol, size;
    Date addedOn;

    public Drink() {
        this.addedOn = new Date();
    }

    public Drink(double alcohol, double size) {
        this.alcohol = alcohol;
        this.size = size;
        this.addedOn = new Date();
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
