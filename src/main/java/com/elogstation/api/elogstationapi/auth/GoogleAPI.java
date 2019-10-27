package com.elogstation.api.elogstationapi.auth;

public class GoogleAPI {
    String error_description;
    String sub;
    String email;
    String picture;
    String name;

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    @Override
    public String toString() {
        return "GoogleAPI{" +
                "error_description='" + error_description + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", name='" + name + '\'' +
                ", sub='" + sub + '\'' +
                '}';
    }
}
