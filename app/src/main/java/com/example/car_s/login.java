package com.example.car_s;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

    }

    public void forget(View view) {
        Intent i = new Intent(login.this,Forget_Password.class);
        startActivity(i);
    }

    public void login(View view) {

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        String userEnteredUsername = editText1.getText().toString();
        String userEnteredPassword = editText2.getText().toString();

        Query checkUsername = databaseReference.orderByChild("username").equalTo(userEnteredUsername);

        checkUsername.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {

               if (snapshot.exists()){
                   String passwordFromDB = snapshot.child(userEnteredUsername).child("pass").getValue(String.class);

                   if (passwordFromDB.equalsIgnoreCase(userEnteredPassword)){
                       Toast.makeText(login.this, "Your password is correct", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(login.this,Services.class));

                   }
                   else{
                       Toast.makeText(login.this, "Your password is incorrect", Toast.LENGTH_SHORT).show();
                   }
               }
               else{
                   Toast.makeText(login.this, "Your username not found", Toast.LENGTH_SHORT).show();
               }

           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }

    public void signup(View view) {
        Intent i = new Intent(login.this,SignUP.class);
        startActivity(i);
    }
}