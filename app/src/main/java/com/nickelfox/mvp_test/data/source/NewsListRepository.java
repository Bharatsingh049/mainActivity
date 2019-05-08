package com.nickelfox.mvp_test.data.source;

import android.support.annotation.NonNull;

import com.nickelfox.mvp_test.data.model.Article;

import java.util.List;

public class NewsListRepository implements NewsListDataSource {


    private static NewsListRepository INSTANCE;

    private final NewsListDataSource mTasksRemoteDataSource;

    public NewsListRepository(NewsListDataSource mTasksRemoteDataSource) {
        this.mTasksRemoteDataSource = mTasksRemoteDataSource;
    }

    public static NewsListRepository getInstance(NewsListDataSource newsRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new NewsListRepository(newsRemoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void fetchList(@NonNull LoadNewsCallback callback, String category, String country, String language) {
         mTasksRemoteDataSource.fetchList(new LoadNewsCallback() {
             @Override
             public void onTasksLoaded(List<Article> newsList) {

             }

             @Override
             public void onDataNotAvailable() {

             }
         },category,country,language);
    }
}
