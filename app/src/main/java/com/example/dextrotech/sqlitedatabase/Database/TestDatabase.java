package com.example.dextrotech.sqlitedatabase.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dextrotech on 2/23/2016.
 */
public class TestDatabase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Dextrotech.db";
    public static final String TABLE_NAME = "Employee";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String MOBILE = "mobile";
    public static final String DOB = "dob";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +ID+ " INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL, " + NAME + " VARCHAR, " + EMAIL + " VARCHAR, " + MOBILE + " INTEGER, " + DOB + " VARCHAR)";
    public TestDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
