package it.thomas.myplaylist;

import android.content.Intent;

public record Data(
        String artistName,
        Integer artistPhoto,
        Integer artistSong,
        Intent intent
) {
}
