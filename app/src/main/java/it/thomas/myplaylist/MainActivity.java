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
    private static ExoPlayer exoPlayer;

    private Intent intent;

    static final List<Data> mySongs = List.of(
            new Data("Annie Marie", R.drawable.anne_marie, R.raw.anne_marie_twozerozerotwo),
            new Data("Maroon5", R.drawable.maroon, R.raw.memories_maroon),
            new Data("Dua Lipa", R.drawable.dualipa, R.raw.newrules_dualipa),
            new Data("Ariana Grande", R.drawable.ariana, R.raw.positions_ariana),
            new Data("Austin Mahone", R.drawable.austin, R.raw.sendit)
    );

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

    public void onSongClick(View view) {
        intent = new Intent(this, SongPlay.class);
        if(view.getId() == R.id.cdAnnie) {
            intent.putExtra("artist_index", 0);
            intent.putExtra("artist", mySongs.get(0));
        } else if (view.getId() == R.id.cdMaroon) {
            intent.putExtra("artist_index", 1);
            intent.putExtra("artist", mySongs.get(1));
        } else if (view.getId() == R.id.cdDualipa) {
            intent.putExtra("artist_index", 2);
            intent.putExtra("artist", mySongs.get(2));
        } else if (view.getId() == R.id.cdAriana) {
            intent.putExtra("artist_index", 3);
            intent.putExtra("artist", mySongs.get(3));
        } else if (view.getId() == R.id.cdAustin) {
            intent.putExtra("artist_index", 4);
            intent.putExtra("artist", mySongs.get(4));
        }
        startActivity(intent);
    }

}