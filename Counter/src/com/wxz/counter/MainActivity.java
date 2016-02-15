package com.wxz.counter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView MText;
	Button Add;
	Button Sub;
	int count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MText=(TextView)findViewById(R.id.Countertext);
		Add=(Button)findViewById(R.id.button1);
		Sub=(Button)findViewById(R.id.button2);
	   count=0;
	
	   Add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				count++;
				MText.setText("The total is "+count);
				
			}
		});
		
		Sub.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				count--;
				MText.setText("The total is "+count);
				
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
}
