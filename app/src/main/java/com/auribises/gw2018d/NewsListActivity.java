package com.auribises.gw2018d;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class NewsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;

    void initViews(){

        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        adapter.add("Aaj Tak"); // 0
        adapter.add("Zee News");
        adapter.add("CNN IBN");
        adapter.add("DD News");
        adapter.add("NDTV");    // 4

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        getSupportActionBar().setTitle("All News");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String news = adapter.getItem(position);
        Toast.makeText(this,"You Selected: "+news,Toast.LENGTH_LONG).show();

        Intent intent = new Intent(NewsListActivity.this,NewsActivity.class);
        startActivity(intent);
    }
}
