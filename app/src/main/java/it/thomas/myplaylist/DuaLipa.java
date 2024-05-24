package it.thomas.myplaylist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.RawResourceDataSource;
import androidx.media3.exoplayer.ExoPlayer;

import java.util.List;

public class DuaLipa extends AppCompatActivity {
    private Button btPrev, btPlay, btPause, btNext;
    private TextView tvLyrics;
    private ExoPlayer exoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua_lipa);
        initUI();
        initListeners();
    }

    private void initListeners() {
        btNext.setOnClickListener(v -> {
            Intent ariana = new Intent(this, Ariana.class);
            startActivity(ariana);
            updatePlayer(R.raw.positions_ariana);
        });

        btPrev.setOnClickListener(v -> {
            Intent maroon = new Intent(this, Maroon.class);
            startActivity(maroon);
            updatePlayer(R.raw.memories_maroon);
        });

        btPause.setOnClickListener(v -> {
            exoPlayer.pause();
        });

        btPlay.setOnClickListener(v -> {
            exoPlayer.play();
        });
    }

    private void initUI() {
        btNext = findViewById(R.id.btNext);
        btPrev = findViewById(R.id.btPrev);
        btPlay = findViewById(R.id.btPlay);
        btPause = findViewById(R.id.btPause);
        tvLyrics = findViewById(R.id.tvLyrics);
    }

    private void updatePlayer(Integer s) {
        exoPlayer = new ExoPlayer.Builder(this).build();
        @OptIn(markerClass = UnstableApi.class) Uri song = RawResourceDataSource.buildRawResourceUri(s);
        exoPlayer.setMediaItem(MediaItem.fromUri(song));
        exoPlayer.prepare();
        exoPlayer.play();

    }
}