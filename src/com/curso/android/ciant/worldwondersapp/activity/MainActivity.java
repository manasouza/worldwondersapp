package com.curso.android.ciant.worldwondersapp.activity;

import com.curso.android.ciant.worldwondersapp.adapter.FeedCursorAdapter;
import com.curso.android.ciant.worldwondersapp.contentprovider.WorldWondersContentProvider;
import com.curso.android.ciant.worldwondersapp.database.table.PlaceTable;
import com.curso.android.ciant.worldwondersapp.integrator.UserSharedPreferences;
import com.curso.android.ciant.worldwondersapp.manager.DatabaseManager;
import com.example.worldwondersapp.R;

import android.app.Activity;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.app.LoaderManager;


public class MainActivity extends Activity {

	private UserSharedPreferences mUserSharedPreferences;
	private ListView mListViewFeed;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mUserSharedPreferences = new UserSharedPreferences(this);
        if (!mUserSharedPreferences.isUserLogged()){
        	logout();
        }
        
        mListViewFeed = (ListView) findViewById(R.id.list_view_feed);
        final FeedCursorAdapter feedCursorAdapter = new FeedCursorAdapter(this, null);
        mListViewFeed.setAdapter(feedCursorAdapter);
        
        final DatabaseManager dbManager = new DatabaseManager(this);
        
        getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Cursor>() {
        	 @Override
             public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                 CursorLoader cursorLoader = dbManager.query(WorldWondersContentProvider.PLACE_CONTENT_URI, PlaceTable.ALL_COLUMNS, null, null, null);
                 return cursorLoader;
             }

             @Override
             public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                 feedCursorAdapter.swapCursor(data);
             }

             @Override
             public void onLoaderReset(Loader<Cursor> loader) {
                 feedCursorAdapter.swapCursor(null);
             }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private void logout() {
        mUserSharedPreferences.logoutUser();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
