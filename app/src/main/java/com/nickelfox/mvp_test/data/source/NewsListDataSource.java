package com.nickelfox.mvp_test.data.source;

import com.nickelfox.mvp_test.data.model.Model;

import java.util.List;

public interface NewsListDataSource {

    void fetchList(String category, String country, String language);

}
