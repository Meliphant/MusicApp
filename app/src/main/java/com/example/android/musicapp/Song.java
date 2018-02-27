package com.example.android.musicapp;

/**
 * Created by Meliphant on 25.02.2018.
 */

// Class for a song, has song name and its artist.
public class Song {

    private String mName;
    private String mArtist;
    private String mNumber;

    public Song (String name, String artist, String number){
        mName = name;
        mArtist = artist;
        mNumber = number;
    }
    public String getName (){
        return mName;
    }
    public String getArtist(){
        return mArtist;
    }
    public String getNumber(){
        return mNumber;
    }
}
