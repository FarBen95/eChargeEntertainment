package com.farben.echargeentertainment.viewmodel;

import android.app.Application;

import com.farben.echargeentertainment.db.WebsiteRepository;
import com.farben.echargeentertainment.db.entity.WebsiteEntity;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MainViewModel extends AndroidViewModel {

    private WebsiteRepository mRepository;
    private LiveData<List<WebsiteEntity>> mWebsiteList;

    public MainViewModel(Application application) {
        super(application);
        mRepository = new WebsiteRepository(application);
        mWebsiteList = mRepository.getAllWebsites();
    }

    public void insert(WebsiteEntity website) {
        mRepository.insert(website);
    }

    public LiveData<List<WebsiteEntity>> getWebsiteList() {
        return mWebsiteList;
    }
}