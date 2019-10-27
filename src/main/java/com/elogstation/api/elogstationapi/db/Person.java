package com.elogstation.api.elogstationapi.db;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persons")
public class Person extends AuditModel {

    @Id
    @Size(min = 3, max = 100)
    private String sub;

    @Column(name = "DISABLED", nullable = false)
    private boolean isAdmin;

    @Size(min = 1, max = 100)
    String name;

    @Size(min = 3, max = 100)
    String email;

    @Column(columnDefinition = "text")
    String picture;

    @Size(min = 3, max = 100)
    String licenseIssuingState;

    @Size(min = 3, max = 100)
    String licenseNumber;

    public String getLicenseIssuingState() {
        return licenseIssuingState;
    }

    public void setLicenseIssuingState(String licenseIssuingState) {
        this.licenseIssuingState = licenseIssuingState;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

//    @Override
//    public String toString() {
//        return "Person{" +
//                "sub='" + sub + '\'' +
//                ", isAdmin=" + isAdmin +
//                ", name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", picture='" + picture + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "Person{" +
                "sub='" + sub + '\'' +
                ", isAdmin=" + isAdmin +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", licenseIssuingState='" + licenseIssuingState + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                '}';
    }
}
