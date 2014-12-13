package com.curso.android.ciant.worldwondersapp.adapter;

import com.curso.android.ciant.worldwondersapp.entity.Place;
import com.example.worldwondersapp.R;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FeedCursorAdapter extends CursorAdapter {

	public FeedCursorAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(final View view, final Context context, final Cursor cursor) {
		final Place place = new Place(cursor);

        final TextView textViewName = (TextView) view.findViewById(R.id.text_view_name);
        ImageView imageViewLike = (ImageView) view.findViewById(R.id.image_view_like);
        TextView textViewCountry = (TextView) view.findViewById(R.id.text_view_country);
        TextView textViewDescription = (TextView) view.findViewById(R.id.text_view_description);
        ImageView imageViewWorldWonder = (ImageView) view.findViewById(R.id.image_view_world_wonder);
        final ProgressBar prgBarViewWorldWonder = (ProgressBar) view.findViewById(R.id.prg_bar_view_world_wonder);

        textViewName.setText(place.placeName);
        textViewCountry.setText(place.placeCountry);
        textViewDescription.setText(place.placeDescription);
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View newView = inflater.inflate(R.layout.main_feed_item, parent, false);
		return newView;
	}
	
	
}