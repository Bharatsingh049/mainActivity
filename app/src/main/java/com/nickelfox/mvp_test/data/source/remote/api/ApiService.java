package com.nickelfox.mvp_test.data.source.remote.api;

import com.nickelfox.mvp_test.data.source.remote.model.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.nickelfox.mvp_test.data.source.remote.api.ApiRoutes.TOP_HEADLINES;

public interface ApiService {
    @GET(TOP_HEADLINES)
    Call<Model> getModelList(@Query("category") String category, @Query("apiKey") String apiKey, @Query("country") String country, @Query("language") String language);
}
