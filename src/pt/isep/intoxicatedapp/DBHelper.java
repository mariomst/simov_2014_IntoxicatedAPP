package pt.isep.intoxicatedapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private String createStatement = "";
	private String tableName;

	public DBHelper(Context context, String tableName, String fields) {
		super(context, tableName, null, DATABASE_VERSION);
		this.createStatement = "CREATE TABLE IF NOT EXISTS ";
		this.createStatement += tableName + " (";
		this.createStatement += fields + ");";
		this.tableName = tableName;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL(this.createStatement);	
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		arg0.execSQL("DROP TABLE IF EXISTS " + this.tableName);
		onCreate(arg0);
	}
}
