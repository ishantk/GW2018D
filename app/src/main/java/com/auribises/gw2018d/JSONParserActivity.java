package com.auribises.gw2018d;

import android.app.ProgressDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class JSONParserActivity extends AppCompatActivity {

    StringBuffer response;
    ProgressDialog progressDialog;
    ArrayList<Book> books;

    // Volley API's
    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonparser);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        if(isInternetConnected()){
            //FetchJSONData fRef = new FetchJSONData();
            //fRef.execute();
            //new FetchJSONData().execute();

            fetchJSONData();

        }else{
            Toast.makeText(this,"Please Connect to Internet and try Again!!",Toast.LENGTH_LONG).show();
        }

    }

    boolean isInternetConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //boolean check = networkInfo!=null && networkInfo.isConnected();
        //return check;
        return networkInfo!=null && networkInfo.isConnected();
    }

    void fetchJSONData(){
        requestQueue = Volley.newRequestQueue(this);

        stringRequest = new StringRequest(
                Request.Method.GET,
                "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // JSON Parsing
                        try{
                            books = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("bookstore");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jObj = jsonArray.getJSONObject(i);
                                Book book = new Book();
                                book.price = jObj.getString("price");
                                book.name = jObj.getString("name");
                                book.author = jObj.getString("author");
                                books.add(book);

                            }

                            // Further display data on Custom ListView
                            // Explore WebService to get songs as JSON
                            // Use newsapi.org to display news from web service
                            // Explore Piscasso to display image on imageview from some URL

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        // Executing Request
        requestQueue.add(stringRequest);
    }

    // Background Task
    // 1. Service
    // 2. AsyncTask | Like threads in Java

    class FetchJSONData extends AsyncTask{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        //doInBackground cannot be used to use UI Elements
        @Override
        protected Object doInBackground(Object[] objects) {

            try{

                String serverUrl = "http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2";
                URL url = new URL(serverUrl);
                URLConnection urlConnection = url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                response = new StringBuffer();

                String line = "";

                while((line = bufferedReader.readLine()) != null){
                    response.append(line);
                }

                Log.i("JSONParserActivity",response.toString());

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            progressDialog.dismiss();
            Toast.makeText(JSONParserActivity.this,response.toString(),Toast.LENGTH_LONG).show();


            // JSON Parsing
            try{
                books = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("bookstore");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jObj = jsonArray.getJSONObject(i);
                    Book book = new Book();
                    book.price = jObj.getString("price");
                    book.name = jObj.getString("name");
                    book.author = jObj.getString("author");
                    books.add(book);

                }

                // Further display data on Custom ListView
                // Explore WebService to get songs as JSON
                // Use newsapi.org to display news from web service
                // Explore Piscasso to display image on imageview from some URL

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
