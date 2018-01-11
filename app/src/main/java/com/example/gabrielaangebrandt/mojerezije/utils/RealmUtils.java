package com.example.gabrielaangebrandt.mojerezije.utils;

import android.widget.EditText;

import com.example.gabrielaangebrandt.mojerezije.model.App;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.Bill;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.User;
//import com.google.firebase.database.DataSnapshot;

import java.util.List;

import io.realm.Realm;

public class RealmUtils {
    private static final Realm realm = App.getRealmInstance();

    public static List<User> getUsers() {
        Realm realm = App.getRealmInstance();
        return realm.where(User.class).findAll();
    }

    public static User checkIfUserExists(String databaseElement, String value) {
        User user = realm.where(User.class).equalTo(databaseElement, value).findFirst();
        if (user != null) {
            return user;
        }
        return null;
    }

    public static String getPass(User user) {
        return user.getPass();
    }

    public static String getEmail(User user) {
        return user.getEmail();
    }

    public static String getName(User user) {
        return user.getName();
    }

    public static User createUser(EditText username, EditText imeIPrezime, EditText adresa, EditText email, EditText pass, EditText placa) {
        return new User(username.getText().toString(), imeIPrezime.getText().toString(), adresa.getText().toString(),
                email.getText().toString(), pass.getText().toString(), placa.getText().toString());
    }

    public static void saveUser(User user) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(user);
        realm.commitTransaction();
    }

    public static void saveUsersBills(Bill bill, String username) {
        realm.beginTransaction();
        List<Bill> listOfBills = realm.copyFromRealm(realm.where(Bill.class).equalTo("user", username).findAll());
        if (listOfBills != null) {
            listOfBills.add(bill);
            realm.copyToRealmOrUpdate(listOfBills);
        }
        realm.commitTransaction();
    }

    public static List<Bill> getUsersBills(String value) {
        realm.beginTransaction();
        List<Bill> bills = realm.copyFromRealm(realm.where(Bill.class).equalTo("user", value).findAll());
        realm.commitTransaction();
        return bills;
    }

    public static List<Bill> getUsersPaidBills(String value) {
        realm.beginTransaction();
        List<Bill> bills = realm.copyFromRealm(realm.where(Bill.class).equalTo("user", value).equalTo("stanje", "rb_placen").findAll());
        realm.commitTransaction();
        return bills;
    }
    public static List<Bill> getUsersUnPaidBills(String value) {
        realm.beginTransaction();
        List<Bill> bills = realm.copyFromRealm(realm.where(Bill.class).equalTo("user", value).equalTo("stanje", "rb_neplacen").findAll());
        realm.commitTransaction();
        return bills;
    }
}
