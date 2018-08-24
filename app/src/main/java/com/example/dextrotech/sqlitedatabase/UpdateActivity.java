package com.example.dextrotech.sqlitedatabase;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dextrotech.sqlitedatabase.Database.ShowingData;
import com.example.dextrotech.sqlitedatabase.Database.TestDatabase;

public class UpdateActivity extends AppCompatActivity {

    EditText id,name,age,email,mob;
    Button update,delete;
    Bundle info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initailization();
        loadData();
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringId = id.getText().toString();
                String[] arrayId = {stringId};
                Toast.makeText(getApplicationContext(), "id= " + stringId, Toast.LENGTH_LONG).show();
                SQLiteDatabase sqLiteDatabase;
                TestDatabase testDatabase = new TestDatabase(getApplicationContext());
                sqLiteDatabase = testDatabase.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(TestDatabase.NAME, name.getText().toString());
                contentValues.put(TestDatabase.EMAIL, email.getText().toString());
                contentValues.put(TestDatabase.MOBILE, mob.getText().toString());
                sqLiteDatabase.update(TestDatabase.TABLE_NAME, contentValues, TestDatabase.ID + " = ?", arrayId);
                Intent intent = new Intent(UpdateActivity.this, ShowingData.class);
                startActivity(intent);
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButtonEvent();
            }
        });
    }

    private void loadData() {
        info =getIntent().getExtras();
        id.setText(String.valueOf(info.getInt(TestDatabase.ID)));
        name.setText(info.getString(TestDatabase.NAME));
        age.setText(info.getString(TestDatabase.DOB));
        email.setText(info.getString(TestDatabase.EMAIL));
        mob.setText(info.getString(TestDatabase.MOBILE));

    }


    private void initailization() {

        id = (EditText)this.findViewById(R.id.edit_text_id);
        id.setEnabled(false);
        name = (EditText)this.findViewById(R.id.edit_text_name);
        age = (EditText)this.findViewById(R.id.edit_text_age);
        email = (EditText)this.findViewById(R.id.edit_text_email);
        mob = (EditText)this.findViewById(R.id.edit_text_mobile);
        update = (Button)this.findViewById(R.id.button_update);
        delete = (Button)this.findViewById(R.id.button_delete);
    }
    public void deleteButtonEvent(){
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
        builder.setTitle("Delete this Record")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String stringId = id.getText().toString();
                        String[] arrayId = {stringId};
                        SQLiteDatabase sqLiteDatabase;
                        TestDatabase testDatabase = new TestDatabase(getApplicationContext());
                        sqLiteDatabase = testDatabase.getWritableDatabase();
                        sqLiteDatabase.delete(TestDatabase.TABLE_NAME,TestDatabase.ID + " = ?",arrayId);
                        Intent intent = new Intent(UpdateActivity.this,ShowingData.class);
                        startActivity(intent);
                        finish();
                    }
                });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
}
