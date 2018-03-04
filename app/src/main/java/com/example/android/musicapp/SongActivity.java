package com.example.android.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Meliphant on 25.02.2018.
 */

public class SongActivity extends AppCompatActivity {

    private String mSong;
    private String mGroup;
    private int switcher = 0;
    private static final String KEY_SONG = "song";
    private static final String KEY_ARTIST = "artist";
    private static final String SHARE_TYPE = "text/html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        mSong = getIntent().getExtras().getString(KEY_SONG);
        mGroup = getIntent().getExtras().getString(KEY_ARTIST);

        TextView mTextSong = findViewById(R.id.song_name);
        TextView mTextGroup = findViewById(R.id.artist_name);

        mTextSong.setText(mSong);
        mTextGroup.setText(mGroup);
        ImageView imageView = findViewById(R.id.song_background);
        imageView.setImageResource(R.drawable.background);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        final ImageView buttonPlayHandler = findViewById(R.id.play_button);
        buttonPlayHandler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switcher == 0) {
                    Snackbar.make(view, R.string.snackbar_status_playing, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    buttonPlayHandler.setImageResource(R.drawable.ic_pause_circle_filled);
                    switcher = 1;
                } else {
                    Snackbar.make(view, R.string.snackbar_status_stopped, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    buttonPlayHandler.setImageResource(R.drawable.ic_play_circle_filled);
                    switcher = 0;
                }
            }
        });
        final TextView shareButton = findViewById(R.id.share);
        shareButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(SHARE_TYPE);
                intent.putExtra(android.content.Intent.EXTRA_TEXT, "Enjoy and listen to music\n" + mGroup + " - " + mSong);
                Intent chooser = Intent.createChooser(intent, "Share your music with friends");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(chooser);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}