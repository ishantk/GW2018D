package com.auribises.gw2018d;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class MyMusicActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;


    void initViews(){
        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        // Path to SD Card
        String path = Environment.getExternalStorageDirectory().getPath();

        // READ_EXTERNAL_STORAGE Permissions to be applied in Manifest File
        // Also permission has to be granted in settings
        File file = new File(path);
        String[] files = file.list();

        for(String fileName : files){
            if(fileName.endsWith(".mp3")) {
                adapter.add(fileName);
            }
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String songName = adapter.getItem(position);
        Intent intent = new Intent(MyMusicActivity.this, MusicPlayerActivity.class);
        intent.putExtra("keySong",songName);
        startActivity(intent);
    }
}
