package com.auribises.gw2018d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    EditText eTxtName, eTxtEmail;
    CheckBox cbCpp, cbJava, cbPython;
    RadioButton rbMale, rbFemale;
    Spinner spCity;
    Button btnSubmit;

    ArrayAdapter<String> adapter;
    EnquiryModel enquiryModel;

    void initViews(){

        enquiryModel = new EnquiryModel();

        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);

        cbCpp = findViewById(R.id.checkBoxcpp);
        cbJava = findViewById(R.id.checkBoxJava);
        cbPython = findViewById(R.id.checkBoxPython);

        rbMale = findViewById(R.id.radioButtonMale);
        rbFemale = findViewById(R.id.radioButtonFemale);

        spCity = findViewById(R.id.spinnerCity);
        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enquiryModel.name = eTxtName.getText().toString();
                enquiryModel.email = eTxtEmail.getText().toString();

                Toast.makeText(ViewsActivity.this, enquiryModel.toString(),Toast.LENGTH_LONG).show();
            }
        });

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.add("==Select City==");
        adapter.add("Ludhiana");
        adapter.add("Chandigarh");
        adapter.add("Delhi");
        adapter.add("Bangalore");
        adapter.add("Pune");

        spCity.setAdapter(adapter);

        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String city = adapter.getItem(position);
                enquiryModel.city = city;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cbCpp.setOnCheckedChangeListener(this);
        cbJava.setOnCheckedChangeListener(this);
        cbPython.setOnCheckedChangeListener(this);
        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
        initViews();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

//        if(buttonView == cbCpp){
//
//        }

        int id =buttonView.getId();

        switch (id){
            case R.id.checkBoxcpp:

                enquiryModel.cpp = isChecked;

                break;

            case R.id.checkBoxJava:

                enquiryModel.java = isChecked;

                break;

            case R.id.checkBoxPython:

                enquiryModel.python = isChecked;

                break;

            case R.id.radioButtonMale:

                if(isChecked){
                    enquiryModel.gender = "Male";
                }

                break;

            case R.id.radioButtonFemale:

                if(isChecked){
                    enquiryModel.gender = "Female";
                }


                break;
        }

    }
}

// Assignment : Open Next Activity and show these details by forward passing the object as Serializable
//              Explore : AutoCompleteTextView (Spinner) | RatingBar (width : wrap_content) | switch
