package com.curso.android.ciant.worldwondersapp.manager;


import android.content.Context;
import android.net.Uri;
import android.content.CursorLoader;


public class DatabaseManager {

    private Context mContext;

    public DatabaseManager(final Context context) {
        this.mContext = context;
    }

    public CursorLoader query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs,
                              final String sortOrder) {

        CursorLoader cursorLoader = new CursorLoader(mContext, uri,
                projection, selection, selectionArgs,
                sortOrder);

        return cursorLoader;
    }

}