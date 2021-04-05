package com.example.car_s;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class R_Signup extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE2 = 1;
    DatabaseReference databaseReference;
    Spinner spcity , job;
    ImageView w1;
    EditText firstName,secondName,age,phone,exper,pass,uname;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r__signup);
        getSupportActionBar().hide();

        w1 = (ImageView) findViewById(R.id.w1);
        firstName = findViewById(R.id.FirstN);
        secondName = findViewById(R.id.SecondN);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.Phone);
        exper = findViewById(R.id.experience);
        pass = findViewById(R.id.password);
        uname = findViewById(R.id.userName);
        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRepairmen();
            }
        });

////////////////////////////////////////////////////////////////////////////////////////////////////
        spcity = (Spinner) findViewById(R.id.spinner2);

        List<String> categori = new ArrayList<String>();
        categori.add("سلێمانی");
        categori.add("هەولێر");
        categori.add("کەرکوک");
        categori.add("هەڵەبجە");
        categori.add("دهۆک");
        categori.add("زاخۆ");
        categori.add("ئاکرێ");
        categori.add("قەرەداغ");
        categori.add("خانەقین");
        categori.add("کفری");
        categori.add("عەنکاوە");
        categori.add("چەمچەماڵ");
        categori.add("پیرەمەگروون");
        categori.add("کەلار");

        ArrayAdapter<String> dataAdapt = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categori);

        dataAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
        spcity.setAdapter(dataAdapt);
////////////////////////////////////////////////////////////////////////////////////////////////////
        job = (Spinner) findViewById(R.id.job);


        List<String> categoris = new ArrayList<String>();
        categoris.add("فیتەر");
        categoris.add("وایەرمەن");
        categoris.add("ڕۆنگۆڕ");
        categoris.add("پاتری");
        categoris.add("سەیارەی فلات");
        categoris.add("بەنزینی گەڕۆک");
        categoris.add("فیت پەمپ");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categoris);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
        job.setAdapter(dataAdapter);
////////////////////////////////////////////////////////////////////////////////////////////////////

    }

    public void wena1(View view) {
        Intent gallery2 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery2 , RESULT_LOAD_IMAGE2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE2 && resultCode == RESULT_OK && data != null);
        Uri selectedImage = data.getData();
        w1.setImageURI(selectedImage);
    }


    public void addRepairmen() {
        databaseReference= FirebaseDatabase.getInstance().getReference("repairmen");
        String full_name=firstName.getText().toString()+" "+secondName.getText().toString();
        String agee= age.getText().toString();
        String phone_num=phone.getText().toString();
        String ex=exper.getText().toString();
        String passw=pass.getText().toString();
        String job_type=job.getSelectedItem().toString();
        String city=spcity.getSelectedItem().toString();
        String UN=uname.getText().toString();

        Query usernameQuery = FirebaseDatabase.getInstance().getReference().child("repairmen").orderByChild("username").equalTo(UN);
        usernameQuery.addValueEventListener(new ValueEventListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getChildrenCount()>0) {
                    uname.setTextColor(Color.parseColor("#F44336"));
                    uname.setBackgroundResource(R.drawable.edittext_border_red);
                    Toast.makeText(R_Signup.this, "Try another usernamen", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (!TextUtils.isEmpty(full_name) && !TextUtils.isEmpty(agee) && !TextUtils.isEmpty(phone_num) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(job_type) && !TextUtils.isEmpty(ex) && !TextUtils.isEmpty(passw)){
                        RepairmenHelperClass repairmenHelperClass=new RepairmenHelperClass(full_name,UN,agee,phone_num,city,job_type,ex,passw);
                        databaseReference.child(UN).setValue(repairmenHelperClass);
                        Toast.makeText(R_Signup.this, "done", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Toast.makeText(R_Signup.this, "unsucessful", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}