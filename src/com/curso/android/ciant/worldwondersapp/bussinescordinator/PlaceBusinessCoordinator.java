package com.curso.android.ciant.worldwondersapp.bussinescordinator;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.curso.android.ciant.worldwondersapp.entity.Place;
import com.curso.android.ciant.worldwondersapp.integrator.PlaceIntegrator;

public class PlaceBusinessCoordinator {

	private PlaceIntegrator mPlaceIntegrator;
	
    public PlaceBusinessCoordinator() {
        this.mPlaceIntegrator = new PlaceIntegrator();
    }
    
    public List<Place> getAllPlace() {

        List<Place> placeList = new ArrayList<Place>();

        String allPlaceJsonStr = this.mPlaceIntegrator.getAllPlace();

        if (allPlaceJsonStr != null && !allPlaceJsonStr.isEmpty()) {

            try {

                JSONObject jsonObj = new JSONObject(allPlaceJsonStr);
                JSONArray jsonArray = jsonObj.getJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++) {

                    Place place = new Place(jsonArray.getJSONObject(i));

                    placeList.add(place);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return placeList;
    }
	
}
