package com.example.gabrielaangebrandt.mojerezije.model.data_models;

import android.content.Context;

import com.example.gabrielaangebrandt.mojerezije.utils.RealmUtils;
import com.example.gabrielaangebrandt.mojerezije.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

public class
TitleCreator {
    static TitleCreator titleCreator;
    List<TitleParent> titleParents;

    public TitleCreator(Context context) {
        titleParents = new ArrayList<>();
        List<String> placeniRacuni = new ArrayList<>();
        List<Bill> allBills = RealmUtils.getUsersBills("username", SharedPrefs.getSharedPrefs("username", context));
        for (Bill bill : allBills) {
            if (bill.getStanje().equals("rb_neplacen")) {
                placeniRacuni.add(bill.getNaziv());
            }
        }
        for (int i = 0; i < placeniRacuni.size(); i++) {
            TitleParent title = new TitleParent(placeniRacuni.get(i));
            titleParents.add(title);
        }
    }

    public static TitleCreator get(Context context) {
        if (titleCreator == null) {
            titleCreator = new TitleCreator(context);
            return titleCreator;
        }
        return null;
    }

    public List<TitleParent> getAll() {
        if (titleParents != null) {
            return titleParents;
        } else
            return new ArrayList<>();
    }
}
