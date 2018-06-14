package com.example.wegua.datingapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.wegua.datingapp.Entity.Setting;
import com.example.wegua.datingapp.SettingDao;

@Database(entities = { Setting.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingDao settingDao();
}


