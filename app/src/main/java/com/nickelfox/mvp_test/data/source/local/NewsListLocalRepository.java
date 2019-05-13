package com.nickelfox.mvp_test.data.source.local;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.nickelfox.mvp_test.data.source.NewsListDataSource;
import com.nickelfox.mvp_test.data.source.remote.model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NewsListLocalRepository implements NewsListDataSource {

    private final String[] categories = {"business", "entertainment", "health", "science", "sports", "technology"};

    private NewsDao newsDao;

    private static List<NewsArticle> newsArticleList;

    private static NewsListLocalRepository INSTANCE;


    private NewsListLocalRepository(@NonNull NewsDao newsDao) {
        this.newsDao = newsDao;
        newsArticleList = new ArrayList<>();
    }


    public static NewsListLocalRepository getInstance(@NonNull NewsDao newsDao) {
        if (INSTANCE == null) {
            INSTANCE = new NewsListLocalRepository(newsDao);
        }

        return INSTANCE;
    }


    @Override
    public void fetchList(@NonNull final LoadNewsCallback callback, String category, String country, String language, int listNo) {
        Executor fetchingExecutor = Executors.newSingleThreadExecutor();
        Runnable fetchingRunnable = new Runnable() {
            @Override
            public void run() {
                List<NewsArticle> newsArticleList = newsDao.getNews();
                List<Article> articleList = new ArrayList<>();
                for (int i = 0; i < 6; i++) {
                    for (NewsArticle newsArticle : newsArticleList) {
                        if (TextUtils.equals(newsArticle.getCategory(), categories[i])) {
                            Article article = new Article();
                            article.setTitle(newsArticle.getTitle());
                            article.setDescription(newsArticle.getDescription());
                            article.setUrlToImage(newsArticle.getUrlToImage());
                            article.setUrl(newsArticle.getUrl());
                            articleList.add(article);
                        }
                    }
                    callback.onTasksLoaded(articleList, i);

                }
            }
        };
        fetchingExecutor.execute(fetchingRunnable);


    }


    public void insertList(@NonNull final OnDBCallBacks.OnDataInsertCallBack callBack) {
        Executor insertExecutor = Executors.newSingleThreadExecutor();
        Runnable insertRunnable = new Runnable() {
            @Override
            public void run() {
                long[] temp=newsDao.insert(newsArticleList);
                callBack.onInserted(temp.length);
            }
        };

        insertExecutor.execute(insertRunnable);
    }

    public void setList(List<Article> list, String category) {

        //final List<NewsArticle> newsArticleList = new ArrayList<>();
        for (Article article : list) {
            String title = article.getTitle();
            String description = article.getDescription();
            String urlToImage = article.getUrlToImage();
            String url = article.getUrl();
            if (TextUtils.isEmpty(article.getDescription())) {
                description = "N/A";
            }
            if (TextUtils.isEmpty(article.getTitle())) {
                title = "N/A";
            }
            if (TextUtils.isEmpty(article.getUrlToImage())) {
                urlToImage = "N/A";
            }
            if (TextUtils.isEmpty(article.getUrl())) {
                url = "N/A";
            }
            NewsArticle newsArticle = new NewsArticle(title, description, urlToImage, category, url);
            newsArticleList.add(newsArticle);


        }


    }

    public void getRowCount(@NonNull final OnDBCallBacks.OnRowCountCallBack callBack) {
        Runnable countRunnable = new Runnable() {
            @Override
            public void run() {
                long count = newsDao.count();
                callBack.getRowCount(count);
                Log.d("run: ", count + " ");
            }
        };
        Executor countExecutors = Executors.newSingleThreadExecutor();
        countExecutors.execute(countRunnable);
    }


    public void deleteList(@NonNull final OnDBCallBacks.OnDeleteCallBack callBacks) {
        Runnable deleteRunnable = new Runnable() {
            @Override
            public void run() {
                //long count= newsDao.count();
                //callBacks.getRowCount(count);
                //Log.d( "run: ",count+" ");
                //if (count>0) {
                int temp = newsDao.deleteNews();
                callBacks.onRowsDeleted(temp);
                Log.d("deleteList: ", temp + "");
                //}
            }
        };
        Executor deleteExecutors = Executors.newSingleThreadExecutor();
        deleteExecutors.execute(deleteRunnable);

        /*Thread deleteThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int temp = newsDao.deleteNews();
                Log.d("deleteList: ",temp+"");

            }
        });
        deleteThread.run();*/

    }


}
