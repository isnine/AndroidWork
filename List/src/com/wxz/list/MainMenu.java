package com.wxz.list;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenu extends ListActivity{

	String classes[]={"MainActivity","Example1","Example2"};//设置菜单
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(MainMenu.this,
		                android.R.layout.simple_list_item_1,classes));
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO 自动生成的方法存根
		super.onListItemClick(l, v, position, id);
    	String classStr = classes[position];
		try {
			Class ourClass = Class.forName("com.wxz.list."+classStr);
			Intent ourIntent = new Intent(MainMenu.this,ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块 
			e.printStackTrace();
		}

	}
	

}
