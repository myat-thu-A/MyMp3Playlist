package it.thomas.myplaylist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.OptIn;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.RawResourceDataSource;
import androidx.media3.exoplayer.ExoPlayer;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private CardView cdAnnie, cdMaroon, cdDualipa, cdAriana, cdAustin;
    private ExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }


    private void initUI() {
        cdAnnie = findViewById(R.id.cdAnnie);
        cdMaroon = findViewById(R.id.cdMaroon);
        cdDualipa = findViewById(R.id.cdDualipa);
        cdAriana = findViewById(R.id.cdAriana);
        cdAustin = findViewById(R.id.cdAustin);
    }

    private void updatePlayer(Integer s) {
        exoPlayer = new ExoPlayer.Builder(this).build();
        @OptIn(markerClass = UnstableApi.class) Uri song = RawResourceDataSource.buildRawResourceUri(s);
        exoPlayer.setMediaItem(MediaItem.fromUri(song));
        exoPlayer.prepare();
        exoPlayer.play();

    }

    public void songClicked(View view) {
        int clickId = view.getId();
        if(clickId == R.id.cdAnnie) {
            Intent annie = new Intent(this, AnnieMarie.class);
            startActivity(annie);
            updatePlayer(R.raw.anne_marie_twozerozerotwo);
        } else if (clickId == R.id.cdMaroon) {
            Intent maroon = new Intent(this, Maroon.class);
            startActivity(maroon);
            updatePlayer(R.raw.memories_maroon);
        } else if (clickId == R.id.cdDualipa) {
            Intent dualipa = new Intent(this, DuaLipa.class);
            startActivity(dualipa);
            updatePlayer(R.raw.newrules_dualipa);
        } else if (clickId == R.id.cdAriana) {
            Intent ariana = new Intent(this, Ariana.class);
            startActivity(ariana);
            updatePlayer(R.raw.positions_ariana);
        } else {
            Intent austin = new Intent(this, Austin.class);
            startActivity(austin);
            updatePlayer(R.raw.sendit);
        }
    }
}