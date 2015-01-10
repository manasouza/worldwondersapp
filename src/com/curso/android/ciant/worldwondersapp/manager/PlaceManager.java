package com.curso.android.ciant.worldwondersapp.manager;

import java.util.List;

import android.os.AsyncTask;

import com.curso.android.ciant.worldwondersapp.bussinescordinator.PlaceBusinessCoordinator;
import com.curso.android.ciant.worldwondersapp.entity.Place;
import com.curso.android.ciant.worldwondersapp.listener.IntegratorOperatorCallback;

public class PlaceManager {
	
	 private PlaceBusinessCoordinator mPlaceBusinessCoordinator;

    public PlaceManager() {
        mPlaceBusinessCoordinator = new PlaceBusinessCoordinator();
    }

    public void getAllPlace(final IntegratorOperatorCallback<List<Place>> callback) {

        new AsyncTask<Void, Void, List<Place>>() {

            @Override
            protected List<Place> doInBackground(Void... params) {

                List<Place> placeList = mPlaceBusinessCoordinator.getAllPlace();

                return placeList;
            }

            @Override
            protected void onPostExecute(List<Place> resultList) {
                super.onPostExecute(resultList);

                if (resultList != null) {
                    callback.onOperationCompleteSuccess(resultList);
                } else {
                    callback.onOperationCompleteError();
                }
            }
        }.execute();
    }
}
