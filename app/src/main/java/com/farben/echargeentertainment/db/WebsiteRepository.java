package com.farben.echargeentertainment.db;

import android.app.Application;

import com.farben.echargeentertainment.db.dao.WebsiteDao;
import com.farben.echargeentertainment.db.database.WebsiteRoomDatabase;
import com.farben.echargeentertainment.db.entity.WebsiteEntity;

import java.util.List;

import androidx.lifecycle.LiveData;

public class WebsiteRepository {

    private WebsiteDao mWebsiteDao;
    private LiveData<List<WebsiteEntity>> mAllWebsites;

    public WebsiteRepository(Application application) {
        WebsiteRoomDatabase db = WebsiteRoomDatabase.getDatabase(application);
        mWebsiteDao = db.websiteDao();
        mAllWebsites = mWebsiteDao.loadAllWebsites();
    }

//    public static WebsiteRepository getInstance(final Application database) {
//        if (sInstance == null) {
//            synchronized (DataRepository.class) {
//                if (sInstance == null) {
//                    sInstance = new DataRepository(database);
//                }
//            }
//        }
//        return sInstance;
//    }

    public LiveData<List<WebsiteEntity>> getAllWebsites() {
        return mAllWebsites;
    }

    public void insert(WebsiteEntity websiteEntity) {
        WebsiteRoomDatabase.databaseWriteExecutor.execute(() -> {
            mWebsiteDao.insert(websiteEntity);
        });
    }
}
