package it.thomas.myplaylist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.RawResourceDataSource;
import androidx.media3.exoplayer.ExoPlayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class AnnieMarie extends AppCompatActivity {
    private Button btPrev, btPlay, btPause, btNext;
    private TextView tvLyrics;
    private ExoPlayer exoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annie_marie);
        initUI();
        initListeners();
        read();
    }


    private void initListeners() {
        btNext.setOnClickListener(v -> {
            Intent maroon = new Intent(this, Maroon.class);
            startActivity(maroon);
            updatePlayer(R.raw.memories_maroon);
        });

        btPrev.setOnClickListener(v -> {
            Intent austin = new Intent(this, Austin.class);
            startActivity(austin);
            updatePlayer(R.raw.sendit);
        });

        btPause.setOnClickListener(v -> {
            exoPlayer.pause();
        });

        btPlay.setOnClickListener(v -> {
            exoPlayer.play();
        });
    }

    @SuppressLint("ResourceType")
    private void initUI() {
        btNext = findViewById(R.id.btNext);
        btPrev = findViewById(R.id.btPrev);
        btPlay = findViewById(R.id.btPlay);
        btPause = findViewById(R.id.btPause);
        tvLyrics = findViewById(R.id.tvLyrics);
    }

    private void read(){
        ArrayList<String> lyrics = new ArrayList<>();
        try{
            FileReader file = new FileReader(String.valueOf(R.raw.twozerolyrics));
            BufferedReader buffer = new BufferedReader(file);
            String line = buffer.readLine();
            while (!line.equals("End of lyrics")) {
                tvLyrics.setText(buffer.readLine());
            }
        } catch (Exception e) {
            e.getMessage();
        }


    }

    private void updatePlayer(Integer s) {
        exoPlayer = new ExoPlayer.Builder(this).build();
        @OptIn(markerClass = UnstableApi.class) Uri song = RawResourceDataSource.buildRawResourceUri(s);
        exoPlayer.setMediaItem(MediaItem.fromUri(song));
        exoPlayer.prepare();
        exoPlayer.play();

    }


}