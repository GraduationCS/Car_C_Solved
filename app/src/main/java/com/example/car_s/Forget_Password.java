package com.example.car_s;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Forget_Password extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    EditText editText;
    Button button;
    String get_phone;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget__password);
        getSupportActionBar().hide();

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setError(null);
                get_phone = editText.getText().toString().trim();

                if (get_phone.isEmpty()){
                    Toast.makeText(Forget_Password.this, "please enter your phone num", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                Query checkPhone = FirebaseDatabase.getInstance().getReference("users").orderByChild("phone").equalTo(get_phone);
                checkPhone.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            Toast.makeText(Forget_Password.this, "Done", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.VISIBLE);
                            button.setVisibility(View.VISIBLE);

                            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                    "+964"+get_phone.trim(),
                                    60,
                                    TimeUnit.SECONDS,
                                    Forget_Password.this,
                                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                            progressBar.setVisibility(View.GONE);
                                            button.setVisibility(View.VISIBLE);
                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            progressBar.setVisibility(View.GONE);
                                            button.setVisibility(View.VISIBLE);
                                            Toast.makeText(Forget_Password.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                            progressBar.setVisibility(View.GONE);
                                            button.setVisibility(View.VISIBLE);
                                            Intent intent = new Intent(Forget_Password.this,Verify_OTP.class);
                                            intent.putExtra("phone",get_phone);
                                            intent.putExtra("verificationId",verificationId);
                                            startActivity(intent);
                                        }
                                    }
                            );

                        }
                        else{
                            Toast.makeText(Forget_Password.this, "Does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
            }
        });

    }

    public static String removeZero(String str)
    {
        // Count leading zeros
        int i = 0;
        while (i < str.length() && str.charAt(i) == '0')
            i++;

        // Convert str into StringBuffer as Strings
        // are immutable.
        StringBuffer sb = new StringBuffer(str);

        // The  StringBuffer replace function removes
        // i characters from given index (0 here)
        sb.replace(0, i, "");

        return sb.toString();  // return in String
    }
}