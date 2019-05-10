package com.nickelfox.mvp_test.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomMasterTable;
import android.content.Context;
import android.support.constraint.ConstraintLayout;

import static com.nickelfox.mvp_test.data.source.local.NewsDatabase.DATABASE_VERSION;
import static com.nickelfox.mvp_test.data.source.local.NewsDatabase.DATABASE_NAME;


@Database(entities = {NewsArticle.class}, version = DATABASE_VERSION)

public abstract class NewsDatabase extends RoomDatabase {

    static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "News.db";

    private static NewsDatabase INSTANCE;

    public abstract NewsDao newsDao();

    private static final Object sLock = new Object();

    public static NewsDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context
                        , NewsDatabase.class, DATABASE_NAME)
                        .build();
            }
        }
        return INSTANCE;
    }

}
