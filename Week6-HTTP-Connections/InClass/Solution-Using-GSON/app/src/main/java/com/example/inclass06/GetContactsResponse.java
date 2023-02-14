package com.example.inclass06;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetContactsResponse {
    String status;
    ArrayList<Contact> contacts;

    public GetContactsResponse() {
    }

    static class Contact implements Serializable {
        @SerializedName("Cid")
        String cid;
        
        @SerializedName("Name")
        String name;


        String Email, Phone, PhoneType;
        public Contact() {
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        @Override
        public String toString() {
            return "Contact{" +
                    "Cid='" + cid + '\'' +
                    ", Name='" + name + '\'' +
                    ", Email='" + Email + '\'' +
                    ", Phone='" + Phone + '\'' +
                    ", PhoneType='" + PhoneType + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GetContactsResponse{" +
                "status='" + status + '\'' +
                ", contacts=" + contacts +
                '}';
    }
}
