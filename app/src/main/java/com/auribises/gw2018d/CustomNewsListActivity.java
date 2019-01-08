package com.auribises.gw2018d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomNewsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayList<News> newsList;
    NewsAdapter adapter;

    void initViews(){

        listView = findViewById(R.id.listView);

        newsList = new ArrayList<>();

        News news1 = new News(R.drawable.lightbulb,"Zee News","http://zeenews.india.com/");
        News news2 = new News(R.drawable.category,"Aaj Tak","http://zeenews.india.com/");
        News news3 = new News(R.drawable.contact,"CNN IBN","http://zeenews.india.com/");
        News news4 = new News(R.drawable.folder,"NDTV","http://zeenews.india.com/");
        News news5 = new News(R.drawable.book,"India Today","http://zeenews.india.com/");

        newsList.add(news1);
        newsList.add(news2);
        newsList.add(news3);
        newsList.add(news4);
        newsList.add(news5);

        adapter = new NewsAdapter(this, R.layout.list_item, newsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_news_list);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        Toast.makeText(this, "You Selected: "+news.toString(),Toast.LENGTH_LONG).show();
    }
}
