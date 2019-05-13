package com.nickelfox.mvp_test.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM newsarticles")
    List<NewsArticle> getNews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<NewsArticle> newsArticles);

    @Query("DELETE FROM newsarticles")
    int deleteNews();

    @Query("SELECT COUNT(*) FROM newsarticles")
    long count();

}
