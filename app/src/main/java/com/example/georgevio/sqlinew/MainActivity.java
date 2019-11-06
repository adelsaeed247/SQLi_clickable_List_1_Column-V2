package com.example.georgevio.sqlinew;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper mydb;

    Button bttnshow1;
    Button bttnshowall;
    Button bttnadd;

    EditText editTextName;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

        editTextName = (EditText)findViewById(R.id.editName);
        editTextPhone = (EditText)findViewById(R.id.editPhone);
        editTextEmail = (EditText)findViewById(R.id.editEmail);
        editTextAge = (EditText)findViewById(R.id.editAge);


        bttnadd = (Button) findViewById(R.id.bttnAdd);
        bttnshow1 = (Button) findViewById(R.id.bttnShow1);
        bttnshowall = (Button) findViewById(R.id.bttnShowAll);

        bttnadd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //remove the following toast...
                Toast.makeText(getApplicationContext(),
                        "bttnOnClick Pressed", Toast.LENGTH_SHORT).show();

                String getName = editTextName.getText().toString();
                String getPhone = editTextPhone.getText().toString();
                String getEmail = editTextEmail.getText().toString();
                String getAge = editTextAge.getText().toString();


                if (mydb.insertContact(getName, getPhone, getEmail, getAge)) {
                    Log.v("georgeLog", "Successfully inserted record to db");
                    Toast.makeText(getApplicationContext(),
                            "Inserted:" + getName + ", " + getPhone + "," + getEmail + "," + getAge, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "DID NOT insert to db :-(", Toast.LENGTH_SHORT).show();
            }
        });

        bttnshow1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("georgeLog", "clicked on fetch");
                Cursor getData=mydb.getData(1); //specific record (id=1)

                if (getData.moveToNext()) {// data?
                    Log.v("georgeLog", "data found in DB...");
                    String dName = getData.getString(getData.getColumnIndex("name"));
                    String dPhone = getData.getString(getData.getColumnIndex("phone"));
                    String dEmail = getData.getString(getData.getColumnIndex("email"));
                    String dAge = getData.getString(getData.getColumnIndex("age"));

                    Toast.makeText(getApplicationContext(),
                            "rec: " + dName + ", " + dPhone + ", " + dEmail + ", " + dAge, Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "did not get any data...:-(", Toast.LENGTH_LONG).show();
                getData.close();
            }
        });

        bttnshowall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Intent intent = new Intent(MainActivity.this,ResultActivity.class);
                //startActivity(intent);

                Log.v("georgeLog", "clicked on Result Button");
                ArrayList<String> fetchAll = new ArrayList<String>();
                fetchAll=mydb.getAllContacts();
                for (String a:fetchAll)
                    Log.v("georgeLog:", a.toString());
                    Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                    Log.v("georgeLog:", "intent executed");
                    intent.putStringArrayListExtra("fetchAll",fetchAll);
                    Log.v("georgeLog:","fetchALL executed");
                    startActivity(intent);
                    Log.v("georgeLog:", "startActivity executed");
            }
        });

        // to delete a record
        //find the record you want, get its id, and use the following
        //mydb.deleteContact (id);

    }
}
