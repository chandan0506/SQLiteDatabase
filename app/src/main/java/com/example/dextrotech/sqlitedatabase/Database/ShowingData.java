package com.example.dextrotech.sqlitedatabase.Database;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dextrotech.sqlitedatabase.R;
import com.example.dextrotech.sqlitedatabase.SetterGetter.SetGet;
import com.example.dextrotech.sqlitedatabase.UpdateActivity;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dextrotech on 2/24/2016.
 */
public class ShowingData extends AppCompatActivity{
    private ArrayList<SetGet> arrayList = new ArrayList<SetGet>();
    private SQLiteDatabase sqLiteDatabase;
    private String tableName = TestDatabase.TABLE_NAME;
    private ListView listView;
    SetGet setGet;
    String name[];

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showing_data);
        listView = (ListView)findViewById(R.id.list_view);
        fetchingResult();
        showingResult();
    }

    private void showingResult() {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ShowingData.this,android.R.layout.simple_list_item_1,name);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(arrayList.size()>0) {
                    setGet = arrayList.get(position);
                    Intent intent = new Intent(ShowingData.this, UpdateActivity.class);
                    intent.putExtra(TestDatabase.ID, setGet.getId());
                    intent.putExtra(TestDatabase.NAME, setGet.getName());
                    intent.putExtra(TestDatabase.EMAIL, setGet.getEmail());
                    intent.putExtra(TestDatabase.MOBILE,setGet.getMobile());
                    intent.putExtra(TestDatabase.DOB, setGet.getDob());
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"No Data Found",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

//    Testing Push

    private void fetchingResult() {
        int i=0;
        try{
            TestDatabase testDatabase = new TestDatabase(getApplicationContext());
            sqLiteDatabase = testDatabase.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from "+tableName,null);
            if(cursor.getCount()>0){
                name = new String[cursor.getCount()];
                while(cursor.moveToNext()){
                    SetGet setGet = new SetGet();
                    setGet.setId(cursor.getInt(0));
                    setGet.setName(cursor.getString(cursor.getColumnIndex("name")));
                    setGet.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                    setGet.setMobile(cursor.getString(cursor.getColumnIndex("mobile")));
                    setGet.setDob(cursor.getString(cursor.getColumnIndex("dob")));
                    name[i]=setGet.getName();
                    arrayList.add(setGet);
                    i++;
                }
            }
            else{
                name = new String[1];
                name[0]="No Record Found";
            }
        }catch (SQLiteException se){
            Log.d("ShowingData","This is from fetchingResult method");
        }
    }
}
