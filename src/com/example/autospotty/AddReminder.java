package com.example.autospotty;

import java.util.Calendar;
import java.util.Vector;



import com.example.autospotty.database.DbUtils;
import com.example.autospotty.service.getReverseGeoCoding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddReminder extends FragmentActivity implements LocationListener {
	GoogleMap googleMap;
	EditText lat, lon,title,desc,profile,date;
	RadioButton c1,c2;
	DbUtils db = new DbUtils(this);
	private int dobDay, dobMonth, dobYear;
	private final int DOB_DATE_DIALOG = 1;
	Button add;
	MarkerOptions markerOption;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_reminder);
		lat = (EditText) findViewById(R.id.editTextLat);
		lon = (EditText) findViewById(R.id.editTextLon);

		title = (EditText) findViewById(R.id.editTextTitle);
		desc = (EditText) findViewById(R.id.editTextDesc);
		

		date = (EditText) findViewById(R.id.editTextDate);
		
		c1=(RadioButton) findViewById(R.id.radioButtonSilent);
		c2=(RadioButton) findViewById(R.id.radioButtonGeneral);
		
		add= (Button) findViewById(R.id.buttonAddSpot);
	
		final Calendar c = Calendar.getInstance();
		dobYear = c.get(Calendar.YEAR);
		dobMonth = c.get(Calendar.MONTH);
		dobDay = c.get(Calendar.DAY_OF_MONTH);
		updateFromDateDisplay();
		
		SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map);

		// Getting GoogleMap object from the fragment
		googleMap = fm.getMap();

		// Enabling MyLocation Layer of Google Map
		googleMap.setMyLocationEnabled(true);
		
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Creating a criteria object to retrieve provider
		Criteria criteria = new Criteria();

		// Getting the name of the best provider
		String provider = locationManager.getBestProvider(criteria, true);

		// Getting Current Location
		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			onLocationChanged(location);
		}

		locationManager.requestLocationUpdates(provider, 50000, 0, this);

	

	googleMap.setOnMapClickListener(new OnMapClickListener() {

				@Override
		public void onMapClick(LatLng arg0) {
			// TODO Auto-generated method stub
					googleMap.clear();
					
					drawMarker(arg0);

					lat.setText(arg0.latitude+"");
					lon.setText(arg0.longitude+"");
				
					Toast.makeText(
							getBaseContext(),
							"Latitude Selected :" + arg0.latitude
									+ "Longitude Selected :" + arg0.longitude,
							Toast.LENGTH_LONG).show();

		}
	});

	googleMap.setOnMapLongClickListener(new OnMapLongClickListener() {
		@Override
		public void onMapLongClick(LatLng point) {

			// Removing the marker and circle from the Google Map
			googleMap.clear();

		}
	});

}

public void onClick(View v)

{
	if(!title.getText().toString().equals("")&&!desc.getText().toString().equals("")&&!lat.getText().toString().equals("")&&!lon.getText().toString().equals("")&&!date.getText().toString().equals(""))
	{
				
	final String gen;
	if (c1.isChecked()) {
		gen = "silent";

	} else {
		gen = "general";

	}
	getReverseGeoCoding g = new getReverseGeoCoding();
	try
	{
	
	g.getAddress(Double.parseDouble(lat.getText().toString()), Double.parseDouble(lon.getText().toString()));
	

	
	}
	
	finally
	{
	
	Vector<String> vectObj = new Vector<String>();
	vectObj.add(title.getText().toString());
	vectObj.add(desc.getText().toString());
	vectObj.add(gen);
	vectObj.add(lat.getText().toString());
	vectObj.add(lon.getText().toString());
	vectObj.add(g.getAddress1()+","+g.getAddress2()+","+g.getCity()+"."+g.getState()+"."+g.getPIN()+"."+g.getCountry());
	vectObj.add(date.getText().toString());

	db.insertReminder(vectObj);
	
	title.setText("");
	desc.setText("");
	lat.setText("");
	lon.setText("");
	
	
	Toast.makeText(getApplicationContext(), "Reminder Added Successfully", 5000).show();
	}
	}
	
	else
	{
		Toast.makeText(getApplicationContext(), "Please Enter All Values", 5000).show();
	}
	
}

private void drawMarker(LatLng point) {
	// Creating an instance of MarkerOptions
	markerOption = new MarkerOptions();

	// Setting latitude and longitude for the marker
	markerOption.position(point);

	googleMap.addMarker(markerOption);

}

		
	
	
	private void updateFromDateDisplay() {
		// Month is 0 based so add 1
		date.setText(String.format("%d-%d-%d", dobYear, dobMonth + 1, dobDay));

	}
	public void showDobDateDialog(View v) {
		showDialog(DOB_DATE_DIALOG);
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DOB_DATE_DIALOG:
			return new DatePickerDialog(this, dobDateSetListener, dobYear,
					dobMonth, dobDay);

		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener dobDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int pYear, int pMonth, int pDay) {
			dobYear = pYear;
			dobMonth = pMonth;
			dobDay = pDay;
			updateFromDateDisplay();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_reminder, menu);
		return true;
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		googleMap.clear();

		// Getting latitude of the current location
		double latitude = location.getLatitude();

		// Getting longitude of the current location
		double longitude = location.getLongitude();
		
		lat.setText(latitude+"");
		lon.setText(longitude+"");


		String latitude1 = Double.toString(latitude);
		String longitude1 = Double.toString(longitude);

		LatLng latLng = new LatLng(latitude, longitude);

		// Adding marker to location
		Marker mylocation = googleMap.addMarker(new MarkerOptions()
				.position(latLng)
				.title("You")
				.snippet("Your Current Location")
				.icon(BitmapDescriptorFactory
						.fromResource(R.drawable.ic_launcher)));

		// Showing the current location in Google Map
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		// Zoom in the Google Map
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

		// Setting latitude and longitude in the TextView tv_location

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
