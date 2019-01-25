package com.auribises.gw2018d.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.gw2018d.R;
import com.auribises.gw2018d.model.Customer;
import com.auribises.gw2018d.model.Util;

import java.util.ArrayList;
import java.util.List;

/*
Ishants-Macbook-Air:~ ishantkumar$ cd /Users/ishantkumar/Library/Android/sdk
Ishants-Macbook-Air:sdk ishantkumar$ pwd
/Users/ishantkumar/Library/Android/sdk
Ishants-Macbook-Air:sdk ishantkumar$ cd platform-tools/
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb shell
generic_x86:/ $ exit
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb root
restarting adbd as root
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adn shell
-bash: ./adn: No such file or directory
Ishants-Macbook-Air:platform-tools ishantkumar$ ./adb shell
generic_x86:/ # cd data/data/com.auribises.gw2018d/databases
generic_x86:/data/data/com.auribises.gw2018d/databases # ls
Customer.db Customer.db-journal
generic_x86:/data/data/com.auribises.gw2018d/databases # sqlite3 Customer.db
SQLite version 3.19.4 2017-08-18 19:28:12
Enter ".help" for usage hints.
sqlite> .tables
Customers         android_metadata
sqlite> select * from Customers;
1|John|9898989898|john@example.com
2|Jennie|9090909090|jennie@example.com
3|Harry|8989898989|harry@example.com



CALL_PHONE -> Permissions Required

Intent intent = new Intent(Intent.ACTION_CALL)
intent.setData(Uri.parse("tel:+91 99155 71177"));
startActivity(intent);

 */

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;


public class AddCustomersActivity extends AppCompatActivity implements View.OnClickListener{

    EditText eTxtName, eTxtPhone, eTxtEmail;
    Button btnAdd;
    Customer customer;

    ContentResolver resolver;

    ArrayList<Customer> customers;

    FirebaseAuth auth;
    FirebaseFirestore db;

    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);
        eTxtEmail = findViewById(R.id.editTextEmail);
        btnAdd = findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(this);

        resolver = getContentResolver();

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customers);
        initViews();
    }

    void insertCustomerInDB(){
        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME, customer.name);
        values.put(Util.COL_PHONE, customer.phone);
        values.put(Util.COL_EMAIL, customer.email);

        Uri uri = resolver.insert(Util.TAB_URI, values);
        Toast.makeText(this,"Customer Inserted. ID:"+uri.getLastPathSegment(),Toast.LENGTH_LONG).show();

        clearFields();
    }

    void fetchCustomers(){

        String[] projection = {Util.COL_ID, Util.COL_NAME, Util.COL_PHONE, Util.COL_EMAIL};
        Cursor cursor = resolver.query(Util.TAB_URI, projection, null, null, null);

        if(cursor!=null){

            customers = new ArrayList<>();

            while(cursor.moveToNext()){
                Customer customer = new Customer();
                customer.id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                customer.name= cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                customer.phone = cursor.getString(cursor.getColumnIndex(Util.COL_PHONE));
                customer.email = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));

                Log.i("CUSTOMER",customer.toString());
                customers.add(customer);

                // Show Customers on RecyclerView/ListView
            }

        }
    }

    void updateCustomer(){
        String where = Util.COL_ID+" = "+1;
        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME, "John Watson");
        values.put(Util.COL_PHONE, "+91 99999 99999");

        int i = resolver.update(Util.TAB_URI, values, where, null);
        if(i>0){
            Toast.makeText(this,"Customer Updated",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Customer Not Updated",Toast.LENGTH_LONG).show();
        }
    }

    void deleteCustomer(){
        String where = Util.COL_ID+" = "+1;


        int i = resolver.delete(Util.TAB_URI, where, null);
        if(i>0){
            Toast.makeText(this,"Customer Deleted",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Customer Not Deleted",Toast.LENGTH_LONG).show();
        }
    }

    void clearFields(){
        eTxtName.setText("");
        eTxtPhone.setText("");
        eTxtEmail.setText("");
    }

    void saveUserInFirebase(){

        db.collection("users").document().set(customer)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddCustomersActivity.this,"User Details Saved",Toast.LENGTH_LONG).show();
                        }
                    }
                });

        //db.collection("users").add(customer).add...
        //db.collection("users").document("id").delete()...;
        /*db.collection("users").get()
                .addOnCompleteListener(this, new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<DocumentSnapshot> list = task.getResult().getDocuments();
                        //Customer cRef = list.get(0).toObject(Customer.class);
                    }
                });*/
    }

    void createUserInFirebase(){

        auth.createUserWithEmailAndPassword(customer.email, customer.phone)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddCustomersActivity.this,"User Registered",Toast.LENGTH_LONG).show();
                            saveUserInFirebase();
                        }
                    }
                });

    }

    void signInUserFromFirebase(){
        auth.signInWithEmailAndPassword(customer.email, customer.phone)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AddCustomersActivity.this,"User Signed In",Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    @Override
    public void onClick(View v) {
        customer = new Customer();
        customer.name = eTxtName.getText().toString();
        customer.phone = eTxtPhone.getText().toString();
        customer.email = eTxtEmail.getText().toString();

        //insertCustomerInDB();
        //fetchCustomers();
        //updateCustomer();
        //deleteCustomer();
        createUserInFirebase();
    }
}
