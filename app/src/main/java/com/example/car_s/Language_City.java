package com.example.car_s;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class Language_City extends AppCompatActivity {
 Spinner splang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language__city);

        if (restorePrefData()) {

            Intent mainActivity = new Intent(getApplicationContext(), login.class );
            startActivity(mainActivity);
            finish();


        }






        getSupportActionBar().hide();

        splang = (Spinner) findViewById(R.id.spinner);

        List<String> categories = new ArrayList<String>();
        categories.add("کوردی");
        categories.add("عرەبی");
        categories.add("English");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) ;
        splang.setAdapter(dataAdapter);


    }

    public void next(View view) {
        Intent i = new Intent(Language_City.this, login.class);
        startActivity(i);
        finish();
    }
    private boolean restorePrefData() {


        SharedPreferences pref2 = getApplicationContext().getSharedPreferences("myPrefs2",MODE_PRIVATE);
        Boolean LanguageCityOpenBefore = pref2.getBoolean("isIntroOpnend2",false);
        return  LanguageCityOpenBefore;



    }


}