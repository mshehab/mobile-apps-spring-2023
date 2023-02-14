package com.example.inclass06;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Contact implements Serializable {
    String Cid, Name, Email, Phone, PhoneType;

    public Contact() {
    }

    public Contact(JSONObject jsonObject) throws JSONException {
        this.Cid = jsonObject.getString("Cid");
        this.Name = jsonObject.getString("Name");
        this.Email = jsonObject.getString("Email");
        this.Phone = jsonObject.getString("Phone");
        this.PhoneType = jsonObject.getString("PhoneType");
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPhoneType() {
        return PhoneType;
    }

    public void setPhoneType(String phoneType) {
        PhoneType = phoneType;
    }
}
