package com.example.android.musicapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Meliphant on 25.02.2018.
 */

public class SongActivity extends AppCompatActivity {

    private TextView mTextSong;
    private TextView mTextGroup;
    private String mSong;
    private String mGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        mTextSong = (TextView) findViewById(R.id.song_name);
        mTextGroup = (TextView) findViewById(R.id.artist_name);

        mSong = getIntent().getExtras().getString("song");
        mGroup = getIntent().getExtras().getString("artist");
        mTextSong.setText(mSong);
        mTextGroup.setText(mGroup);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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