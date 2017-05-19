package com.example.autospotty;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("unused")
public class SecondActivity extends Activity {
	
	SharedPreferences sp;
	public static final String pin="pin";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		sp=getSharedPreferences("MyPref", Context.MODE_PRIVATE);
		final Editor editor=sp.edit();
		
		final EditText ed2=(EditText) findViewById(R.id.editText2);
		Button b13=(Button) findViewById(R.id.button13);
		Button b14=(Button) findViewById(R.id.button14);
		Button b15=(Button) findViewById(R.id.button15);
		Button b16=(Button) findViewById(R.id.button16);
		Button b17=(Button) findViewById(R.id.button17);
		Button b18=(Button) findViewById(R.id.button18);
		Button b19=(Button) findViewById(R.id.button19);
		Button b20=(Button) findViewById(R.id.button20);
		Button b21=(Button) findViewById(R.id.button21);
		Button reset1=(Button) findViewById(R.id.button22);
		Button b23=(Button) findViewById(R.id.button23);
		Button ok1=(Button) findViewById(R.id.button24);
	
		
		b13.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			if(ed2.getText().equals(""))
				{
					ed2.setText("1");
				}
				else
			{
					ed2.setText(ed2.getText().toString()+"1");
			}
			}
		});
		
		b14.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ed2.getText().equals(""))
				{
					ed2.setText("2");
				}
				else
				{
				ed2.setText(ed2.getText().toString()+"2");
				}
			}
		});
		
b15.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(ed2.getText().equals(""))
				{
					ed2.setText("3");
				}
				else
				{
				ed2.setText(ed2.getText().toString()+"3");
				}
			}
		});
		
b16.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed2.getText().equals(""))
		{
			ed2.setText("4");
		}
		else
		{
		ed2.setText(ed2.getText().toString()+"4");
		}
	}
});

b17.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed2.getText().equals(""))
		{
			ed2.setText("5");
		}
		else
		{
		ed2.setText(ed2.getText().toString()+"5");
		}
	}
});

b18.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed2.getText().equals(""))
		{
			ed2.setText("6");
		}
		else
		{
		ed2.setText(ed2.getText().toString()+"6");
		}
	}
});

b19.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed2.getText().equals(""))
		{
			ed2.setText("7");
		}
		else
		{
		ed2.setText(ed2.getText().toString()+"7");
		}
	}
});

b20.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed2.getText().equals(""))
		{
			ed2.setText("8");
		}
		else
		{
		ed2.setText(ed2.getText().toString()+"8");
		}
	}
});

b21.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed2.getText().equals(""))
		{
			ed2.setText("9");
		}
		else
		{
		ed2.setText(ed2.getText().toString()+"9");
		}
	}
});

reset1.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		ed2.setText("");
		}
});

b23.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		if(ed2.getText().equals(""))
		{
			ed2.setText("0");
		}
		else
		{
		ed2.setText(ed2.getText().toString()+"0");
		}
	}
});

ok1.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		String pin=sp.getString("pin", "");
		String text=ed2.getText().toString();
		if(pin.equals(text))
		{
			Intent in=new Intent(SecondActivity.this, Reminder.class);
			startActivity(in);
			finish();
		}
		else
		{
//			Dialog dialog=new Dialog(getApplicationContext());
			Toast.makeText(SecondActivity.this, "Incorrect Password", 3000).show();
			
		}
	}
});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

}
