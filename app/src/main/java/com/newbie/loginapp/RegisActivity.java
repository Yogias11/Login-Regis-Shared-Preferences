package com.newbie.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisActivity extends AppCompatActivity {

    SharedPreferences prefRegis;
    SharedPreferences.Editor editor;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        Button btnRegis = findViewById(R.id.btnProsesRegis);
        final EditText etUserr = findViewById(R.id.etUserr);
        final EditText etPasss = findViewById(R.id.etPasss);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefRegis = getSharedPreferences("APP", MODE_PRIVATE);
                String user = etUserr.getText().toString();
                String pass = etPasss.getText().toString();

                editor = prefRegis.edit();
                editor.putString(user + pass + "data", user);
                editor.commit();

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
