package pt.isep.intoxicatedapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBAdapter {
	private DBHelper dbHelper_scores;
	private DBHelper dbHelper_contacts;
	
	private static final String TABLE_SCORES = "Scores";
	private static final String TABLE_CONTACTS = "Contacts";
	
	/*Campos para a tabela de pontuações*/
	private static final String GAME_ID = "game_id";
	private static final String SCORE = "score";
	
	/*Campos para a tabela de contactos*/
	private static final String CONTACT_ID = "contact_id";
	private static final String NAME = "name";
	private static final String PHONE_NUMBER = "phone_number";
	
	/*Obter todos os valores das tabelas*/
	private static final String SELECT_SCORES = "SELECT * FROM " + TABLE_SCORES;
	private static final String SELECT_CONTACTS = "SELECT * FROM " + TABLE_CONTACTS;
	
	public DBAdapter(Context context){
		dbHelper_scores = new DBHelper(context, TABLE_SCORES, GAME_ID + " INTEGER, " + SCORE + " TEXT");
		dbHelper_contacts = new DBHelper(context, TABLE_CONTACTS, CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + PHONE_NUMBER + " TEXT");		
	}
	
	/*Inserir novo score*/
	public boolean insertScore(int gameID, String Score){
		try{
			SQLiteDatabase sql = dbHelper_scores.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(GAME_ID, gameID);
			values.put(SCORE, Score);
			sql.insert(TABLE_SCORES, null, values);
		} catch (SQLException sqlerror){
			Log.v("Insert into table Scores failed with error: ", sqlerror.getMessage());
			return false;
		}
		return true;
	}
	
	/*Atualizar Scores*/
	public boolean updateScore(int gameID, String Score){
		try{
			SQLiteDatabase sql = dbHelper_scores.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(GAME_ID, gameID);
			values.put(SCORE, Score);
			sql.update(TABLE_SCORES, values, GAME_ID + "=" + gameID, null);
		} catch (SQLException sqlerror){
			Log.v("Update value in table Scores failed with error: ", sqlerror.getMessage());
			return false;
		}
		return true;
	}
	
	/*Eliminar Scores*/
	public boolean deleteScore(int gameID){
		try{
			SQLiteDatabase sql = dbHelper_scores.getWritableDatabase();
			sql.delete(TABLE_SCORES, GAME_ID + "=" + gameID, null);
		} catch (SQLException sqlerror){
			Log.v("Delete value in table Scores failed with error: ", sqlerror.getMessage());
			return false;
		}
		return true;
	}	
}
