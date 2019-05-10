package com.nickelfox.mvp_test;

import com.nickelfox.mvp_test.main.MainContract;

public interface BasePresenter {

    void start(MainContract.View view);

    void onDestroy();
}
