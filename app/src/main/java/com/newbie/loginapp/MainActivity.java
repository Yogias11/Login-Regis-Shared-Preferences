package com.newbie.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cek();
        setContentView(R.layout.activity_main);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegis = findViewById(R.id.btnRegis);
        Session session = new Session(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Status Login adalah " + session.isLogin(), Toast.LENGTH_SHORT).show();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisActivity.class);
                startActivity(i);
            }
        });
    }

    public void cek(){
        pref = getSharedPreferences("LoginApp", 0);
        Boolean b = pref.getBoolean("isLoggin", false);
        if(b) {
            Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
            startActivity(i);
        }
    }
}
