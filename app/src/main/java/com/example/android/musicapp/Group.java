package com.example.android.musicapp;

/**
 * Created by Meliphant on 25.02.2018.
 */

public class Group {
    private String mName;
    private int mImageId;

    public Group(String name, int id){
        mName = name;
        mImageId = id;
    }

    public String getGroupName(){
        return mName;
    }

    public int getImageId(){
        return mImageId;
    }
}
