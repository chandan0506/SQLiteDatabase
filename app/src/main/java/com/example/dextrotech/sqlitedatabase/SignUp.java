package com.example.dextrotech.sqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dextrotech.sqlitedatabase.Database.InsertData;
import com.example.dextrotech.sqlitedatabase.Database.TestDatabase;
import com.example.dextrotech.sqlitedatabase.SetterGetter.SetGet;
import java.util.Calendar;

public class SignUp extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextMobile;
    private EditText editTextAge;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonSignUp;
    private String date;
    private SetGet setGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        intializeContent();
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takingValue();

            }
        });
    }

    public void intializeContent() {
        editTextName = (EditText) this.findViewById(R.id.edit_text_name);
        editTextEmail = (EditText) this.findViewById(R.id.edit_text_email);
        editTextMobile = (EditText) this.findViewById(R.id.edit_text_mobile);
        editTextPassword = (EditText) this.findViewById(R.id.edit_text_password);
        editTextConfirmPassword = (EditText) this.findViewById(R.id.edit_text_confirm_password);
        buttonSignUp = (Button) this.findViewById(R.id.button_signup_signup);
        editTextAge = (EditText) this.findViewById(R.id.edit_text_age);
        /*editTextAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        SignUp.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });*/
    }

    /*@Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        date = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        editTextAge.setText(date);

    }*/

    public void takingValue() {
        if(checking()) {
            if (editTextPassword.getText().toString().equals( editTextConfirmPassword.getText().toString())) {
            setGet = new SetGet();
            setGet.setName(editTextName.getText().toString().trim());
            setGet.setEmail(editTextAge.getText().toString().trim());
            setGet.setMobile(editTextMobile.getText().toString().trim());
            setGet.setPassword(editTextPassword.getText().toString().trim());
            setGet.setDob(date);
                TestDatabase testDatabase = new TestDatabase(getApplicationContext());
                InsertData insertData = new InsertData();
                insertData.insertingData(testDatabase, setGet);
        }else{
                Toast.makeText(SignUp.this,"Please enter same Password",Toast.LENGTH_SHORT).show();
            }

        }
    }
    private boolean checking(){
        boolean isNotNull=false;

        if(!editTextName.getText().toString().trim().equals("")){
            if(!editTextEmail.getText().toString().trim().equals("")){
                if (!editTextMobile.getText().toString().trim().equals("")){
                    if(!editTextPassword.getText().toString().trim().equals("")){
                        isNotNull = true;
                    }else{
                        Toast.makeText(SignUp.this,"Enter valid password",Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(SignUp.this,"Enter valid Mobile",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(SignUp.this,"Enter valid Email",Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(SignUp.this,"Enter valid Name",Toast.LENGTH_SHORT).show();
        }
        return isNotNull;
    }
}
