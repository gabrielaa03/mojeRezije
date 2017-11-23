package com.example.gabrielaangebrandt.mojerezije.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabrielaangebrandt.mojerezije.R;
import com.example.gabrielaangebrandt.mojerezije.forgPass.ForgottenPass;
import com.example.gabrielaangebrandt.mojerezije.listOfBills.ListOfBills;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.User;
import com.example.gabrielaangebrandt.mojerezije.registration.Registration;
import com.example.gabrielaangebrandt.mojerezije.utils.RealmUtils;
import com.example.gabrielaangebrandt.mojerezije.utils.SharedPrefs;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {
    @BindView(R.id.et_username)
    EditText username;
    @BindView(R.id.et_password)
    EditText password;
//    DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SharedPrefs.getSharedPrefs("isLogged", this).equals("in")) {
            startActivity(new Intent(this, ListOfBills.class));
        } else if (SharedPrefs.getSharedPrefs("isLogged", this).equals("out")) {
            setContentView(R.layout.activity_login);
            setTitle(R.string.logIn);
            ButterKnife.bind(this);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick(R.id.btn_login1)
    public void logIn() {
        if (Objects.equals(username.getText().toString(), "") || Objects.equals(password.getText().toString(), "")) {
            Toast.makeText(this, R.string.wrongPasswordOrUsername, Toast.LENGTH_SHORT).show();
        } else {
//            User user = FirebaseUtils.checkIfUserExists(username.getText().toString(), dataSnapshot);
            User user = RealmUtils.checkIfUserExists("username", username.getText().toString());
            if (user != null) {
                if (user.getPass().equals(password.getText().toString())) {
                    SharedPrefs.setSharedPrefs("username", username.getText().toString(), this);
                    SharedPrefs.setSharedPrefs("password", password.getText().toString(), this);
                    SharedPrefs.setSharedPrefs("isLogged", "in", this);
                    startActivity(new Intent(this, ListOfBills.class));
                } else {
                    Toast.makeText(this, R.string.wrongPasswordOrUsername, Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.wrongPasswordOrUsername, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.btn_register)
    public void register() {
        startActivity(new Intent(this, Registration.class));
    }

    @OnClick(R.id.tv_forgotten_pass)
    public void getYourPass() {
        startActivity(new Intent(this, ForgottenPass.class));
    }
}
