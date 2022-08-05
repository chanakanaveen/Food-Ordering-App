package com.example.brotherskitchen.models;

public class UserModel {
    String name;
    String email;
    String password;
    String profileImg;
    String address;
    String number;

    public UserModel() {
    }

    public UserModel(String number,String address, String email,String name, String password, String profileImg) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.number = number;
        this.profileImg= profileImg;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
