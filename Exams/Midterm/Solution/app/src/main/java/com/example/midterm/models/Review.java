package com.example.midterm.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Review {
    String review, rating, created_at;

    public Review() {
    }

    public Review(JSONObject json) throws JSONException {
/*
 {
            "review": "This is not and good product !!",
            "rating": "4",
            "created_at": "2023-03-14 15:00:41"
        },
 */
        this.review = json.getString("review");
        this.rating = json.getString("rating");
        this.created_at = json.getString("created_at");
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
