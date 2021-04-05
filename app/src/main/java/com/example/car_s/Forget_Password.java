package com.example.car_s;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Forget_Password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        getSupportActionBar().hide();

    }

    public void submit(View view) {
        Toast.makeText(Forget_Password.this,"بەسرکەوتوی ئەنجامتدا !!",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Forget_Password.this ,login.class );
        startActivity(intent);
        finish();
    }
}