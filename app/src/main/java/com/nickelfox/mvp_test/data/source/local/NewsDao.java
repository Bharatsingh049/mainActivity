package com.nickelfox.mvp_test.data.source.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
