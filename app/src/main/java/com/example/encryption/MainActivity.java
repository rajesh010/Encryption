package com.example.encryption;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Btn_encrypt, Btn_decrypt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_encrypt = findViewById(R.id.Btn_encrypt);
        Btn_decrypt = findViewById(R.id.Btn_decrypt);

        Btn_encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Encrypt.class);
                startActivity(i);
            }
        });


        Btn_decrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Decrypt.class);
                startActivity(i);
            }
        });


    } //end of on create function
}