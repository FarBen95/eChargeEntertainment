package com.farben.echargeentertainment.db.dao;

import com.farben.echargeentertainment.db.entity.WebsiteEntity;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface WebsiteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(WebsiteEntity website);

    @Delete
    void delete(WebsiteEntity website);

    @Query("DELETE FROM website_table")
    void deleteAll();

    @Query("SELECT * FROM website_table WHERE id = :websiteId")
    LiveData<WebsiteEntity> loadWebsite(int websiteId);

    @Query("SELECT * FROM website_table")
    LiveData<List<WebsiteEntity>> loadAllWebsites();
}
