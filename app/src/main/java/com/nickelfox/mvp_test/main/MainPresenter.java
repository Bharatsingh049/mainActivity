package com.nickelfox.mvp_test.main;

import com.nickelfox.mvp_test.data.model.Article;
import com.nickelfox.mvp_test.data.source.NewsListDataSource;
import com.nickelfox.mvp_test.data.source.NewsListRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private final NewsListRepository mMainRepository;

    private final MainContract.View mMainView;

    public MainPresenter(NewsListRepository mTasksRepository, MainContract.View mTasksView) {
        this.mMainRepository = mTasksRepository;
        this.mMainView = mTasksView;

        mTasksView.setPresenter(this);
    }




    @Override
    public void getTopHeadings() {
        mMainRepository.fetchList(new NewsListDataSource.LoadNewsCallback() {
            @Override
            public void onTasksLoaded(List<Article> newsList) {
                mMainView.showList(newsList);
                mMainView.hideLoading();
            }

            @Override
            public void onDataNotAvailable() {

            }
        },"business","In","en");
        mMainView.showLoading();

    }

    @Override
    public void start() {

    }
}
