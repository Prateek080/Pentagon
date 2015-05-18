package com.example.pentagonfinal;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class Googlemap extends ActionBarActivity {

	static LatLng present=null;
	  
	  private GoogleMap map;
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_googlemap);
		
		Bundle data=getIntent().getExtras();
		Double latitude=data.getDouble("latitude");
		Double longitude=data.getDouble("longitude");
		
		present = new LatLng(latitude, longitude);
		if(map==null)
	    {
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap(); 
	    if(map==null)
	    {
	    	Toast chc=Toast.makeText(this, "NOPE", Toast.LENGTH_LONG);
	    	chc.show();
	    }
	    else
	    {
	    MarkerOptions hamburg = new MarkerOptions().position(present).title("Your Location");
	    
	    map.addMarker(hamburg);
	    }
	    // Move the camera instantly to hamburg with a zoom of 15.
	   map.moveCamera(CameraUpdateFactory.newLatLngZoom(present, 15));
	    
	    // Zoom in, animating the camera.
	    map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	    }
		
	}

	public void onBackPressed()
	{
		super.onBackPressed();
		startActivity(new Intent(this,MainActivity.class));
		finish();
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.googlemap, menu);
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
