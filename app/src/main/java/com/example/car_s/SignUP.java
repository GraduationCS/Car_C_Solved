package com.example.car_s;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SignUP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_u_p);
        getSupportActionBar().hide();
    }

    public void repermen(View view) {
        Intent intent = new Intent(SignUP.this ,Repairman_Signup.class );
        startActivity(intent);
        finish();
    }

    public void user(View view) {
        Intent intent = new Intent(SignUP.this ,User_Sginup.class );
        startActivity(intent);
        finish();
    }
}