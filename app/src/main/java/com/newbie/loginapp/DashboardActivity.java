package com.newbie.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity {

    SharedPreferences prefDone;
    SharedPreferences.Editor editor;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnClear = findViewById(R.id.btnClear);
        TextView tvTampil = findViewById(R.id.tvTampil);

        prefDone = getSharedPreferences("APP", MODE_PRIVATE);
        final String hasil = prefDone.getString("hasil", "data telah dihapus");

        session = new Session(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Status Login adalah " + session.isLogin(), Toast.LENGTH_SHORT).show();

        session.cekLogin();
        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(Session.key_name);
        String pass = user.get(Session.key_pass);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefDone = getSharedPreferences("LoginApp", MODE_PRIVATE);
                editor = prefDone.edit();
                editor.putBoolean("isLoggin", false);
                editor.commit();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefDone = getSharedPreferences("APP", MODE_PRIVATE);
                editor = prefDone.edit();
                editor.remove("hasil");
                editor.commit();
                session.logout();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });
        tvTampil.setText(hasil);
    }
}
