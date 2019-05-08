package com.nickelfox.mvp_test.data.source.remote;

import android.support.annotation.NonNull;

import com.nickelfox.mvp_test.data.model.Model;
import com.nickelfox.mvp_test.data.source.NewsListDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NewsListRemoteSource implements NewsListDataSource {
    private static final String sKey = "40ab81231e4c43fd925cdc3a0d08f7f6";

    private static final String sUrl = "https://newsapi.org/v2/";

    public static NewsService newsService = null;



    public static NewsService getService(){

        if (newsService==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(sUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);
        }
        return newsService;
    }



    @Override
    public void fetchList(@NonNull final LoadNewsCallback callback, String category, String country, String language) {
        final Call<Model> modelCall = NewsListRemoteSource.getService().getModelList(category, sKey, country, language);
        modelCall.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                Model model = response.body();
                callback.onTasksLoaded(model.getArticles());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                return;
            }
        });

    }

    public interface  NewsService{
        @GET("top-headlines")
        Call<Model> getModelList(@Query("category") String category, @Query("apiKey") String apiKey, @Query("country") String country, @Query("language") String language);
    }
}
