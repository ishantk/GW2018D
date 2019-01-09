package com.auribises.gw2018d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomNewsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //ListView listView;
    //GridView listView;

    RecyclerView recyclerView;

    ArrayList<News> newsList;
    NewsAdapter adapter;
    NewsRecyclerAdapter recyclerAdapter;

    void initViews(){

        //listView = findViewById(R.id.listView);
        recyclerView = findViewById(R.id.recyclerView);

        newsList = new ArrayList<>();

        News news1 = new News(R.drawable.lightbulb,"Zee News","http://zeenews.india.com/");
        News news2 = new News(R.drawable.category,"Aaj Tak","http://zeenews.india.com/");
        News news3 = new News(R.drawable.contact,"CNN IBN","http://zeenews.india.com/");
        News news4 = new News(R.drawable.folder,"NDTV","http://zeenews.india.com/");
        News news5 = new News(R.drawable.book,"India Today","http://zeenews.india.com/");
        News news6 = new News(R.drawable.category,"Aaj Tak","http://zeenews.india.com/");
        News news7 = new News(R.drawable.lightbulb,"Aaj Tak","http://zeenews.india.com/");

        newsList.add(news1); // 0
        newsList.add(news2);
        newsList.add(news3);
        newsList.add(news4);
        newsList.add(news5); // 4
        newsList.add(news6);
        newsList.add(news7);

        //adapter = new NewsAdapter(this, R.layout.list_item, newsList);
        //listView.setAdapter(adapter);

        //listView.setOnItemClickListener(this);

        recyclerAdapter = new NewsRecyclerAdapter(this, R.layout.list_item, newsList);

        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //StaggeredGridLayoutManager -> Explore API

        recyclerView.setAdapter(recyclerAdapter);

        // To refresh data in RecyclerView or ListView or GridView
        //recyclerAdapter.notifyDataSetChanged();
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
