package com.nickelfox.mvp_test.main;

import com.nickelfox.mvp_test.data.model.Article;
import com.nickelfox.mvp_test.data.source.NewsListDataSource;
import com.nickelfox.mvp_test.data.source.NewsListRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private final NewsListRepository mTasksRepository;

    private final MainContract.View mTasksView;

    public MainPresenter(NewsListRepository mTasksRepository, MainContract.View mTasksView) {
        this.mTasksRepository = mTasksRepository;
        this.mTasksView = mTasksView;
    }




    @Override
    public void getTopHeadings() {
        mTasksRepository.fetchList(new NewsListDataSource.LoadNewsCallback() {
            @Override
            public void onTasksLoaded(List<Article> newsList) {
                mTasksView.showList(newsList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        },"business","In","en");
    }

    @Override
    public void start() {

    }
}
