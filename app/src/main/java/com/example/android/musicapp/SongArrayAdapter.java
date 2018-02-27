package com.example.android.musicapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Meliphant on 25.02.2018.
 */

public class SongArrayAdapter extends ArrayAdapter<Song>{

    public SongArrayAdapter(Activity activity, ArrayList<Song> songs){
        super(activity, 0, songs);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View currentListView = convertView;
        if (currentListView == null) {
            currentListView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Song currentSong = getItem(position);

        TextView currentName = (TextView) currentListView.findViewById(R.id.template_song_name);
        currentName.setText(currentSong.getName());

        TextView currentArtist = (TextView) currentListView.findViewById(R.id.template_artist_name);
        currentArtist.setText(currentSong.getArtist());

        TextView currentNumber = (TextView) currentListView.findViewById(R.id.template_number);
        currentNumber.setText(currentSong.getNumber());

        return currentListView;
    }
}
