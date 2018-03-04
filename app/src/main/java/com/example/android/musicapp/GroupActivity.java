package com.example.android.musicapp;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private TypedArray imgs;
    private static final String KEY_SONG = "song";
    private static final String KEY_ARTIST = "artist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        ArrayList<Song> songsArtist = new ArrayList<Song>();
        String mArtist;
        mArtist = getIntent().getExtras().getString(KEY_ARTIST);

        // get albums name array and albums' images array
        String[] gridAllAlbums = getResources().getStringArray(R.array.grid_all_albums);
        imgs = getResources().obtainTypedArray(R.array.list_all_images);

        // get array with array's ids
        TypedArray arrayOfArrays = getResources().obtainTypedArray(R.array.list_all_arrays);

        for (int j = 0; j < gridAllAlbums.length; j++){
            if (mArtist.equalsIgnoreCase(gridAllAlbums[j])){
                updateLayout(mArtist, j);
                if (gridAllAlbums.length == arrayOfArrays.length()) {
                    int id = arrayOfArrays.getResourceId(j, 0);
                    int arrayLength = getResources().getStringArray(id).length;
                    String[] array = new String[arrayLength];
                    if (id > 0) array = getResources().getStringArray(id);
                    for (int i = 0; i < array.length; i++) {
                        songsArtist.add(new Song(array[i], gridAllAlbums[j], Integer.toString(i + 1)));
                    }
                }
            }
        }
        arrayOfArrays.recycle();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // floating button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.snackbar_status_playing, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SongArrayAdapter arrayAdapter = new SongArrayAdapter(this, songsArtist);
        ListView listView = findViewById(R.id.songs_list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(this);
    }

    private void updateLayout(String artist, int i){
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(artist);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        TextView textView = findViewById(R.id.group_title);
        textView.setText(artist);
        textView.setTextColor(getResources().getColor(R.color.white));
        ImageView imageView = findViewById(R.id.image_group);
        imageView.setImageResource(imgs.getResourceId(i, -1));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        TextView textViewSong = view.findViewById(R.id.template_song_name);
        String song = textViewSong.getText().toString();

        TextView textViewSinger = view.findViewById(R.id.template_artist_name);
        String artist = textViewSinger.getText().toString();

        Intent intent = new Intent(this, SongActivity.class);
        intent.putExtra(KEY_SONG, song);
        intent.putExtra(KEY_ARTIST, artist);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return super.onSupportNavigateUp();
    }
}