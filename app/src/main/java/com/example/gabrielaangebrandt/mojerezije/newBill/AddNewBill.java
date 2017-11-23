package com.example.gabrielaangebrandt.mojerezije.newBill;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.gabrielaangebrandt.mojerezije.App;
import com.example.gabrielaangebrandt.mojerezije.R;
import com.example.gabrielaangebrandt.mojerezije.listOfBills.ListOfBills;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.Bill;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.User;
//import com.example.gabrielaangebrandt.mojerezije.utils.FirebaseUtils;
import com.example.gabrielaangebrandt.mojerezije.utils.RealmUtils;
import com.example.gabrielaangebrandt.mojerezije.utils.SharedPrefs;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//1import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

import static java.lang.Integer.parseInt;

public class AddNewBill extends AppCompatActivity {
    @BindView(R.id.et_naziv)
    EditText vrsta;
    @BindView(R.id.et_tvrtka)
    EditText tvrtka;
    @BindView(R.id.et_datumZaprimljenosti)
    EditText datumDospijeca;
    @BindView(R.id.et_mjesec)
    EditText mjesec;
    @BindView(R.id.et_iznos)
    EditText iznos;
    @BindView(R.id.rb_placen)
    RadioButton rb_placen;
    @BindView(R.id.rb_neplacen)
    RadioButton rb_neplacen;
    //    DataSnapshot dataSnapshot;

    List<Bill> listOfBills = new ArrayList<>();
    Bill bill;
    private int CAMERA_PIC_REQUEST = 9000;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_bill);
        ButterKnife.bind(this);
//        FirebaseUtils.getFirebaseReference().addValueEventListener(this);
        rb_neplacen.setChecked(true);

        realm = App.getRealmInstance();
    }

    @OnClick(R.id.btn_potvrdi)
    public void addBill() {
//        realm.beginTransaction();
        if (rb_placen.isChecked()) {
            if (vrsta.getText().toString().equals("") || tvrtka.getText().toString().equals("") || datumDospijeca.getText().toString().equals("") ||
                    mjesec.getText().toString().equals("") || iznos.getText().toString().equals("")) {
                Toast.makeText(this, R.string.elementsArentEntered, Toast.LENGTH_SHORT).show();
            } else {
//                User user = FirebaseUtils.checkIfUserExists(username, dataSnapshot);
//                User user = RealmUtils.checkIfUserExists("username", SharedPrefs.getSharedPrefs("username", this));
                bill = new Bill(parseInt(mjesec.getText().toString()), datumDospijeca.getText().toString(), vrsta.getText().toString(), tvrtka.getText().toString(), iznos.getText().toString(), "rb_placen");
                listOfBills = RealmUtils.getUsersBills("username", SharedPrefs.getSharedPrefs("username", this));
            }
        } else {
            if (vrsta.getText().toString().equals("") || tvrtka.getText().toString().equals("") || datumDospijeca.getText().toString().equals("") ||
                    mjesec.getText().toString().equals("") || iznos.getText().toString().equals("")) {
                Toast.makeText(this, R.string.elementsArentEntered, Toast.LENGTH_SHORT).show();
            } else {
//                User user = RealmUtils.checkIfUserExists("username", SharedPrefs.getSharedPrefs("username", this));
//               User user = FirebaseUtils.checkIfUserExists(username, dataSnapshot);

                bill = new Bill(parseInt(mjesec.getText().toString()), datumDospijeca.getText().toString(), vrsta.getText().toString(), tvrtka.getText().toString(), iznos.getText().toString(), "rb_placen");
                listOfBills = RealmUtils.getUsersBills("username", SharedPrefs.getSharedPrefs("username", this));

            }
        }
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle(R.string.potvrdiZaSpremanje)
                .setMessage(R.string.spremanjeRacuna)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        RealmList<Bill> realmlist = new RealmList<>();
                        realmlist.addAll(listOfBills);
                        realmlist.add(bill);
                        RealmUtils.saveUsersBills(realmlist, SharedPrefs.getSharedPrefs("username", getApplicationContext()));
                        Intent intent = new Intent(getApplicationContext(), ListOfBills.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @OnClick(R.id.ib_camera_btn)
    public void openCamera() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
//            ImageView imageview = (ImageView) findViewById(R.id.ImageView01); //sets imageview as the bitmap
//            imageview.setImageBitmap(image);
        }
    }
//
//    @Override
//    public void onDataChange(DataSnapshot dataSnapshot) {
//        this.dataSnapshot = dataSnapshot;
//    }
//
//    @Override
//    public void onCancelled(DatabaseError databaseError) {
//
//    }
}