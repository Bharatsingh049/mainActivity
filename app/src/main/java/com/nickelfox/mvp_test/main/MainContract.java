package com.nickelfox.mvp_test.main;

import android.support.annotation.NonNull;

import com.nickelfox.mvp_test.BasePresenter;
import com.nickelfox.mvp_test.BaseView;
import com.nickelfox.mvp_test.data.model.Article;
import com.nickelfox.mvp_test.data.model.Model;

import java.util.List;

public interface MainContract  {

    interface View extends BaseView<Presenter>{

        void showBusinessList(@NonNull List<Article> businessList);

        void showEntertainmentList(@NonNull List<Article> entertainmentList);

        void showHealthList(@NonNull List<Article> healthList);

        void showScienceList(@NonNull List<Article> scienceList);

        void showSportsList(@NonNull List<Article> sportsList);

        void showTechnologyList(@NonNull List<Article> technologyList);

        void showLoading();

        void hideLoading();

        void showError(@NonNull String errorMessage);


    }

    interface Presenter extends BasePresenter{

        void getTopHeadings();


    }
}
