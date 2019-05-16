package com.nickelfox.mvp_test.main;

import androidx.annotation.NonNull;

import com.nickelfox.mvp_test.BasePresenter;
import com.nickelfox.mvp_test.BaseView;
import com.nickelfox.mvp_test.data.source.remote.model.SamachaarModel;

import java.util.List;

public interface MainContract {

    interface View extends BaseView<Presenter> {

        void showBusinessList(@NonNull List<SamachaarModel> businessList);

        void showEntertainmentList(@NonNull List<SamachaarModel> entertainmentList);

        void showHealthList(@NonNull List<SamachaarModel> healthList);

        void showScienceList(@NonNull List<SamachaarModel> scienceList);

        void showSportsList(@NonNull List<SamachaarModel> sportsList);

        void showTechnologyList(@NonNull List<SamachaarModel> technologyList);

        void showLoading();

        void hideLoading();

        void showError(@NonNull String errorMessage);


    }

    interface Presenter extends BasePresenter {

        void getTopHeadings();


    }
}
