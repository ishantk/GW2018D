package com.auribises.gw2018d;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class PersistanceDemoActivity extends AppCompatActivity implements View.OnClickListener{

    //@BindView(R.id.editTextData)
    EditText eTxtData;

    //@BindView(R.id.button)
    Button btn;

    String data;

    // SharedPreferences is an XML file. Data is saved as key value pair !!
    SharedPreferences sharedPreferences;

    // Editor will save data in SharedPreferences
    SharedPreferences.Editor editor;

    void initViews(){
        eTxtData = findViewById(R.id.editTextData);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persistance_demo);

        getSupportActionBar().setTitle("Persistance Demo");

        //ButterKnife.bind(this);
        initViews();
    }

    public void writeInFile(){
        try{

            //FileOutputStream outputStream = openFileOutput("data.txt",MODE_PRIVATE);

            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";
            FileOutputStream outputStream = new FileOutputStream(path);
            outputStream.write(data.getBytes());
            outputStream.close();

            Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show();
            eTxtData.setText("");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void readFromFile(){
        try{
            //FileInputStream inputStream = openFileInput("data.txt");

            String path = Environment.getExternalStorageDirectory().getPath()+"/data.txt";
            FileInputStream inputStream = new FileInputStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            eTxtData.setText(line);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        //data = eTxtData.getText().toString();
        //writeInFile();

        //readFromFile();

        /*
        data = eTxtData.getText().toString();
        editor.putString("keyQuote",data);
        editor.putInt("keyLikes",100);

        // To save data in XML file
        editor.apply();     // apply will run in background thread (If we have lot of data)
        //editor.commit();  // run in the same thread
        Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show();
        eTxtData.setText("");
        */

        String data = sharedPreferences.getString("keyQuote","NA");
        int likes = sharedPreferences.getInt("keyLikes",0);

        eTxtData.setText(likes+"\n"+data);

        //editor.clear();
        //editor.apply();

    }

    // Assignment : ActivityOne will not be opened in case user has opened it once
}
