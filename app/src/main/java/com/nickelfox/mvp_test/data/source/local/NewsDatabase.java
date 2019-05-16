package com.nickelfox.mvp_test.data.source.local;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import android.content.Context;

import static com.nickelfox.mvp_test.data.source.local.NewsDatabase.DATABASE_VERSION;


@Database(entities = {NewsArticle.class}, version = DATABASE_VERSION)

public abstract class NewsDatabase extends RoomDatabase {

    static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "News.db";

    private static NewsDatabase INSTANCE;

    public abstract NewsDao newsDao();

    public static synchronized NewsDatabase getInstance(Context context) {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context
                        , NewsDatabase.class, DATABASE_NAME)
                        .build();
            }

        return INSTANCE;
    }

}
