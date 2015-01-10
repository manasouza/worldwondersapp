package com.curso.android.ciant.worldwondersapp.manager;


import com.curso.android.ciant.worldwondersapp.listener.DatabaseOperatorCallback;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
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
    
    
    public void bulkInsert(final Uri uri, final ContentValues[] contValues, final boolean deleteBeforeInsert,
            final String deleteSelection, final String[] deleteSelectionArgs,
            final DatabaseOperatorCallback<Integer> callback) {

			AsyncTask<Void, Void, Integer> dbTask = new AsyncTask<Void, Void, Integer>() {
			
				@Override
				protected Integer doInBackground(Void... params) {
				
					ContentResolver contResolver = mContext.getContentResolver();
				
					 if (deleteBeforeInsert) {
					     contResolver.delete(uri, deleteSelection, deleteSelectionArgs);
					 }
				
					 int bulkInsertCount = contResolver.bulkInsert(uri, contValues);
				
					 return bulkInsertCount;
				}
				
				@Override
				protected void onPostExecute(Integer bulkInsertCount) {				
					 if (callback != null) {
					     callback.onOperationSuccess(bulkInsertCount);
					 }
				}
			};
			
			dbTask.execute();
	}
}