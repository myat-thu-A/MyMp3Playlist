package it.thomas.myplaylist;

import android.content.Intent;

import java.io.Serializable;

public record Data(
        String artistName,
        Integer artistPhoto,
        Integer artistSong
) implements Serializable {
}
