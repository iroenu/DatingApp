package com.example.wegua.datingapp.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Match implements Parcelable{


    public String uid;
    public String name;
    public String imageUrl;
    public Boolean liked;

    public Match() {

    }

    public Match (String mImageURL,Boolean mLiked,String mTitle,String mId){
        this.imageUrl = mImageURL;
        this.liked = mLiked;
        this.name = mTitle;
        this.uid = mId;
    }

    public Match(Parcel in) {
        imageUrl = in.readString();
        liked = in.readByte() != 0;
        name = in.readString();
        uid = in.readString();
    }

    public String getUid(){
        return uid;
    }

    public String getName(){
        return name;
    }

    public String getImageUrl(){
        return imageUrl;
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
        result.put("imageUrl", imageUrl);
        result.put("liked", liked);
        result.put("name", name);
        result.put("uid", uid);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeByte((byte) (liked ? 1 : 0));
        dest.writeString(name);
        dest.writeString(uid);

    }
}
