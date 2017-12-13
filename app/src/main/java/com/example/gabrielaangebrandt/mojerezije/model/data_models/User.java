package com.example.gabrielaangebrandt.mojerezije.model.data_models;

import com.example.gabrielaangebrandt.mojerezije.App;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class User extends RealmObject {
    @PrimaryKey
    private String username;
    private String name, addr, email, pass;
    //private RealmList<String> listOfBillsCategory;
//    private RealmList<Bill> listOfBills;
    private String datumPlace;

    public User(String username, String name, String addr, String email, String pass/*, RealmList<Bill> listOfBills*/, String datumPlace/*, RealmList<String> listOfBillsCategory*/) {
        this.username = username;
        this.name = name;
        this.addr = addr;
        this.email = email;
        this.pass = pass;
        this.datumPlace = datumPlace;
        //this.listOfBillsCategory = listOfBillsCategory;
    }

    public User() {
    }

//    public RealmList<String> getListOfBillsCategory() {
//        return listOfBillsCategory;
//    }

//    public void setListOfBillsCategory(RealmList<String> listOfBillsCategory) {
//        this.listOfBillsCategory = listOfBillsCategory;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

//    public RealmList<Bill> getListOfBills() {
//        return listOfBills;
//    }
//
//    public void setListOfBills(RealmList<Bill> listOfBills) {
//        this.listOfBills = listOfBills;
//    }

    public String getDatumPlace() {
        return datumPlace;
    }

    public void setDatumPlace(String datumPlace) {
        this.datumPlace = datumPlace;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                //", listOfBillsCategory=" + listOfBillsCategory +
                ", datumPlace=" + datumPlace +
                '}';
    }
}
