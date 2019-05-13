package com.nickelfox.mvp_test.data.source.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "newsarticles")
public class NewsArticle {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "newsid")
    private int id;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "urltoimage")
    private String urlToImage;

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    @ColumnInfo(name = "category")
    private String category;

    @NonNull
    @ColumnInfo(name = "url")
    private String url;

    public NewsArticle() {
    }

    public NewsArticle(@NonNull String title, @NonNull String description, @NonNull String urlToImage, @NonNull String category, @NonNull String url) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.category = category;
        this.url = url;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public void setUrlToImage(@NonNull String urlToImage) {
        this.urlToImage = urlToImage;
    }

    @NonNull
    public String getCategory() {
        return category;
    }

    public void setCategory(@NonNull String category) {
        this.category = category;
    }

    @NonNull
    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getUrlToImage() {
        return urlToImage;
    }
}
