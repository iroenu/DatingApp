package com.example.wegua.datingapp.Entity;

import android.support.annotation.NonNull;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Setting {

    @PrimaryKey
    @NonNull
    private String user="";

    @ColumnInfo(name = "displayName")
    private String displayName;

    @ColumnInfo(name = "hour")
    private int hour;

    @ColumnInfo(name = "minute")
    private int minute;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "searchMile")
    private String searchMile;

    @ColumnInfo(name = "privacy")
    private boolean privacy;

    @ColumnInfo(name = "AgeRangeLow")
    private int ageRangeLow;

    @ColumnInfo(name = "AgehRangeHigh")
    private int ageRangeHigh;

    @NonNull
    public String getUser() {
        return user;
    }

    public void setUser(@NonNull String user) {
        this.user = user;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getSearchMile() {
        return searchMile;
    }

    public void setSearchMile(String searchMile) {
        this.searchMile = searchMile;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public int getAgeRangeLow() {
        return ageRangeLow;
    }

    public void setAgeRangeLow(int ageRangeLow) {
        this.ageRangeLow = ageRangeLow;
    }

    public int getAgeRangeHigh() {
        return ageRangeHigh;
    }

    public void setAgeRangeHigh(int ageRangeHigh) {
        this.ageRangeHigh = ageRangeHigh;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
