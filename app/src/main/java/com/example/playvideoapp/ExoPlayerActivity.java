package com.example.playvideoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class ExoPlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    private String url_NG = "https://sire-ngcuk-pmd.fichub.com/mpx/NGP_NatGeo_GS/645/880/180620_UNESCO_Great_Barrier_Reef_UK~~~~~uk~mux~~1_222939717064_mp4_video_1920x1080_4000000_primary_audio_eng_6.mp4";
    private SimpleExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo_player);

        playerView = findViewById(R.id.id_player_View);

        Toast.makeText(getApplicationContext(),"Exo Player ACtivity Reached",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        exoPlayer = ExoPlayerFactory.newSimpleInstance(getApplicationContext(), new DefaultTrackSelector());

        DataSource.Factory datasourceFactory = new DefaultDataSourceFactory(getApplicationContext(), Util.getUserAgent(this, getString(R.string.app_name)));

        MediaSource videoSource = new ExtractorMediaSource.Factory(datasourceFactory)
                .createMediaSource(Uri.parse(url_NG));

        exoPlayer.prepare(videoSource);

        // exoPlayer.setMediaSource(mediaSource);
        //exoPlayer.prepare(mediaSource);

        playerView.setPlayer(exoPlayer);
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        exoPlayer.release();
        exoPlayer=null;
    }
}