package com.auribises.gw2018d;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class MyMusicService extends Service {

    String songToPlay;
    MediaPlayer mediaPlayer;

    public MyMusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyMusicService", "MyMusicService - onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("MyMusicService", "MyMusicService - onStartCommand");

        songToPlay = intent.getStringExtra("keySong");
        String songPath = Environment.getExternalStorageDirectory().getPath()+"/"+songToPlay;
        try {

            mediaPlayer = new MediaPlayer();

            //Uri uri = Uri.parse("http://somedomain.com/somesong.mp3");
            //mediaPlayer.setDataSource(this,uri);

            mediaPlayer.setDataSource(songPath);
            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (Exception e){
            e.printStackTrace();
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyMusicService", "MyMusicService - onDestroy");

        //mediaPlayer.pause();
        //mediaPlayer.seekTo(345);

        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
