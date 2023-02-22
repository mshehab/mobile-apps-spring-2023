package com.example.assessment5.models;

import org.json.JSONObject;

import java.io.Serializable;

public class Forum implements Serializable {
    String thread_id, title, created_at;
    String createdByFname, createdByLname, createdByUserId;

    public Forum() {
    }

    public Forum(JSONObject jsonObject) {
        //TODO: parse the json object

        /*
     {
            "created_by": {
                "fname": "Bob",
                "lname": "Smith",
                "user_id": 1
            },
            "thread_id": 2,
            "title": "This is a test thread",
            "created_at": "2023-02-22 18:30:42"
        }
     */
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCreatedByFname() {
        return createdByFname;
    }

    public void setCreatedByFname(String createdByFname) {
        this.createdByFname = createdByFname;
    }

    public String getCreatedByLname() {
        return createdByLname;
    }

    public void setCreatedByLname(String createdByLname) {
        this.createdByLname = createdByLname;
    }

    public String getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(String createdByUserId) {
        this.createdByUserId = createdByUserId;
    }
}
