package com.example.restaurant_reservation2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Users {

    public String name,email,phone,Timestamp;
    public Users () {}
    public Users(String name, String email, String phone,String Timestamp) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.Timestamp=Timestamp;
    }
}
