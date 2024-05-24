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

public class Maroon extends AppCompatActivity {
    private Button btPrev, btPlay, btPause, btNext;
    private TextView tvLyrics;
    private ExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maroon);
        initUI();
        initListeners();
    }

    private void initListeners() {
        btNext.setOnClickListener(v -> {
            Intent dualipa = new Intent(this, DuaLipa.class);
            startActivity(dualipa);
            updatePlayer(R.raw.newrules_dualipa);
        });

        btPrev.setOnClickListener(v -> {
            Intent annie = new Intent(this, AnnieMarie.class);
            startActivity(annie);
            updatePlayer(R.raw.anne_marie_twozerozerotwo);
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
    }

    private void updatePlayer(Integer s) {
        exoPlayer = new ExoPlayer.Builder(this).build();
        @OptIn(markerClass = UnstableApi.class) Uri song = RawResourceDataSource.buildRawResourceUri(s);
        exoPlayer.setMediaItem(MediaItem.fromUri(song));
        exoPlayer.prepare();
        exoPlayer.play();

    }
}