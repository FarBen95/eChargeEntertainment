package com.farben.echargeentertainment.db.database;

import android.content.Context;

import com.farben.echargeentertainment.db.dao.WebsiteDao;
import com.farben.echargeentertainment.db.entity.WebsiteEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = WebsiteEntity.class, version = 1, exportSchema = false)
public abstract class WebsiteRoomDatabase extends RoomDatabase {

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static volatile WebsiteRoomDatabase INSTANCE;

    public static WebsiteRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WebsiteRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WebsiteRoomDatabase.class, "website_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WebsiteDao websiteDao();

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            databaseWriteExecutor.execute(() -> {
                WebsiteDao dao = INSTANCE.websiteDao();

                WebsiteEntity websiteEntity = new WebsiteEntity();
                websiteEntity.setLink("google.com");
                dao.insert(websiteEntity);

                websiteEntity = new WebsiteEntity();
                websiteEntity.setLink("facebook.com");
                dao.insert(websiteEntity);

                websiteEntity = new WebsiteEntity();
                websiteEntity.setLink("twitter.com");
                dao.insert(websiteEntity);
            });
        }
    };
}
