package it.thomas.myplaylist;

import static it.thomas.myplaylist.MainActivity.mySongs;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
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

public class SongPlay extends AppCompatActivity {
    private TextView tvArtist;
    private ImageView ivArtist;
    private Button btPrev, btPause, btNext;
    private int artistIndex;
    private Data data;
    private ExoPlayer exoPlayer;
    private SeekBar seekBar;
    private Handler handler;
    private Runnable updateSeekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_play);
        initDataFromIntent();
        initUI();
        initPlayer();
        initSeekbar();
        initListeners();
    }

    private void initSeekbar() {
        seekBar = findViewById(R.id.seekbar);
        handler = new Handler();
        updateSeekbar = new Runnable() {
            @Override
            public void run() {
                if (exoPlayer != null) {
                    int currentPosition = (int) exoPlayer.getCurrentPosition();
                    int totalDuration = (int) exoPlayer.getDuration();
                    seekBar.setProgress(currentPosition * 100 / totalDuration);
                    handler.postDelayed(this, 1000);
                }
            }
        };
    }

    private void initListeners() {
        btNext.setOnClickListener(v -> {
            if(artistIndex == mySongs.size() - 1) {
                artistIndex = 0;
            } else {
                artistIndex++;
            }
            data = mySongs.get(artistIndex);
            updatePlayer();
            seekBar.setProgress(0);
        });

        btPause.setOnClickListener(v -> {
            if(exoPlayer.isPlaying()) {
                exoPlayer.pause();
                btPause.setText("play");
                handler.removeCallbacks(updateSeekbar);
            } else {
                exoPlayer.play();
                btPause.setText("pause");
                handler.postDelayed(updateSeekbar, 1000);
            }
        });

        btPrev.setOnClickListener(v -> {
            if (artistIndex == 0) {
                artistIndex = mySongs.size() - 1;
            } else {
                artistIndex--;
            }
            data = mySongs.get(artistIndex);
            updatePlayer();
            seekBar.setProgress(0);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int newPosition = (int) (exoPlayer.getDuration() * progress / 100);
                    exoPlayer.seekTo(newPosition);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (exoPlayer.isPlaying())
                    exoPlayer.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                exoPlayer.play();
            }
        });
    }

    private void initPlayer() {
        if (exoPlayer == null) {
            exoPlayer = new ExoPlayer.Builder(this).build();
        }
        updatePlayer();
    }

    private void updatePlayer() {
        tvArtist.setText(data.artistName());
        ivArtist.setImageResource(data.artistPhoto());
        @OptIn(markerClass = UnstableApi.class) Uri song = RawResourceDataSource.buildRawResourceUri(data.artistSong());

        exoPlayer.setMediaItem(MediaItem.fromUri(song));
        exoPlayer.prepare();
        exoPlayer.play();
        exoPlayer.seekTo(0);
    }

    private void initUI() {
        tvArtist = findViewById(R.id.tv_artist);
        ivArtist = findViewById(R.id.iv_artist);
        btNext = findViewById(R.id.bt_next);
        btPrev = findViewById(R.id.bt_prev);
        btPause = findViewById(R.id.bt_pause);

    }

    private void initDataFromIntent() {
        if(getIntent() != null) {
            artistIndex = getIntent().getIntExtra("artist_index", 0);
            data = (Data) getIntent().getSerializableExtra("artist");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        exoPlayer.release();
        handler.removeCallbacks(updateSeekbar);
    }
}