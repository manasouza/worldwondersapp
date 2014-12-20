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
		
				   String sqlMock1 = "INSERT INTO place VALUES(1, 'Machu Picchu', 'Peru', " +
	                "'Da hora d+ Machu Picchu', 'http://atp.cx/wp-content/uploads/2011/02/Machu-Picchu1.jpg');";
	        String sqlMock2 = "INSERT INTO place VALUES(2, 'Chichen Itza', 'Mexico', " +
	                "'Da hora d+ Chichen Itza', 'http://www.chichenitza.com/images/chichenitza.jpg');";
	        String sqlMock3 = "INSERT INTO place VALUES(3, 'Christ Redeemer', 'Brazil', " +
	                "'Da hora d+ Christ Redeemer', 'http://www.thewondersoftheworld.net/images/ChristTheRedeemer1.jpg');";
	        String sqlMock4 = "INSERT INTO place VALUES(4, 'Great Wall', 'China', " +
	                "'Da hora d+ Great Wall', 'http://img.timeinc.net/time/photoessays/2008/beijing_travel/great_wall_beijing.jpg');";
	        String sqlMock5 = "INSERT INTO place VALUES(5, 'Taj Mahal', 'India', " +
	                "'Da hora d+ Taj Mahal', 'http://www.tajmahal.com/images/taj_mahal_india.jpg');";
	        String sqlMock6 = "INSERT INTO place VALUES(6, 'Petra', 'Jordan', " +
	                "'Da hora d+ Petra', 'http://www.culturefocus.com/jordan/pictures/petra-26small.jpg');";
	        String sqlMock7 = "INSERT INTO place VALUES(7, 'Colosseum', 'Italy', " +
	                "'Da hora d+ Colosseum', 'http://www.altiusdirectory.com/Arts/images/Colosseum.jpg');";

	        db.execSQL(sqlMock1);
	        db.execSQL(sqlMock2);
	        db.execSQL(sqlMock3);
	        db.execSQL(sqlMock4);
	        db.execSQL(sqlMock5);
	        db.execSQL(sqlMock6);
	        db.execSQL(sqlMock7);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
