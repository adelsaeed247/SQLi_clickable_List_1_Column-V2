package com.example.georgevio.sqlinew;

import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    DBHelper mydb;
    ArrayList<Users> userList;
    ListView listView;
    Users users;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mydb = new DBHelper(this);

        userList = new ArrayList<>();
        Cursor data = mydb.getcontacts();
        int numRows = data.getCount();
        if(numRows == 0){
            Toast.makeText(ResultActivity.this,"The Database is empty  :(.",Toast.LENGTH_LONG).show();
        }else{
            int i=0;
            while(data.moveToNext()){
                users = new Users(data.getString(1),data.getString(2),data.getString(3),data.getString(4));
                userList.add(i,users);
                System.out.println(data.getString(1)+" "+data.getString(2)+" "+data.getString(3)+" "+data.getString(4));
                System.out.println(userList.get(i).getName());
                i++;
            }
            CustomListView adapter =  new CustomListView(this,R.layout.listview_row, userList);
            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
        }
    }



    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ListView resList= (ListView) findViewById(R.id.listView);
        final DBHelper mydb = new DBHelper(this);

        ArrayList<String> getData
            =getIntent().getExtras().getStringArrayList("fetchAll");
        Log.v("georgeLog", "getData created");
        for (String a:getData)
            Log.v("georgeLog dataToString:", a.toString());

        // we have to populate this...
        CustomListView newList = new CustomListView(this,getData);

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                getData);
        Log.v("georgeLog", "arrayAdapter created");
        resList.setAdapter(arrayAdapter);
        Log.v("georgeLog:", "resList called");

        // make the list clickable
        resList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String getName = ((TextView) view).getText().toString();
                if( mydb.deleteContactByName(getName) == 1){
                    Toast.makeText(getApplicationContext(),
                            "Record DELETED", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
}
