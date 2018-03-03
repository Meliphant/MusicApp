package com.example.android.musicapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String KEY_ARTIST = "artist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        ArrayList<Group> groups = new ArrayList<>();

        groups.add(new Group(getString(R.string.group_1), R.drawable.muse));
        groups.add(new Group(getString(R.string.group_2), R.drawable.ofmm));
        groups.add(new Group(getString(R.string.group_3), R.drawable.pguys));
        groups.add(new Group(getString(R.string.group_4), R.drawable.aurora));

        GridView gridView = findViewById(R.id.gridview);
        GroupArrayAdapter adapter = new GroupArrayAdapter(this, R.layout.group_item, groups);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

        TextView textViewSinger = view.findViewById(R.id.template_artist_name);
        String artist = textViewSinger.getText().toString();

        Intent intent = new Intent(this, GroupActivity.class);
        intent.putExtra(KEY_ARTIST, artist);
        startActivity(intent);
    }
}
