package com.example.gabrielaangebrandt.mojerezije.registration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabrielaangebrandt.mojerezije.R;
import com.example.gabrielaangebrandt.mojerezije.login.Login;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.Bill;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.User;
//import com.example.gabrielaangebrandt.mojerezije.utils.FirebaseUtils;
import com.example.gabrielaangebrandt.mojerezije.utils.RealmUtils;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.RealmList;

public class Registration extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText imeIPrezime;
    @BindView(R.id.et_addr)
    EditText adresa;
    @BindView(R.id.et_email)
    EditText email;
    @BindView(R.id.et_username)
    EditText username;
    @BindView(R.id.et_password1)
    EditText pass;
    @BindView(R.id.et_password2)
    EditText pass2;
    @BindView(R.id.racun1)
    EditText rac1;
    @BindView(R.id.racun2)
    EditText rac2;
    @BindView(R.id.racun3)
    EditText rac3;
    @BindView(R.id.racun4)
    EditText rac4;
    @BindView(R.id.racun5)
    EditText rac5;
    @BindView(R.id.datumPlace)
    EditText placa;
//    DataSnapshot dataSnapshot;
    RealmList<Bill> listOfBills = new RealmList<>();
    public static final String EMAIL_REGEX = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
//        FirebaseUtils.getFirebaseReference().addValueEventListener(this);
    }

    @OnClick(R.id.btn_register_user)
    public void saveIntoDB() {
        if (imeIPrezime.getText().toString().equals("") || adresa.getText().toString().equals("") || email.getText().toString().equals("")
                || username.getText().toString().equals("") || pass.getText().toString().equals("") || pass2.getText().toString().equals("")
                || placa.getText().toString().equals("")) {
            Toast.makeText(this, R.string.elementsArentEntered, Toast.LENGTH_LONG).show();
        } else {
//            if (FirebaseUtils.checkIfUserExists(username.getText().toString(), dataSnapshot) != null) {
            if (RealmUtils.checkIfUserExists("username", username.getText().toString()) != null) {
                Toast.makeText(this, R.string.userAlreadyExists, Toast.LENGTH_SHORT).show();
            } else {
                if (!pass.getText().toString().equals(pass2.getText().toString())) {
                    Toast.makeText(this, R.string.error_passwordsDontMatch, Toast.LENGTH_SHORT).show();
                } else {
                    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(email.getText().toString());
                    if (matcher.matches()) {

                        List<String> categoryList = new ArrayList<>();
                        if (!rac1.getText().toString().equals("")) {
                            categoryList.add(rac1.getText().toString());
                        }
                        if (!rac2.getText().toString().equals("")) {
                            categoryList.add(rac2.getText().toString());
                        }
                        if (!rac3.getText().toString().equals("")) {
                            categoryList.add(rac3.getText().toString());
                        }
                        if (!rac4.getText().toString().equals("")) {
                            categoryList.add(rac4.getText().toString());
                        }
                        if (!rac5.getText().toString().equals("")) {
                            categoryList.add(rac5.getText().toString());
                        }
//                    FirebaseUtils.writeNewUser(username.getText().toString(), imeIPrezime.getText().toString(), adresa.getText().toString()
//                            , email.getText().toString(), pass.getText().toString(), listOfBills, placa.getText().toString(), categoryList);
                        RealmList<String> categories = new RealmList<>();
                        categories.addAll(categoryList);

                        User user = new User(username.getText().toString(), imeIPrezime.getText().toString(), adresa.getText().toString()
                                , email.getText().toString(), pass.getText().toString(), placa.getText().toString());
                        RealmUtils.saveUser(user);
                        startActivity(new Intent(this, Login.class));
                        Toast.makeText(this, R.string.successfullRegistration, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(this, R.string.emailIsNotValid, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

//    @Override
//    public void onDataChange(DataSnapshot dataSnapshot) {
//        this.dataSnapshot = dataSnapshot;
//        int i = 0;
//    }
//
//    @Override
//    public void onCancelled(DatabaseError databaseError) {
//
//    }
}
