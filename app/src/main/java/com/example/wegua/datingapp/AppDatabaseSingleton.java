package com.example.wegua.datingapp;

import android.arch.persistence.room.Room;
import android.content.Context;

public class AppDatabaseSingleton {
    private static AppDatabase db;

    public static AppDatabase getDatabase(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
            AppDatabase.class, "dating-database").build();
        }
        return db;
    }
}
