package com.example.wegua.datingapp.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Match implements Parcelable{


    public String matchId;
    public String title;
    public String matchImageURL;
    public Boolean like;

    public Match() {

    }

    public Match (String mImageURL,Boolean mLiked,String mTitle,String mId){
        this.matchImageURL = mImageURL;
        this.like = mLiked;
        this.title = mTitle;
        this.matchId = mId;
    }

    public Match(Parcel in) {
        matchImageURL = in.readString();
        like = in.readByte() != 0;
        title = in.readString();
        matchId = in.readString();
    }

    public String getId() {
        return matchId;
    }

    public String getTitle(){
        return title;
    }

    public String getImageURL(){
        return matchImageURL;
    }

    public boolean getLike(){
        return like;
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("imageUrl", matchImageURL);
        result.put("liked", like);
        result.put("name", title);
        result.put("uid", matchId);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(matchImageURL);
        dest.writeByte((byte) (like ? 1 : 0));
        dest.writeString(title);
        dest.writeString(matchId);

    }
}
