package com.nickelfox.mvp_test.main;

import android.support.annotation.NonNull;

import com.nickelfox.mvp_test.data.model.Article;
import com.nickelfox.mvp_test.data.source.NewsListDataSource;
import com.nickelfox.mvp_test.data.source.NewsListRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private final NewsListRepository mMainRepository;

    private String[] category = {"business", "entertainment", "health", "science", "sports", "technology"};

    private final MainContract.View mMainView;

    public MainPresenter(NewsListRepository mTasksRepository, MainContract.View mTasksView) {
        this.mMainRepository = mTasksRepository;
        this.mMainView = mTasksView;

        mTasksView.setPresenter(this);
    }




    @Override
    public void getTopHeadings() {

        for (int i=0;i<6;i++){
            getList(category[i],i);
        }

    }

    private void getList(@NonNull String category,final int list_No){
        mMainRepository.fetchList(new NewsListDataSource.LoadNewsCallback() {


            @Override
            public void onTasksLoaded(@NonNull List<Article> newsList, @NonNull int listNo) {

                switch (list_No){
                    case 0: mMainView.showBusinessList(newsList);
                            break;
                    case 1: mMainView.showEntertainmentList(newsList);
                        break;
                    case 2: mMainView.showHealthList(newsList);
                        break;
                    case 3: mMainView.showScienceList(newsList);
                        break;
                    case 4: mMainView.showSportsList(newsList);
                        break;
                    case 5: mMainView.showTechnologyList(newsList);
                            mMainView.hideLoading();
                        break;
                }
            }

            @Override
            public void onDataNotAvailable(@NonNull String errorMessage) {
                mMainView.hideLoading();
                mMainView.showError(errorMessage);
            }

        },category,"In","en",list_No);
        mMainView.showLoading();
    }


    @Override
    public void start() {

    }
}
