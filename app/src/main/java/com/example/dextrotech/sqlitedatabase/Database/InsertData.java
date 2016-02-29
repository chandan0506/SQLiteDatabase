package com.example.dextrotech.sqlitedatabase.Database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dextrotech.sqlitedatabase.SetterGetter.SetGet;

/**
 * Created by Dextrotech on 2/23/2016.
 */
public class InsertData {
    SQLiteDatabase sqLiteDatabase;
    public void insertingData(TestDatabase testDatabase,SetGet setGet){
        ContentValues contentValues = new ContentValues();
        contentValues.put(TestDatabase.NAME,setGet.getName());
        contentValues.put(TestDatabase.EMAIL,setGet.getEmail());
        contentValues.put(TestDatabase.MOBILE,setGet.getMobile());
        contentValues.put(TestDatabase.DOB,setGet.getDob());
        sqLiteDatabase = testDatabase.getWritableDatabase();
        try{
            sqLiteDatabase.insert(TestDatabase.TABLE_NAME, null, contentValues);
        }
        catch (Exception e){
            Log.d("InsertDataClass","Data not insert in database");
        }
        sqLiteDatabase.close();
    }
}
