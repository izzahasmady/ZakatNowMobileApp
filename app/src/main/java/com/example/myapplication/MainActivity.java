package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//ss
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Toolbar mytoolbar;
    Button btnAbout, btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalc = findViewById(R.id.btnCalc);
        btnAbout = findViewById(R.id.btnAbout);


        btnAbout.setOnClickListener(this);
        btnCalc.setOnClickListener(this);


        mytoolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mytoolbar);
        getSupportActionBar().setTitle(R.string.app_name);

    }

    public void onClick(View view){

        if (view == btnAbout){
            Intent intent = new Intent(this, ProfileActivity.class);

            startActivity(intent);
        }
        else if (view == btnCalc) {
            Intent intent = new Intent(this, CalculatorActivity.class);

            startActivity(intent);
        }
    }
}