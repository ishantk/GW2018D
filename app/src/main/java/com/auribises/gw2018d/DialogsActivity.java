package com.auribises.gw2018d;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DialogsActivity extends AppCompatActivity {

    Dialog dialog;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
    }

    void showProgressDialog(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        //progressDialog.setCancelable(false); // Cannot destroy dialog on back press
        progressDialog.show();
    }

    void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("This is Title");
        builder.setMessage("This is Message for You !!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        /*builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });*/
        builder.setNegativeButton("Cancel",null);
        //builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void showDialogWithItems(){

        String[] items = {"View", "Update", "Delete", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:

                        break;

                    case 3:
                        finish();
                        break;

                }
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    void showCustomDialog(){
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_dialog);

        btn = dialog.findViewById(R.id.button9);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dialog.show();
    }


    public void showDialog(View view){
        //showProgressDialog();
        //showAlertDialog();
        //showDialogWithItems();
        showCustomDialog();
    }
}
