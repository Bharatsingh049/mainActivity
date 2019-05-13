package com.nickelfox.mvp_test.data.source.local;

import android.support.annotation.NonNull;

public interface OnDBCallBacks {

    interface OnDeleteCallBack{
        void onRowsDeleted(long deletedRows);
    }

    interface OnRowCountCallBack{
        void getRowCount(long rowCount);
    }

    interface OnDataInsertCallBack{
        void onInserted(long rowCount);
    }
    /*
    void deleteRows(@NonNull OnDeleteCallBack callBack);

    void countRows(@NonNull OnRowCountCallBack callBack);*/



}
