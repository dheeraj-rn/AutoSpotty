package com.example.autospotty.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbUtils extends SQLiteOpenHelper {
	
	
	
	public DbUtils(Context context) {
		super(context, "autospotty.db"	, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		String query = "create table _reminders (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, profile TEXT, latitude TEXT, longitude TEXT,address TEXT,date TEXT)";
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		onCreate(db);
	}
	
	
	public int getReminderCount()
	{
		SQLiteDatabase db=getWritableDatabase();
        
        String query="SELECT * FROM _reminders";
        Cursor c=db.rawQuery(query, null);
        int count = c.getCount();
		return count;
    }

	
	public Vector<Vector<String>> getReminders()
    {
    	SQLiteDatabase db = getWritableDatabase();
    	Vector<Vector<String>> vectData = new Vector<Vector<String>>();
    	String query ="SELECT * FROM _reminders";
    	Cursor c = db.rawQuery(query, null);
    	while(c.moveToNext())
    	{
    		Vector<String> vectObj = new Vector<String>();
    		vectObj.add(c.getString(0));
    		vectObj.add(c.getString(1));
    		vectObj.add(c.getString(2));
    		vectObj.add(c.getString(3));
    		vectObj.add(c.getString(4));
    		vectObj.add(c.getString(5));
    		vectObj.add(c.getString(6));
    		vectObj.add(c.getString(7));
    		
    		System.out.println("Reminder ID:"+c.getString(0));
    		
    		vectData.add(vectObj);
    	}
    	
    	
    	
    	return vectData;
    }
	


	public Vector<String> getReminderDetails(int id)  {
    	SQLiteDatabase db = getWritableDatabase();
    	Vector<String> vectObj = new Vector<String>();
    	String query ="SELECT * FROM _reminders WHERE _id="+id;
    	Cursor c = db.rawQuery(query, null);
    	while(c.moveToNext())
    	{
    		
    		vectObj.add(c.getString(1));    		
    		vectObj.add(c.getString(2));
    		vectObj.add(c.getString(3));
    		vectObj.add(c.getString(4));
    		vectObj.add(c.getString(5));
    		vectObj.add(c.getString(6));
    		vectObj.add(c.getString(7));
    		
    		
    		
    	}
    	
    	return vectObj;
    }

	public void dropDB()
	{
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("DROP TABLE IF EXISTS _reminders");
		db.close();

		onCreate(db);
	}
	
	public void insertReminder(Vector<String> vectObj)
    {
    	SQLiteDatabase db = getWritableDatabase();
    	ContentValues values = new ContentValues();
    	values.put("title", vectObj.get(0));
    	values.put("description", vectObj.get(1));
    	values.put("profile", vectObj.get(2));
    	values.put("latitude",vectObj.get(3));
    	values.put("longitude", vectObj.get(4));
    	values.put("address", vectObj.get(5));
    	values.put("date", vectObj.get(6));
    	db.insert("_reminders", null, values);
    	db.close();
    	
    	System.out.println("INSERTED:"+vectObj.get(1));
    	
    }
	public void deleteReminders()
	{
    	SQLiteDatabase db = getWritableDatabase();
    	db.execSQL("DELETE FROM _reminders");
    	db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE name='_reminders'");
    	
    	db.close();
	}

	public void update(Vector<String> vectObj,int pos) {
		
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("title", vectObj.get(0));
    	values.put("description", vectObj.get(1));
    	values.put("profile", vectObj.get(2));
    	values.put("latitude", vectObj.get(3));
    	values.put("longitude", vectObj.get(4));
    	values.put("address", vectObj.get(5));
    	values.put("date", vectObj.get(6));
    	db.update("_reminders", values, "_id"+"="+pos, null);
		
		db.close();
	}

	public void deleteReminder(int pos1) {
		SQLiteDatabase db = getWritableDatabase();
    	db.execSQL("DELETE FROM _reminders WHERE _id="+pos1);
    	
    	db.close();
		
	}
}
