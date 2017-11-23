package com.example.gabrielaangebrandt.mojerezije.forgPass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gabrielaangebrandt.mojerezije.R;
import com.example.gabrielaangebrandt.mojerezije.login.Login;
import com.example.gabrielaangebrandt.mojerezije.model.data_models.User;
//import com.example.gabrielaangebrandt.mojerezije.utils.FirebaseUtils;
import com.example.gabrielaangebrandt.mojerezije.utils.RealmUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgottenPass extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText username;
    @BindView(R.id.et_name)
    EditText name;
    @BindView(R.id.et_email)
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotten_pass_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_getMyPass)
    public void getPass() {
        if (username.getText().toString().equals("") || name.getText().toString().equals("") || email.getText().toString().equals("")) {
            Toast.makeText(this, R.string.elementsArentEntered, Toast.LENGTH_SHORT).show();
        } else {
            User user = RealmUtils.checkIfUserExists("username", username.getText().toString());
            assert user != null;
            if (user.getName().equals(name.getText().toString()) && user.getEmail().equals(email.getText().toString())) {
                //pošalji mail
                Toast.makeText(this, user.getPass(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, Login.class));
            } else {
                Toast.makeText(this, R.string.kriviPodaci, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
