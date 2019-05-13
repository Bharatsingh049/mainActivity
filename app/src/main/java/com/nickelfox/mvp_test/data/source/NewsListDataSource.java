package com.nickelfox.mvp_test.data.source;

import android.support.annotation.NonNull;

import com.nickelfox.mvp_test.data.source.remote.model.Article;

import java.util.List;

public interface NewsListDataSource {
    interface LoadNewsCallback {

        void onTasksLoaded(@NonNull List<Article> newsList, int listNo);

        void onDataNotAvailable(@NonNull String errorMessage);
    }



    void fetchList(@NonNull LoadNewsCallback callback, String category, String country, String language, int listNo);




}
