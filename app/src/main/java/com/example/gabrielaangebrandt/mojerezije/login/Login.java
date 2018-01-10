package com.example.gabrielaangebrandt.mojerezije.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabrielaangebrandt.mojerezije.R;
import com.example.gabrielaangebrandt.mojerezije.forgPass.ForgottenPass;
import com.example.gabrielaangebrandt.mojerezije.listOfBills.ListOfBills;
import com.example.gabrielaangebrandt.mojerezije.userManual.UserManual;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.User;
import com.example.gabrielaangebrandt.mojerezije.registration.Registration;
import com.example.gabrielaangebrandt.mojerezije.utils.Credentials;
import com.example.gabrielaangebrandt.mojerezije.utils.RealmUtils;
import com.example.gabrielaangebrandt.mojerezije.utils.SharedPrefs;
import com.example.gabrielaangebrandt.mojerezije.utils.WidgetUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Login extends AppCompatActivity {
    @BindView(R.id.et_username)
    EditText username;
    @BindView(R.id.et_password)
    EditText password;

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

    @OnClick(R.id.btn_login1)
    public void logIn() {
        if (Credentials.checkCredentials(username) || Credentials.checkCredentials(password)) {
            WidgetUtils.setToast(this, R.string.wrongPasswordOrUsername);
        } else {
            User user = RealmUtils.checkIfUserExists("username", username.getText().toString());
            if (user != null) {
                if (user.getPass().equals(password.getText().toString())) {
                    SharedPrefs.setSharedPrefs("username", username.getText().toString(), this);
                    SharedPrefs.setSharedPrefs("password", password.getText().toString(), this);
                    SharedPrefs.setSharedPrefs("isLogged", "in", this);
                    startActivity(new Intent(this, ListOfBills.class));
                } else {
                    WidgetUtils.setToast(this,  R.string.wrongPasswordOrUsername);
                }
            } else {
                WidgetUtils.setToast(this,  R.string.wrongPasswordOrUsername);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(this, UserManual.class));
        return super.onOptionsItemSelected(item);
    }
}
