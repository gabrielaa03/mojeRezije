package com.example.gabrielaangebrandt.mojerezije.model;

import android.app.Application;

//import com.google.firebase.database.FirebaseDatabase;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration defaultConfig = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(defaultConfig);
    }

    public static Realm getRealmInstance() {
        return Realm.getDefaultInstance();
    }
//    public static FirebaseDatabase getFirebaseDb() {
//        return FirebaseDatabase.getInstance();
//    }

}
