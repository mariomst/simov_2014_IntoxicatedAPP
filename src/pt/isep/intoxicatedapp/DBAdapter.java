package pt.isep.intoxicatedapp;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		dbHelper_scores = new DBHelper(context, TABLE_SCORES, GAME_ID + " INTEGER, " + SCORE + " DOUBLE");
		dbHelper_contacts = new DBHelper(context, TABLE_CONTACTS, CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + PHONE_NUMBER + " TEXT");		
	}
	
	/*Obter Scores*/
	public ArrayList<Score> getScores(){
		ArrayList<Score> scores = new ArrayList<Score>();
		SQLiteDatabase sqliteDB = dbHelper_scores.getReadableDatabase();
		Cursor crsr = sqliteDB.rawQuery(SELECT_SCORES, null);
		crsr.moveToFirst();
		for(int i=0; i<crsr.getCount(); i++){
			scores.add(new Score(crsr.getInt(0), crsr.getDouble(1)));
			crsr.moveToNext();
		}
		return scores;
	}
	
	/*Obter Score*/
	public Score getScore(int gameID) {
        SQLiteDatabase sqliteDB = dbHelper_scores.getReadableDatabase();
        String s = "SELECT * FROM " + TABLE_SCORES + " WHERE " + GAME_ID + "=" + gameID;
        Cursor crsr = sqliteDB.rawQuery(s, null);
        crsr.moveToFirst();
        Score scr = new Score(crsr.getInt(0), crsr.getDouble(1));
        return scr;
    }
	
	/*Inserir novo score*/
	public boolean insertScore(int gameID, double Score){
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
	
	/*Atualizar score*/
	public boolean updateScore(int gameID, double Score){
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

	/*Obter Contactos*/
	public ArrayList<Contact> getContacts(){
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		SQLiteDatabase sqliteDB = dbHelper_contacts.getReadableDatabase();
		Cursor crsr = sqliteDB.rawQuery(SELECT_CONTACTS, null);
		crsr.moveToFirst();
		for(int i=0; i<crsr.getCount(); i++){
			contacts.add(new Contact(crsr.getInt(0), crsr.getString(1), crsr.getString(2)));
			crsr.moveToNext();
		}
		return contacts;
	}

	/*Obter Contacto*/
	public Contact getContact(int contactID){
		SQLiteDatabase sqliteDB = dbHelper_contacts.getReadableDatabase();
		String s = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + CONTACT_ID + "=" + contactID;
		Cursor crsr = sqliteDB.rawQuery(s, null);
		crsr.moveToFirst();
		Contact cnt = new Contact(crsr.getInt(0), crsr.getString(1), crsr.getString(2));
		return cnt;
	}

	/*Inserir novo contacto*/
	public boolean insertContact(int contactID, String name, String phoneNumber){
		try{
			SQLiteDatabase sql = dbHelper_contacts.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(CONTACT_ID, contactID);
			values.put(NAME, name);
			values.put(PHONE_NUMBER, phoneNumber);
			sql.insert(TABLE_CONTACTS, null, values);
		} catch (SQLException sqlerror) {
			Log.v("Insert into table Contacts failed with error: ", sqlerror.getMessage());
			return false;
		}
		return true;
	}

	/*Atualizar contacto*/
	public boolean updateContact(int contactID, String name, String phoneNumber){
		try{
			SQLiteDatabase sql = dbHelper_contacts.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put(CONTACT_ID, contactID);
			values.put(NAME, name);
			values.put(PHONE_NUMBER, phoneNumber);
			sql.update(TABLE_CONTACTS, values, CONTACT_ID + "=" + contactID, null);
		} catch (SQLException sqlerror){
			Log.v("Update value in table Contacts failed with error: ", sqlerror.getMessage());
			return false;
		}
		return true;
	}

	/*Eliminar contacto*/
	public boolean deleteContact(int contactID){
		try{
			SQLiteDatabase sql = dbHelper_contacts.getWritableDatabase();
			sql.delete(TABLE_CONTACTS, CONTACT_ID + "=" + contactID, null);
		} catch (SQLException sqlerror){
			Log.v("Delete value in table Contacts failed with error: ", sqlerror.getMessage());
			return false;
		}
		return true;
	}
}
