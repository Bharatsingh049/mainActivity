package com.nickelfox.mvp_test.data.source.local;

import android.support.annotation.NonNull;

import com.nickelfox.mvp_test.data.source.NewsListDataSource;
import com.nickelfox.mvp_test.data.source.remote.NewsListRemoteRepository;

public class NewsListLocalRepository implements NewsListDataSource {



    private NewsDao newsDao;

    private static NewsListLocalRepository INSTANCE;

    private NewsListLocalRepository(@NonNull NewsDao newsDao){
        this.newsDao=newsDao;
    }


    public static NewsListLocalRepository getInstance(@NonNull NewsDao newsDao) {
        if (INSTANCE == null) {
            INSTANCE = new NewsListLocalRepository(newsDao);
        }
        return INSTANCE;
    }


    @Override
    public void fetchList(@NonNull LoadNewsCallback callback, String category, String country, String language, int listNo) {



    }




}
