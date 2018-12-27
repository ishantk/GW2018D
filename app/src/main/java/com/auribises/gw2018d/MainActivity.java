package com.auribises.gw2018d;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // Declare Reference to Views
    TextView txtTitle;
    EditText eTxtNum1, eTxtNum2;
    Button btnAddNumbers;

    final String TAG = "MainActivity";

    // User Defined Function/Method
    // Create Objects and Initialize Reference Variables
    void initViews(){
        txtTitle = findViewById(R.id.textViewTitle);
        eTxtNum1 = findViewById(R.id.editTextNum1);
        eTxtNum2 = findViewById(R.id.editTextNum2);
        btnAddNumbers = findViewById(R.id.buttonSubmit);

        btnAddNumbers.setOnClickListener(this);

        getSupportActionBar().setTitle("Add Numbers UI");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Log.i(TAG,"==onCreate==");

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"==onStart==");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"==onRestart==");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"==onResume==");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"==onPause==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"==onStop==");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"==onDestroy==");
    }

    @Override
    public void onClick(View v) {
        String num1 = eTxtNum1.getText().toString();
        String num2 = eTxtNum2.getText().toString();

        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);

        int n3 = n1 + n2;

        txtTitle.setText("Welcome\nResult is:"+n3);
        eTxtNum1.setText("");
        eTxtNum2.setText("");
    }

    /*
    public void clickHandler(View view){

        Date date = new Date();
        String message = "Its: "+date;
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

        txtTitle.setText("Welcome\n"+message);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Explicit Way : to create menu
        /*menu.add(1,101,2,"All Songs");
        menu.add(1,201,5,"PlayLists");
        menu.add(1,301,1,"Artists");
        menu.add(2,401,3,"Favourites");
        menu.add(2,501,4,"Recently Played");*/


        // Implicit Way : to create menu
        getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //int gid = item.getGroupId();
        int id = item.getItemId();

        switch (id){
            case 101:
                Toast.makeText(this,"All Songs Selected",Toast.LENGTH_LONG).show();
                break;

            case 201:

                break;

            case 301:

                break;

            case 401:

                break;

            case 501:

                break;

            case R.id.allSongs:

                break;

            case R.id.fav:

                break;

            case R.id.bol:
                Toast.makeText(this,"Bollywood Songs Selected",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);

                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
