package com.auribises.gw2018d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        txtTitle = findViewById(R.id.textViewTitle);
    }

    public void clickHandler(View view){

        Date date = new Date();
        String message = "Its: "+date;
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        txtTitle.setText("Welcome\n"+message);
    }
}
