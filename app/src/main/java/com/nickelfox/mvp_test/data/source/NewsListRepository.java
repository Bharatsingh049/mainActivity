package com.nickelfox.mvp_test.data.source;

import androidx.annotation.NonNull;
import android.util.Log;

import com.nickelfox.mvp_test.data.source.local.NewsListLocalRepository;
import com.nickelfox.mvp_test.data.source.local.OnDBCallBacks;
import com.nickelfox.mvp_test.data.source.remote.NewsListRemoteRepository;
import com.nickelfox.mvp_test.data.source.remote.model.SamachaarModel;

import java.util.List;

public class NewsListRepository implements NewsListDataSource {


    private static NewsListRepository INSTANCE;

    private final NewsListRemoteRepository mNewsRemoteDataRepository;

    private final NewsListLocalRepository mNewsLocalDataRepository;

    //private final String[] categories = {"business", "entertainment", "health", "science", "sports", "technology"};

    private static long sRowCount, sDeletedRows;


    private NewsListRepository(NewsListRemoteRepository mTasksRemoteDataRepository, NewsListLocalRepository newsListLocalRepository) {
        this.mNewsRemoteDataRepository = mTasksRemoteDataRepository;
        this.mNewsLocalDataRepository = newsListLocalRepository;
        /*mNewsLocalDataRepository.deleteList(new OnDBCallBacks() {
            @Override
            public void onRowsDeleted(long deletedRows) {
                NewsListRepository.sDeletedRows = deletedRows;
                Log.d( "onRowsDeleted: ",deletedRows+"");
            }

            @Override
            public void getRowCount(long rowCount) {
                NewsListRepository.sRowCount = rowCount;
                Log.d( "onRowsDeleted: ",rowCount+"");
            }
        });*/
    }

    public static NewsListRepository getInstance(NewsListRemoteRepository newsRemoteDataSource, NewsListLocalRepository newsListLocalRepository) {
        if (INSTANCE == null) {
            INSTANCE = new NewsListRepository(newsRemoteDataSource, newsListLocalRepository);
        }

        return INSTANCE;
    }


    @Override
    public void fetchList(@NonNull final LoadNewsCallback callback, final String category, final String country, final String language, int listNo) {
        getRowCount(new OnDBCallBacks.OnRowCountCallBack() {
            @Override
            public void getRowCount(long rowCount) {
                if(rowCount>0){
                    getListFromDB(callback, language, country);
                }
            }
        });

        mNewsRemoteDataRepository.fetchList(new LoadNewsCallback() {
            @Override
            public void onTasksLoaded(@NonNull final List<SamachaarModel> newsList, final int listNo) {

                    switch (listNo) {
                        case 0:
                            mNewsLocalDataRepository.setList(newsList, category);
                            callback.onTasksLoaded(newsList,listNo);
                            break;
                        case 1:
                            mNewsLocalDataRepository.setList(newsList, category);
                            callback.onTasksLoaded(newsList,listNo);
                            break;
                        case 2:
                            mNewsLocalDataRepository.setList(newsList, category);
                            callback.onTasksLoaded(newsList,listNo);
                            break;
                        case 3:
                            mNewsLocalDataRepository.setList(newsList, category);
                            callback.onTasksLoaded(newsList,listNo);
                            break;
                        case 4:
                            mNewsLocalDataRepository.setList(newsList, category);
                            callback.onTasksLoaded(newsList,listNo);
                            break;
                        case 5:
                            mNewsLocalDataRepository.setList(newsList, category);
                            callback.onTasksLoaded(newsList,listNo);
                            deleteRows();
                            break;

                    }


            }


            @Override
            public void onDataNotAvailable(@NonNull String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
                mNewsLocalDataRepository.fetchList(new LoadNewsCallback() {
                    @Override
                    public void onTasksLoaded(@NonNull List<SamachaarModel> newsList, int listNo) {

                        callback.onTasksLoaded(newsList, listNo);

                    }

                    @Override
                    public void onDataNotAvailable(@NonNull String errorMessage) {
                        callback.onDataNotAvailable(errorMessage);
                    }
                }, category, country, language, 1);

            }
        }, category, country, language, listNo);

    }

    private void getListFromDB(@NonNull final LoadNewsCallback callback, String language, String country) {
        mNewsLocalDataRepository.fetchList(new LoadNewsCallback() {
            @Override
            public void onTasksLoaded(@NonNull List<SamachaarModel> newsList, int listNo) {

                callback.onTasksLoaded(newsList, listNo);

            }

            @Override
            public void onDataNotAvailable(@NonNull String errorMessage) {
                callback.onDataNotAvailable(errorMessage);
            }
        }, "", country, language, 1);
    }

    private void deleteRows() {
        mNewsLocalDataRepository.deleteList(new OnDBCallBacks.OnDeleteCallBack() {
            @Override
            public void onRowsDeleted(long deletedRows) {
                NewsListRepository.sDeletedRows = deletedRows;
                Log.d("onRowsDeleted: ", deletedRows + "");
                if (deletedRows>0){
                    insertRows();
                }
            }
        });
    }

    private void insertRows(){
        mNewsLocalDataRepository.insertList(new OnDBCallBacks.OnDataInsertCallBack() {
            @Override
            public void onInserted(long rowCount) {
                if (rowCount>0){
                    Log.i( "onInserted: ","data was inserted "+rowCount);
                }
            }
        });
    }

    private void getRowCount(@NonNull final OnDBCallBacks.OnRowCountCallBack callBack) {
        mNewsLocalDataRepository.getRowCount(new OnDBCallBacks.OnRowCountCallBack() {

            @Override
            public void getRowCount(long rowCount) {
                NewsListRepository.sRowCount = rowCount;
                callBack.getRowCount(rowCount);
                Log.d("onRowsDeleted: ", rowCount + "");
            }
        });

    }


}
