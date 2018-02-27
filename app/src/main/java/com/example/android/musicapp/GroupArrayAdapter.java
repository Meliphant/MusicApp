package com.example.android.musicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Meliphant on 25.02.2018.
 */

public class GroupArrayAdapter extends ArrayAdapter<Group> {

    public GroupArrayAdapter(Activity activity, int res, ArrayList<Group> groups){
        super(activity,0, groups);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View currentListView = convertView;
        if (currentListView == null) {
            currentListView = LayoutInflater.from(getContext()).inflate(R.layout.group_item, parent, false);
        }
        Group currentGroup = getItem(position);

        TextView currentArtist = (TextView) currentListView.findViewById(R.id.template_artist_name);
        currentArtist.setText(currentGroup.getGroupName());

        ImageView currentImage = (ImageView) currentListView.findViewById(R.id.template_logo);
        currentImage.setImageResource(currentGroup.getImageId());
        return currentListView;
    }
}
