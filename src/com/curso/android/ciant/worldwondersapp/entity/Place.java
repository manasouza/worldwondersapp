package com.curso.android.ciant.worldwondersapp.entity;

import org.json.JSONObject;

import com.curso.android.ciant.worldwondersapp.database.table.PlaceTable;

import android.database.Cursor;

public class Place extends BaseEntity{

    private Integer id;
    private String placeName;
    private String placeCountry;
    private String placeDescription;
    private String placeImageUrl;
    
    public Place(final JSONObject jsonObject) {
        setId(this.getInteger(jsonObject, "id"));
    	setPlaceName(this.getString(jsonObject, "name"));
    	setPlaceCountry(this.getString(jsonObject, "country"));
    	setPlaceDescription(this.getString(jsonObject, "description"));
    	setPlaceImageUrl(this.getString(jsonObject, "image_url"));
    }
    
    public Place(final Cursor cursor){
    	setId(cursor.getInt(cursor.getColumnIndex(PlaceTable.ID)));
    	setPlaceName(cursor.getString(cursor.getColumnIndex(PlaceTable.PLACE_NAME)));
    	setPlaceCountry(cursor.getString(cursor.getColumnIndex(PlaceTable.PLACE_COUNTRY)));
    	setPlaceDescription(cursor.getString(cursor.getColumnIndex(PlaceTable.PLACE_DESCRIPTION)));
    	setPlaceImageUrl(cursor.getString(cursor.getColumnIndex(PlaceTable.PLACE_IMAGE_URL)));
    }
    
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPlaceName() {
		return placeName;
	}
	
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	
	public String getPlaceCountry() {
		return placeCountry;
	}
	
	public void setPlaceCountry(String placeCountry) {
		this.placeCountry = placeCountry;
	}
	
	public String getPlaceDescription() {
		return placeDescription;
	}
	
	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}
	
	public String getPlaceImageUrl() {
		return placeImageUrl;
	}
	
	public void setPlaceImageUrl(String placeImageUrl) {
		this.placeImageUrl = placeImageUrl;
	}
	
    public String toString() {
    	
        StringBuffer strBuf = new StringBuffer();
        
        strBuf.append(id).
        	   append(" ").
        	   append(placeName).
        	   append(" ").
        	   append(placeCountry).
        	   append(" ").
        	   append(placeDescription).
        	   append(" ").
        	   append(placeImageUrl);

        return strBuf.toString();
    }
}
