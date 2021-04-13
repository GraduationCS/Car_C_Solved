package com.example.car_s;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPassword extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText editText1,editText2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText1);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewPasswordBtn();
            }
        });

    }

    void setNewPasswordBtn() {

        CheckInternet checkInternet = new CheckInternet();
        if (!checkInternet.isConnected(this)){
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            return;
        }

        String pass1 = editText1.getText().toString().trim();
        String pass2 = editText2.getText().toString().trim();

        String usernameDB = getIntent().getStringExtra("usernameDB");

        if (pass1.equals(pass2)){
            String newPassword = editText1.getText().toString().trim();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
            databaseReference.child(usernameDB).child("pass").setValue(newPassword);
            Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "the password doesn't match", Toast.LENGTH_SHORT).show();
        }
    }
}