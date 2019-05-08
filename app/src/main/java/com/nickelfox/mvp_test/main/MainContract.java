package com.nickelfox.mvp_test.main;

import com.nickelfox.mvp_test.BasePresenter;
import com.nickelfox.mvp_test.BaseView;
import com.nickelfox.mvp_test.data.model.Model;

import java.util.List;

public interface MainContract  {

    interface View extends BaseView<Presenter>{

        void showList(List<Model> list);

        void showLoading();

        void hideLoading();



    }

    interface Presenter extends BasePresenter{

        void getTopHeadings();


    }
}
