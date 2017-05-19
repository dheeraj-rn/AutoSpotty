package com.example.autospotty;



import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class EmergencyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergency);
		
		Button msg=(Button) findViewById(R.id.buttonMsg);
		msg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				TelephonyManager mTelephonyMgr;
			    mTelephonyMgr = (TelephonyManager)
			        getSystemService(Context.TELEPHONY_SERVICE);

			    String number = mTelephonyMgr.getLine1Number();
			    
			    String location = "";

			    LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
			    
			    Criteria criteria = new Criteria();
			    String provider = locationManager.getBestProvider(criteria, true);
			    Location location1 = locationManager.getLastKnownLocation(provider);
				
			    
			    if(location1==null) {
			    	Toast.makeText(getApplicationContext(), "Location Not Avaliable!", Toast.LENGTH_LONG).show();
			    	return;
			    }
			    
			    Geocoder geoCoder = new Geocoder(getApplicationContext());
			    
			    try {
					List<Address> addresses = geoCoder.getFromLocation(location1.getLatitude(), location1.getLongitude(), 1);
				
					if(addresses.size()>0) {
						location = addresses.get(0).getAddressLine(0);
					}
			    
			    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
			    String message = "My Number : "+number+"\r\nMy Location : "+location+"\r\n";
			    
			    
			    //PackageManager packageManager = (PackageManager) getPackageManager();
			    
			   try {

//					Uri mUri = Uri.parse("smsto:+"+Globals.TrafficACP);
//					Intent mIntent = new Intent(Intent.ACTION_SENDTO, mUri);
//					mIntent.setPackage("com.whatsapp");
//					mIntent.putExtra("sms_body", "Help Required! : \r\n" + message);
//					mIntent.putExtra("chat",true);
//					startActivity(mIntent);
					
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(Configuration.EMERGENCY_PHONENO_1, number, "Help Required: "+message, null, null);

					Toast.makeText(getApplicationContext(), "SMS Send!", Toast.LENGTH_LONG).show();
				} catch(Exception e) {
					Toast.makeText(getApplicationContext(), "Please Install WhatsApp!", Toast.LENGTH_LONG).show();
				}
		

				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_emergency, menu);
		return true;
	}

}
