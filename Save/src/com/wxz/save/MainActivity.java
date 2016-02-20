package com.wxz.save;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Toast;

public class MainActivity extends Activity {

    private EditText editText;
    private TextView showTextView;
    // 要保存的文件名
    private String fileName = "chenzheng_java.txt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        // 获取页面中的组件
        editText = (EditText) findViewById(R.id.addText);
        showTextView = (TextView) findViewById(R.id.showText);
        Button addButton = (Button) this.findViewById(R.id.addButton);
        Button showButton = (Button) this.findViewById(R.id.showButton);
        // 绑定单击事件
        addButton.setOnClickListener(listener);
        showButton.setOnClickListener(listener);
	}

    // 声明监听器
    private View.OnClickListener listener = new OnClickListener() {
        public void onClick(View v) {
            Button view = (Button) v;
            switch (view.getId()) {
            case R.id.addButton:
                save();
                break;
            case R.id.showButton:
                read();
                break;
            }
        }
    };	
	
    /**
     *@author chenzheng_Java 
     *保存用户输入的内容到文件
     */
    private void save() {

        String content = editText.getText().toString();
        try {
            /* 根据用户提供的文件名，以及文件的应用模式，打开一个输出流.文件不存系统会为你创建一个的，
             * 至于为什么这个地方还有FileNotFoundException抛出，我也比较纳闷。在Context中是这样定义的
             *   public abstract FileOutputStream openFileOutput(String name, int mode)
             *   throws FileNotFoundException;
             * openFileOutput(String name, int mode);
             * 第一个参数，代表文件名称，注意这里的文件名称不能包括任何的/或者/这种分隔符，只能是文件名
             *          该文件会被保存在/data/data/应用名称/files/chenzheng_java.txt
             * 第二个参数，代表文件的操作模式
             *             MODE_PRIVATE 私有（只能创建它的应用访问） 重复写入时会文件覆盖
             *             MODE_APPEND  私有   重复写入时会在文件的末尾进行追加，而不是覆盖掉原来的文件
             *             MODE_WORLD_READABLE 公用  可读
             *             MODE_WORLD_WRITEABLE 公用 可读写
             *  */
            FileOutputStream outputStream = openFileOutput(fileName,
                    Activity.MODE_APPEND);
            outputStream.write(content.getBytes());
            outputStream.flush();
            outputStream.close();
            Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
    /**
     * @author chenzheng_java 
     * 读取刚才用户保存的内容
     */
    private void read() {
        try {
            FileInputStream inputStream = this.openFileInput(fileName);
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            while (inputStream.read(bytes) != -1) {
                arrayOutputStream.write(bytes, 0, bytes.length);
            }
            inputStream.close();
            arrayOutputStream.close();
            String content = new String(arrayOutputStream.toByteArray());
            showTextView.setText(content);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
