package com.nickelfox.mvp_test.data.source;

import android.support.annotation.NonNull;

import com.nickelfox.mvp_test.data.model.Article;
import com.nickelfox.mvp_test.data.source.remote.NewsListRemoteRepository;

import java.util.List;

public class NewsListRepository implements NewsListDataSource {


    private static NewsListRepository INSTANCE;

    private final NewsListDataSource mTasksRemoteDataSource;

    public NewsListRepository(NewsListRemoteRepository mTasksRemoteDataSource) {
        this.mTasksRemoteDataSource = mTasksRemoteDataSource;
    }

    public static NewsListRepository getInstance(NewsListRemoteRepository newsRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new NewsListRepository(newsRemoteDataSource);
        }
        return INSTANCE;
    }



    @Override
    public void fetchList(@NonNull final LoadNewsCallback callback, String category, String country, String language, @NonNull int listNo) {
        mTasksRemoteDataSource.fetchList(new LoadNewsCallback() {

            @Override
            public void onTasksLoaded(@NonNull List<Article> newsList, @NonNull int listNo) { callback.onTasksLoaded(newsList,listNo); }

            @Override
            public void onDataNotAvailable(@NonNull String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }


        },category,country,language,listNo);
    }
}
