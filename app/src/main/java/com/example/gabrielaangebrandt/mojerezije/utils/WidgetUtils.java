package com.example.gabrielaangebrandt.mojerezije.utils;

import android.content.Context;
import android.media.Image;
import android.support.annotation.IntegerRes;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.gabrielaangebrandt.mojerezije.R;

public class WidgetUtils {
    private static void loadImage(ImageView iv, int path){
        Glide.with(iv.getContext()).load(path).into(iv);
    }
    public static void setImages(ImageView registration, ImageView registration2, ImageView login, ImageView bill, ImageView newbill, ImageView graph){
        loadImage(registration, R.drawable.registrationscreenshot);
        loadImage(registration2, R.drawable.registrationscreenshot2);
        loadImage(login, R.drawable.loginscreenshot);
        loadImage(bill, R.drawable.billsscreenshot);
        loadImage(newbill, R.drawable.newbillscreenshot);
        loadImage(graph, R.drawable.loginscreenshot);
    }

    public static void setSpinner(Spinner spinner, Context context){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.vrsteRacuna, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static void setToast(Context context, int sourceOfMessage){
        Toast.makeText(context, sourceOfMessage, Toast.LENGTH_SHORT).show();
    }
}
