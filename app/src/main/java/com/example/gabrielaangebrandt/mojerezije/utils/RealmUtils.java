package com.example.gabrielaangebrandt.mojerezije.utils;

import com.example.gabrielaangebrandt.mojerezije.App;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.Bill;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.User;
//import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

public class RealmUtils {

    public static List<User> getUsers() {
        Realm realm = App.getRealmInstance();
        return realm.where(User.class).findAll();
    }

    public static User checkIfUserExists(String databaseElement, String value) {
        Realm realm = App.getRealmInstance();
        User user = realm.where(User.class).equalTo(databaseElement, value).findFirst();
        if (user != null) {
            return user;
        }
        return null;
    }

    public static String getPass(String databaseElement, String value) {
        Realm realm = App.getRealmInstance();
        User user = realm.where(User.class).equalTo(databaseElement, value).findFirst();
        if (user != null) {
            return user.getPass();
        }
        return null;
    }

    public static void saveUser(User user) {
        Realm realm = App.getRealmInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
    }

    public static void saveUsersBills(RealmList<Bill> listOfBills, String username) {
        Realm realm = App.getRealmInstance();
        realm.beginTransaction();
        User user = realm.where(User.class).equalTo("username", username).findFirst();
        if (user != null) {
            user.setListOfBills(listOfBills);
        }

        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
    }

    public static List<Bill> getUsersBills(String databaseElement, String value) {
        Realm realm = App.getRealmInstance();
        realm.beginTransaction();
        User user = realm.where(User.class).equalTo(databaseElement, value).findFirst();
        List<Bill> list = new ArrayList<>();
        for (Bill bill : user.getListOfBills()) {
            list.add(bill);
        }
        realm.commitTransaction();
        return list;
    }

}
