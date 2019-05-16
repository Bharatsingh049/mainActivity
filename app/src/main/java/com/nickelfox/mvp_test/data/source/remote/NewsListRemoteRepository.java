package com.nickelfox.mvp_test.data.source.remote;

import androidx.annotation.NonNull;
import android.util.Log;

import com.nickelfox.mvp_test.data.source.remote.model.Model;
import com.nickelfox.mvp_test.data.source.NewsListDataSource;
import com.nickelfox.mvp_test.data.source.remote.api.ApiClient;
import com.nickelfox.mvp_test.data.source.remote.api.ApiService;

import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListRemoteRepository implements NewsListDataSource {
    private ApiService newsService = ApiClient.getService().create(ApiService.class);

    private static NewsListRemoteRepository INSTANCE;



    public static NewsListRemoteRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NewsListRemoteRepository();
        }
        return INSTANCE;
    }

    @Override
    public void fetchList(@NonNull final LoadNewsCallback callback, String category, String country, String language, final int listNo) {
        final Call<Model> modelCall = newsService.getModelList(category, ApiClient.sKey, country, language);
        modelCall.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {

                Model model = response.body();
                //Log.e( "onResponse: ",model.getSamachaars().size() );

                if (model != null) {
                    callback.onTasksLoaded(model.getSamachaars(),listNo);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                Log.e("onFailure: ", "error Occurred", t);
                if (t instanceof UnknownHostException){
                    callback.onDataNotAvailable("No Internet");
                }else {
                    callback.onDataNotAvailable(t.getLocalizedMessage());
                }


            }
        });
    }




    //API Interface file

}
