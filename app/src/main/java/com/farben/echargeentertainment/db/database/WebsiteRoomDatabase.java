package com.farben.echargeentertainment.db.database;

import android.content.Context;

import com.farben.echargeentertainment.db.dao.WebsiteDao;
import com.farben.echargeentertainment.db.entity.WebsiteEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = WebsiteEntity.class, version = 1, exportSchema = false)
public abstract class WebsiteRoomDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile WebsiteRoomDatabase INSTANCE;

    static WebsiteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WebsiteRoomDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WebsiteRoomDatabase.class, "website_database")
                        .build();
            }
        }
        return INSTANCE;
    }

    public abstract WebsiteDao websiteDao();
}
