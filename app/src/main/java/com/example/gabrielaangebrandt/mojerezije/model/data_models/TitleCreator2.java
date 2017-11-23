package com.example.gabrielaangebrandt.mojerezije.model.data_models;

import android.content.Context;

import com.example.gabrielaangebrandt.mojerezije.utils.RealmUtils;
import com.example.gabrielaangebrandt.mojerezije.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.List;

public class TitleCreator2 {
    static TitleCreator2 titleCreator;
    List<TitleParent> titleParents;

    public TitleCreator2(Context context){
        titleParents = new ArrayList<>();
        List<String> neplaceniRacuni = new ArrayList<>();
        List<Bill> allBills = RealmUtils.getUsersBills("username", SharedPrefs.getSharedPrefs("username", context));
        for(Bill bill : allBills){
            if(bill.getStanje().equals("rb_neplacen")){
                neplaceniRacuni.add(bill.getNaziv());
            }
        }
        for(int i = 0; i<neplaceniRacuni.size(); i++){
            TitleParent title = new TitleParent(neplaceniRacuni.get(i));
            titleParents.add(title);
        }
    }
    public static TitleCreator2 get(Context context) {
        if (titleCreator == null) {
            titleCreator = new TitleCreator2(context);
            return titleCreator;
        }
        return null;
    }

    public List<TitleParent> getAll() {
        return titleParents;
    }
}
