package com.auribises.gw2018d;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MusicPlayerActivity extends AppCompatActivity implements View.OnClickListener{

    String songToPlay;
    TextView txtSong;
    Button btnPlay, btnStop;


    void initViews(){

        Intent rcv = getIntent();
        songToPlay = rcv.getStringExtra("keySong");

        txtSong = findViewById(R.id.textViewSong);
        btnPlay = findViewById(R.id.buttonPlay);
        btnStop = findViewById(R.id.buttonStop);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        txtSong.setText(songToPlay);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        initViews();
    }

    @Override
    public void onClick(View v) {
        if(v == btnPlay){

        }else{

        }
    }
}
