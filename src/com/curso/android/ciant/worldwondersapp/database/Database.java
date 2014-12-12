package com.curso.android.ciant.worldwondersapp.database;

import com.curso.android.ciant.worldwondersapp.database.table.PlaceTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "worldwonders.db";
    private static final int DATABASE_VERSION = 1;
	
    public Database(final Context context){
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		//Criando place table
		db.execSQL(PlaceTable.SQL_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
