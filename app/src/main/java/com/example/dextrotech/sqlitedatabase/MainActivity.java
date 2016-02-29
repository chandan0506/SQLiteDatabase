package com.example.dextrotech.sqlitedatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dextrotech.sqlitedatabase.Database.ShowingData;

public class MainActivity extends AppCompatActivity {
    Button buttonShowingData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonShowingData = (Button)this.findViewById(R.id.button_login);
        buttonShowingData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowingData.class);
                startActivity(intent);
            }
        });
    }
    public void intentSignUP(View view){
        Intent intent = new Intent(MainActivity.this,SignUp.class);
        startActivity(intent);
    }
}
