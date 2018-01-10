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
import com.example.gabrielaangebrandt.mojerezije.utils.Credentials;
import com.example.gabrielaangebrandt.mojerezije.utils.RealmUtils;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.ValueEventListener;

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
    @BindView(R.id.datumPlace)
    EditText placa;
    public static final String EMAIL_REGEX = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_register_user)
    public void saveIntoDB() {
        if (Credentials.checkCredentials(imeIPrezime) || Credentials.checkCredentials(adresa) || Credentials.checkCredentials(email)
                || Credentials.checkCredentials(username) || Credentials.checkCredentials(pass) || Credentials.checkCredentials(pass2)
                || Credentials.checkCredentials(placa)) {
            Toast.makeText(this, R.string.elementsArentEntered, Toast.LENGTH_LONG).show();
        } else {
            if (RealmUtils.checkIfUserExists("username", username.getText().toString()) != null) {
                Toast.makeText(this, R.string.userAlreadyExists, Toast.LENGTH_SHORT).show();
            } else {
                if (!pass.getText().toString().equals(pass2.getText().toString())) {
                    Toast.makeText(this, R.string.error_passwordsDontMatch, Toast.LENGTH_SHORT).show();
                } else {
//                    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                    Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(email.getText().toString());
                    if (matcher.matches()) {
                        RealmUtils.saveUser(RealmUtils.createUser(username, imeIPrezime, adresa, email, pass, pass2));
                        startActivity(new Intent(this, Login.class));
                        Toast.makeText(this, R.string.successfullRegistration, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, R.string.emailIsNotValid, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}
