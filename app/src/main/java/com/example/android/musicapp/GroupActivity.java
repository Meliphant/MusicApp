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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String mArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        ArrayList<Song> songsArtist = new ArrayList<Song>();

        mArtist = getIntent().getExtras().getString("artist");

        // define AppBar Layout to inflate it an image
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);

        // get albums name array
        String[] gridAllAlbums = getResources().getStringArray(R.array.grid_all_albums);
        // get albums' images array
        TypedArray imgs = getResources().obtainTypedArray(R.array.list_all_images);
        // get array with array's ids
        TypedArray arrayOfArrays = getResources().obtainTypedArray(R.array.list_all_arrays);
        // define new matrix for songs list for each album
        String[][] array = new String[arrayOfArrays.length()][];
        // get array with albums songs for each matrix arrayOfArrays row
        for (int i = 0; i < arrayOfArrays.length(); ++i) {
            int id = arrayOfArrays.getResourceId(i, 0);
            if (id > 0) {
                array[i] = getResources().getStringArray(id);
            }
        }
        for (int j = 0; j < gridAllAlbums.length; j++){
            if (mArtist.equalsIgnoreCase(gridAllAlbums[j])){
                // set Image for clicked playlist screen
                if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    appBarLayout.setBackgroundResource(imgs.getResourceId(j, -1));
                } else {
                    appBarLayout.setBackgroundResource(imgs.getResourceId(j, -1));
                }
                // set Title for clicked playlist screen
                collapsingToolbarLayout.setTitle(mArtist);
                // inflate songs for clicked playlist screen
                for (int i = 0; i < array[j].length; i++) {
                    songsArtist.add(new Song(array[j][i], gridAllAlbums[j], Integer.toString(i + 1)));
                }
            }
        }
        arrayOfArrays.recycle();

        // TODO: ! перенести наполение плейлиста в метод и вызывать его после клика, а не при загрузке главного экрана.
        // TODO: добавить проверку на совпадение длин массивов gridAllAlbums и arrayOfArrays.

        // adds back arrow to the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // floating button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Imagine that music now is playing...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        SongArrayAdapter arrayAdapter = new SongArrayAdapter(this, songsArtist);
        ListView listView = (ListView) findViewById(R.id.songs_list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        TextView textViewSong = (TextView) view.findViewById(R.id.template_song_name);
        String song = textViewSong.getText().toString();

        TextView textViewSinger = (TextView) view.findViewById(R.id.template_artist_name);
        String artist = textViewSinger.getText().toString();

        Intent intent = new Intent(this, SongActivity.class);
        intent.putExtra("song", song);
        intent.putExtra("artist", artist);
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
