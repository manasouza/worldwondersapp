package com.curso.android.ciant.worldwondersapp.database.table;

public interface PlaceTable {

	String TABLE_NAME = "place";
	 
	String ID = "_id";
	String PLACE_NAME = "place_name";
	String PLACE_COUNTRY = "place_country";
	String PLACE_DESCRIPTION = "place_description";
	String PLACE_IMAGE_URL = "place_image_url";
	
	/**
	 * Definição das colunas 
	 */
	
	String[] ALL_COLUMNS = new String[]{
											ID, 
											PLACE_NAME,
											PLACE_COUNTRY, 
											PLACE_DESCRIPTION, 
											PLACE_IMAGE_URL
										};

	
	/**
	 * Criação da tabela com seus respectivos tipos
	 */
	
    String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT" + "," 
    														 + PLACE_NAME + " TEXT" + "," 
    														 + PLACE_COUNTRY + " TEXT" + "," 
    														 + PLACE_DESCRIPTION + " TEXT" + "," 
    														 + PLACE_IMAGE_URL + " TEXT" + ")";
	
    
    /**
     * Modelo de String para execução dos comandos insert full
     */
    
    String SQL_INSERT = "INSERT INTO " + TABLE_NAME + " (" + PLACE_NAME + "," 
    													   + PLACE_COUNTRY + ","
    													   + PLACE_DESCRIPTION + "," 
    													   + PLACE_IMAGE_URL + ") VALUES (?, ?, ?, ? )";
    
    
    /**
     * Modelo de String para dropar uma tabela
     */
    
    String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    
    
    /**
     * Mapeamento do id equals da tabela
     */
    
    String WHERE_ID_EQUALS = ID + "= ?";
}
