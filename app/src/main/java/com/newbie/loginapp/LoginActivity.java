package com.newbie.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences prefLogin;
    SharedPreferences.Editor editor;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = findViewById(R.id.btnProsesLogin);
        final EditText etUser = findViewById(R.id.etUser);
        final EditText etPass = findViewById(R.id.etPass);
        session = new Session(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUser.getText().toString();
                String password = etPass.getText().toString();

                prefLogin = getSharedPreferences("APP", MODE_PRIVATE);
                String hasil = prefLogin.getString(username + password + "data", "username atau password salah");
                editor = prefLogin.edit();
                editor.putString("hasil", hasil);
                editor.commit();

                session.createSession("username", "password");
                Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
