package com.nickelfox.mvp_test.data.source;

import android.support.annotation.NonNull;

import com.nickelfox.mvp_test.data.source.local.NewsListLocalRepository;
import com.nickelfox.mvp_test.data.source.remote.NewsListRemoteRepository;

public class NewsListRepository implements NewsListDataSource {


    private static NewsListRepository INSTANCE;

    private final NewsListDataSource mTasksRemoteDataRepository;

    private final NewsListDataSource mTasksLocalDataRepository;


    private NewsListRepository(NewsListRemoteRepository mTasksRemoteDataRepository,NewsListLocalRepository newsListLocalRepository) {
        this.mTasksRemoteDataRepository = mTasksRemoteDataRepository;
        this.mTasksLocalDataRepository = this.mTasksRemoteDataRepository;
    }

    public static NewsListRepository getInstance(NewsListRemoteRepository newsRemoteDataSource, NewsListLocalRepository newsListLocalRepository) {
        if (INSTANCE == null) {
            INSTANCE = new NewsListRepository(newsRemoteDataSource,newsListLocalRepository);
        }
        return INSTANCE;
    }



    @Override
    public void fetchList(@NonNull LoadNewsCallback callback, String category, String country, String language, int listNo) {
        mTasksRemoteDataRepository.fetchList(callback,category,country,language,listNo);
    }


}
