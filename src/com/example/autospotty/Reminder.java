package com.example.autospotty;



import java.security.Provider;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Reminder extends FragmentActivity {

	Button b;
	GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reminder);
		
		b = (Button) findViewById(R.id.buttonAddReminder);

		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(),
						AddReminder.class);
				startActivity(i);
			}
		});
		
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);

		// Getting GoogleMap object from the fragment
		map = fm.getMap();

		// Enabling MyLocation Layer of Google Map
		LocationManager location=(LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria c=new Criteria();
		String provider=location.getBestProvider(c, true);
		map.setMyLocationEnabled(true);
		MarkerOptions marker=new MarkerOptions().title("Hello").position(new LatLng(10.0090, 13.32323));
		map.addMarker(marker);
		// Getting LocationManager object from System Service
		// LOCATION_SERVICE
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reminder, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		
		
		case R.id.menuEmer:
			startActivity(new Intent(getApplicationContext(),
					EmergencyActivity.class));
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
