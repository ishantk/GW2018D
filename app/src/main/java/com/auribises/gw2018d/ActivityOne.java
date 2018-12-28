package com.auribises.gw2018d;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener{

    //1. Declare Reference Variables to Views
    EditText eTxtName, eTxtAge;
    Button btnSubmit;

    //2. Initialize Views
    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtAge = findViewById(R.id.editTextAge);
        btnSubmit = findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setting Layout on Activity
        setContentView(R.layout.activity_one);

        // After we Set Layout initialize Views
        initViews();
    }

    @Override
    public void onClick(View v) {
        if(v == btnSubmit){

            // We extracted data from UI
            //String name = eTxtName.getText().toString();
            //String age = eTxtAge.getText().toString();
            //int iAge = Integer.parseInt(age);

            // Start a new Activity
            Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);

            //1. Forward Passing - Data in putExtra
            //intent.putExtra("keyName",name);
            //intent.putExtra("keyAge",iAge);

            //2. Forward Passing - Data in Bundle
            //Bundle bundle = new Bundle();
            //bundle.putString("keyName",name);
            //bundle.putInt("keyAge",iAge);

            //intent.putExtra("keyBundle",bundle);

            //3. Forward Passing - Data in Object
            // We extracted data from UI
            Person person = new Person();
            person.name = eTxtName.getText().toString();
            try {
                person.age = Integer.parseInt(eTxtAge.getText().toString());

                intent.putExtra("keyPerson",person);
                startActivity(intent);

            }catch (Exception e){
                Toast.makeText(this,"Please Enter Age in number",Toast.LENGTH_LONG).show();
            }


        }
    }
}
