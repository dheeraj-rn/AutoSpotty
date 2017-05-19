package com.example.autospotty;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
SharedPreferences sp;
public final static String  pin="pin";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sp=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final Editor editor=sp.edit();
		final EditText ed1=(EditText) findViewById(R.id.editText1);
		
		Button b1=(Button) findViewById(R.id.button1);
		Button b2=(Button) findViewById(R.id.button2);
		Button b3=(Button) findViewById(R.id.button3);
		Button b4=(Button) findViewById(R.id.button4);
		Button b5=(Button) findViewById(R.id.button5);
		Button b6=(Button) findViewById(R.id.button6);
		Button b7=(Button) findViewById(R.id.button7);
		Button b8=(Button) findViewById(R.id.button8);
		Button b9=(Button) findViewById(R.id.button9);
		Button reset=(Button) findViewById(R.id.button10);
		Button b0=(Button) findViewById(R.id.button11);
		Button ok=(Button) findViewById(R.id.button12);
		String s=sp.getString("pin", "");
		if(!s.equals(""))
		{
			Intent in=new Intent(MainActivity.this, SecondActivity.class);
			startActivity(in);
			finish();
		}
		else{
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			if(ed1.getText().equals(""))
				{
					ed1.setText("1");
				}
				else
			{
					ed1.setText(ed1.getText().toString()+"1");
			}
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ed1.getText().equals(""))
				{
					ed1.setText("2");
				}
				else
				{
				ed1.setText(ed1.getText().toString()+"2");
				}
			}
		});
		
b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ed1.getText().equals(""))
				{
					ed1.setText("3");
				}
				else
				{
				ed1.setText(ed1.getText().toString()+"3");
				}
			}
		});
		
b4.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed1.getText().equals(""))
		{
			ed1.setText("4");
		}
		else
		{
		ed1.setText(ed1.getText().toString()+"4");
		}
	}
});

b5.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed1.getText().equals(""))
		{
			ed1.setText("5");
		}
		else
		{
		ed1.setText(ed1.getText().toString()+"5");
		}
	}
});

b6.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed1.getText().equals(""))
		{
			ed1.setText("6");
		}
		else
		{
		ed1.setText(ed1.getText().toString()+"6");
		}
	}
});

b7.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed1.getText().equals(""))
		{
			ed1.setText("7");
		}
		else
		{
		ed1.setText(ed1.getText().toString()+"7");
		}
	}
});

b8.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed1.getText().equals(""))
		{
			ed1.setText("8");
		}
		else
		{
		ed1.setText(ed1.getText().toString()+"8");
		}
	}
});

b9.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed1.getText().equals(""))
		{
			ed1.setText("9");
		}
		else
		{
		ed1.setText(ed1.getText().toString()+"9");
		}
	}
});

reset.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		ed1.setText("");
		}
});

b0.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed1.getText().equals(""))
		{
			ed1.setText("0");
		}
		else
		{
		ed1.setText(ed1.getText().toString()+"0");
		}
	}
});

ok.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		editor.putString("pin", ed1.getText().toString());
		editor.commit();
		String msg=sp.getString("pin", "123").toString();
		if(msg.equals(""))
		{
			Toast.makeText(MainActivity.this, "Invalid!!", 3000).show();
	}
	else
	{
		Toast.makeText(MainActivity.this, "Password Set", 3000).show();
		Intent in=new Intent(MainActivity.this, Reminder.class);
		startActivity(in);
		finish();
		}
		
		
	}
});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
