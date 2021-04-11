package com.example.car_s;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class U_Signup extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    DatabaseReference databaseReference;
    Spinner spcity;
    ImageView w1;
    Button button;
    Uri selectedImage;
    EditText firstName,secondName,age,phone,pass,uname;
    private StorageReference storageReference;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u__signup);

        w1 = findViewById(R.id.w1);
        firstName = findViewById(R.id.fname);
        secondName = findViewById(R.id.lname);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        uname = findViewById(R.id.userName);
        pass = findViewById(R.id.password);
        progressBar = new ProgressBar(U_Signup.this);

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

        dataAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spcity.setAdapter(dataAdapt);
////////////////////////////////////////////////////////////////////////////////////////////////////


        button= (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });


    }
    ///////////////////////////////////////////////////////////// Browse Image
    public void wena1(View view) {

        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(gallery, RESULT_LOAD_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) ;
        selectedImage = data.getData();

        try {

            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),selectedImage);
            w1.setImageURI(selectedImage);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    ////////////////////////////////////////////////////////////

    void addUser(){

        ////////////////////////////////////////////////////////////////////////////// Upload Image

        /*if (w1 != null){
            StorageReference fileRefrence = storageReference.child("users/"+ UUID.randomUUID().toString());
            fileRefrence.putFile(selectedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBar.setProgress(0);

                    Toast.makeText(U_Signup.this, "Upload Successfully", Toast.LENGTH_SHORT).show();



                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setProgress(0);
                            Toast.makeText(U_Signup.this,"Failed "+e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);
                        }
                    });
        }
        else {
            Toast.makeText(this, "No file Selected", Toast.LENGTH_SHORT).show();
        }
*/
        /////////////////////////////////////////////////////////////////////////////
        databaseReference= FirebaseDatabase.getInstance().getReference("users");

        String full_name=firstName.getText().toString()+" "+secondName.getText().toString();
        String agee= age.getText().toString();
        String phone_num=phone.getText().toString();
        String passw=pass.getText().toString();
        String city=spcity.getSelectedItem().toString();
        String UN=uname.getText().toString();

        Query usernameQuery = FirebaseDatabase.getInstance().getReference().child("users").orderByChild("username").equalTo(UN);
        usernameQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.getChildrenCount()>0){
                    uname.setTextColor(Color.parseColor("#F44336"));
                    uname.setBackgroundResource(R.drawable.edittext_border_red);
                    Toast.makeText(U_Signup.this, "Try another username", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!TextUtils.isEmpty(full_name) && !TextUtils.isEmpty(agee) && !TextUtils.isEmpty(phone_num) && !TextUtils.isEmpty(city) && !TextUtils.isEmpty(passw) && !TextUtils.isEmpty(UN)){
                        UserHelperClass userHelperClass=new UserHelperClass(full_name,UN,agee,phone_num,city,passw);
                        databaseReference.child(UN).setValue(userHelperClass);
                        Toast.makeText(U_Signup.this, "done", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(U_Signup.this ,Services.class ));
                    }
                    else{
                        Toast.makeText(U_Signup.this, "fill all the fields", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}