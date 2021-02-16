package com.example.playvideoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this,R.raw.autostrada_video_10feb);
        surfaceView = findViewById(R.id.id_surfaceView);

        surfaceView.setKeepScreenOn(true); // keep the surface on while we are plaing the video

        SurfaceHolder holder = surfaceView.getHolder();
        //surface is an interface that makes sure to put the view on top of our main view
        holder.addCallback(this);
        holder.setFixedSize(400,300);

        Button play = findViewById(R.id.id_btnPlay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        Button pause = findViewById(R.id.id_btnPause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });

        Button skip = findViewById(R.id.id_btnSkip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get to the middle ov video when pressing skip button
                mediaPlayer.seekTo(mediaPlayer.getDuration()/2);
            }
        });

        Button go_to_ExoPlayer = findViewById(R.id.id_EXO);
        go_to_ExoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ExoPlayerActivity.class));
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mediaPlayer.setDisplay(surfaceHolder);
        //mediaPlayer.start(); //to start right away when app is turned on

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(mediaPlayer != null){
            mediaPlayer.pause();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}