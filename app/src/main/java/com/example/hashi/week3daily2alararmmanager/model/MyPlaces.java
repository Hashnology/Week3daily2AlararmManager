package com.example.hashi.week3daily2alararmmanager.model;

public class MyPlaces {
    private String name;
    private  String email;
    private String phone;
    private byte[] imageByteArray;

    public MyPlaces(String name, String email, String phone, byte[] imageByteArray) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.imageByteArray = imageByteArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImageByteArray() {
        return imageByteArray;
    }

    public void setImageByteArray(byte[] imageByteArray) {
        this.imageByteArray = imageByteArray;
    }
}