package com.curso.android.ciant.worldwondersapp.service.syncer;

import java.util.List;

import com.curso.android.ciant.worldwondersapp.contentprovider.WorldWondersContentProvider;
import com.curso.android.ciant.worldwondersapp.database.table.PlaceTable;
import com.curso.android.ciant.worldwondersapp.entity.Place;
import com.curso.android.ciant.worldwondersapp.infrastructure.Constants;
import com.curso.android.ciant.worldwondersapp.listener.DatabaseOperatorCallback;
import com.curso.android.ciant.worldwondersapp.listener.IntegratorOperatorCallback;
import com.curso.android.ciant.worldwondersapp.manager.DatabaseManager;
import com.curso.android.ciant.worldwondersapp.manager.PlaceManager;
import com.example.worldwondersapp.R;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class PlaceSyncer extends Syncer{

	 private Service mService;
	
	 public PlaceSyncer(final Service service) {
		 this.mService = service;
	 }

	 
	@Override
	public void sync(Intent intent) {
		// TODO Auto-generated method stub
		
		final ResultReceiver receiver = intent.getParcelableExtra(Constants.Service.Tag.RESULT_RECEIVER);
        final int command = intent.getIntExtra(Constants.Service.Tag.COMMAND, 0);
        
        PlaceManager placeManager = new PlaceManager();
        
        switch (command) {
        	case Constants.Service.SyncCommand.PLACE_ALL:
        		placeManager.getAllPlace(new IntegratorOperatorCallback<List<Place>>() {
					
					@Override
					public void onOperationCompleteSuccess(List<Place> result) {
						// TODO Auto-generated method stub
						
                        ContentValues[] contValuesArray = prepareBulkInsertPlace(result);
                        
                        DatabaseManager dbManager = new DatabaseManager(mService);
                        dbManager.bulkInsert(WorldWondersContentProvider.PLACE_CONTENT_URI, contValuesArray, Boolean.TRUE,
                        null, null, new DatabaseOperatorCallback<Integer>() {

                            @Override
                            public void onOperationSuccess(Integer integer) {
                                receiver.send(Constants.Service.Status.FINISHED, null);
                            }
                        });
                        
					}
					
					@Override
					public void onOperationCompleteError() {
						// TODO Auto-generated method stub
						final Bundle bundleExtras = new Bundle();
                        bundleExtras.putString(Constants.Service.Tag.ERROR_MSG, mService.getResources().getString(R.string.msg_error_no_service_available));
                        receiver.send(Constants.Service.Status.ERROR, bundleExtras);
					}
				});
        		break;
        }
        
	}
	
	private ContentValues[] prepareBulkInsertPlace(final List<Place> placeList) {

        ContentValues[] contValuesArray = new ContentValues[placeList.size()];
        int i = 0;

        for (Place place : placeList) {

            ContentValues contValues = new ContentValues();

            contValues.put(PlaceTable.ID, place.getId());
            contValues.put(PlaceTable.PLACE_NAME, place.getPlaceName());
            contValues.put(PlaceTable.PLACE_COUNTRY, place.getPlaceCountry());
            contValues.put(PlaceTable.PLACE_DESCRIPTION, place.getPlaceDescription());
            contValues.put(PlaceTable.PLACE_IMAGE_URL, place.getPlaceImageUrl());

            contValuesArray[i++] = contValues;
        }

        return contValuesArray;
    }

}
