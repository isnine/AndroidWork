package com.nine.sqlite3;

import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends Activity  {
Button Add;
Button Find;
Button Del;
ListView myListView;
EditText Select;
SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 Add=(Button)findViewById(R.id.button1);
		 Find=(Button)findViewById(R.id.button2);
		 Del=(Button)findViewById(R.id.button3);
		 Select=(EditText)findViewById(R.id.editText1);
		 myListView=new ListView(this);
	     myListView=(ListView)findViewById(R.id.listView1);
	     OpenCreateDB();
	
	     
	     
	 	Add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				InsertData();
			}
		});
	 	
		Find.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Display();
			
			}
		});
		
		Del.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Delect();
			
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    public void OpenCreateDB(){
    	db = openOrCreateDatabase("sqldatas", this.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student (_id INTEGER PRIMARY KEY AUTOINCREMENT, Text text)");
    }
    
    public void InsertData(){
    	//String key=Select.getText().toString();
    	db.execSQL("INSERT INTO student VALUES (NULL,?)", new String[]{Select.getText().toString()});
    }
    
    public void Display(){
    	List<String> list=new ArrayList<String>();
    	Cursor c = db.rawQuery("SELECT * FROM student", null);
        while (c.moveToNext()) {
        	int a = c.getInt(c.getColumnIndex("_id"));
        	String b= c.getString(c.getColumnIndex("Text"));
        	String string=""+a+" "+b;
        	list.add(string);
        }
        c.close();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list);
        myListView.setAdapter(adapter);
    }
    
	 public void Delect(){
			db.execSQL("DELETE FROM student WHERE Text=?",new String[]{Select.getText().toString()});
	 }


}
