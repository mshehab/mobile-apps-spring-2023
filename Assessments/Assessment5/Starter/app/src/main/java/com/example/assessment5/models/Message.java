package com.example.assessment5.models;

import org.json.JSONObject;

public class Message {
    String message_id, message, created_at;
    String createdByFname, createdByLname, createdByUserId;

    public Message() {
    }

    public Message(JSONObject jsonObject) {
        //TODO: parse the json object
         /*
    {
            "created_by": {
                "fname": "Bob",
                "lname": "Smith",
                "user_id": 1
            },
            "message_id": 4,
            "message": "testing message",
            "created_at": "2023-02-22 19:36:32"
        },
     */
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
