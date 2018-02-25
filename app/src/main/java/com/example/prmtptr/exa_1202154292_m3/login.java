package com.example.prmtptr.exa_1202154292_m3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //user dan password statik, memasukan nilai username dan password

    public void login(View view) {
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        String message;
        if (username.getText().toString().equals("EAD") && password.getText().toString().equals("MOBILE")) {
            message = "Berhasil login";
            startActivity(new Intent(this, MainActivity.class));
        } else {
            message = "Gagal login";
            finish();
            startActivity(getIntent());
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
