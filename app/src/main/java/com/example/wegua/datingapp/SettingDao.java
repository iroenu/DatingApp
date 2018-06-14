package com.example.wegua.datingapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.wegua.datingapp.Entity.Setting;

import java.util.List;

@Dao
public interface SettingDao {
    @Query("SELECT * FROM Setting")
    List<Setting> getAll();

    @Query("SELECT * FROM Setting WHERE user = :mUser")
    List<Setting> loadAllByIds(String mUser);

    @Update
    void updateSettings(Setting... settings);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Setting... settings);

    @Delete
    void delete(Setting setting);

}
