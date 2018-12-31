package com.auribises.gw2018d;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener{

    //1. Declare Reference Variables to Views
    EditText eTxtName, eTxtAge;
    Button btnBack;

    //2. Initialize Views
    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtAge = findViewById(R.id.editTextAge);
        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);

        //Intent rcv = getIntent();
        //String name = rcv.getStringExtra("keyName");
        //int age = rcv.getIntExtra("keyAge",0);

        //Bundle rcvBun = rcv.getBundleExtra("keyBundle");
        //String name = rcvBun.getString("keyName");
        //int age = rcvBun.getInt("keyAge",0);

        //Person person = (Person) rcv.getSerializableExtra("keyPerson");

        //eTxtName.setText(person.name);
        //eTxtAge.setText(String.valueOf(person.age));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting Layout on Activity
        setContentView(R.layout.activity_two);

        // After we Set Layout initialize Views
        initViews();
    }

    @Override
    public void onClick(View v) {
        if(v == btnBack){

            // We extracted data from UI
            String name = eTxtName.getText().toString();
            String age = eTxtAge.getText().toString();
            int iAge = Integer.parseInt(age);

            // Put Data in empty Intent
            Intent data = new Intent(); // no source and no destination
            data.putExtra("keyName",name);
            data.putExtra("keyAge",iAge);

            setResult(201, data);

            finish();
        }
    }
}
