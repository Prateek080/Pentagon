package com.example.pentagonfinal;





import java.io.IOException;

import android.support.v7.app.ActionBarActivity;


import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.InputFilter.LengthFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.provider.*;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.app.Activity;
import android.view.Menu;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;











public class MainActivity extends ActionBarActivity {
	
	
	
	
	//add contacts
	int flag=0;
	int i=0;
	final static int PICK_CONTACT=3;
	String name=null,id=null;
	String number=null;
	TextView sd;
	int counter=0;
	private String[] stored_number = new String[5];
	private String[] stored_names = new String[5];
	String myMsg="I am in an emergency situation. I need urgent help. Come and get me. My location is near : " ;
	
	
	
	
	
	GPSTracker gps;
	double latitude;
	double longitude;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();
		
		
		final Vibrator vibe = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
		//replace yourActivity.this with your own activity or if you declared a context you can write context.getSystemService(Context.VIBRATOR_SERVICE);   
		
		ImageButton camera=(ImageButton)findViewById(R.id.recordbtn1);
		ImageButton call=(ImageButton)findViewById(R.id.imageButton4);
		ImageButton track=(ImageButton)findViewById(R.id.imageButton2);
		ImageButton audio=(ImageButton)findViewById(R.id.imageButton5);
		ImageButton alarm=(ImageButton)findViewById(R.id.imageButton3);
		
		
		
		
		//add contacts
		for(int k=0;k<5;k++){
			stored_names[i]=stored_number[i]=null;
		}
		
		
		
		loadSavedPreferences();
		ImageButton b1=(ImageButton)findViewById(R.id.imageButton6);
		ImageButton b2 = (ImageButton)findViewById(R.id.imageButton7);
		ImageButton b3 = (ImageButton)findViewById(R.id.imageButton8);
		ImageButton b4 = (ImageButton)findViewById(R.id.imageButton9);
		ImageButton b5 = (ImageButton)findViewById(R.id.imageButton10);
		ImageButton b6=(ImageButton)findViewById(R.id.imageButton11);
		
		
		
		
		b1.setOnClickListener(new btnClick());
		b2.setOnClickListener(new btnClick());
		b3.setOnClickListener(new btnClick());
		b4.setOnClickListener(new btnClick());
		b5.setOnClickListener(new btnClick());
		String location= "";
		locate_me();
		
		myMsg =myMsg + "http://maps.gogle.com/maps?q="+latitude+","+longitude;
		
	b6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			
			public void onClick(View v) {
				
				vibe.vibrate(80);
				
				Toast.makeText(getBaseContext(),"Request Accepted",Toast.LENGTH_SHORT).show();
				
				for( i =0; i<5;i++)
				{
					String thenumber= stored_number[i];
					String thename= stored_names[i];
					
					if(thenumber!=null && thename!=null)
					{
					//	System.out.println(i+" "+thenumber+" "+thename);
			        	
					sendMsg(thename,thenumber,myMsg);
					}
				}
				
			}
		});		
		//camera
		camera.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				vibe.vibrate(80);
				opencamera();
			}
		});
		
		
		
		
				
		
		//call
		
		
		call.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				callme();
				
			}
		});
		
		
		//track
		
		track.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				vibe.vibrate(80);
				gps=new GPSTracker(MainActivity.this);
				if(gps.canGetLocation()){
					latitude=gps.getLatitude();
					longitude=gps.getLongitude();
					
					Intent openmap=new Intent(getApplicationContext(),Googlemap.class);
					Bundle b=new Bundle();
					
					b.putDouble("latitude", latitude);
					b.putDouble("longitude", longitude);
					openmap.putExtras(b);
					startActivity(openmap);
					finish();
					
				}
				else
				{
					gps.showSettingsAlert();
				}
			}
				
});
		//audio
		
		audio.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent openaudio=new Intent(getApplicationContext(),Audio.class);
				startActivity(openaudio);
				finish();
			
				
			}
		});
		
		
		//alarm
		
		
		alarm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				vibe.vibrate(80);
				Intent opensound=new Intent(getApplicationContext(),Alarm.class);
				startActivity(opensound); 
				
			}
		});
		
		
		
		
	}

	//add contacts
	//add contacts
	 class btnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.imageButton6:
				flag=1;
				break;
			case R.id.imageButton7:
				flag=2;
				break;
			case R.id.imageButton8:
				flag=3;
				break;
			case R.id.imageButton9:
				flag=4;
				break;
			case R.id.imageButton10:
				flag=5;
				break;	
			
		}
			getContact();
			
		}
		 
	 }


		private void locate_me()
		{
			gps=new GPSTracker(MainActivity.this);
			if(gps.canGetLocation()){
				latitude=gps.getLatitude();
				longitude=gps.getLongitude();
				
				
			}
			else
			{
				gps.showSettingsAlert();
					
			}
		}

	 public void getContact(){
 		
	    	Intent it= new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(it,PICK_CONTACT);
	    }
	    
	    @Override
		public void onActivityResult(int reqCode, int resultCode, Intent data){
	    	
	    	
		super.onActivityResult(reqCode, resultCode, data);
		switch(reqCode){
		case (PICK_CONTACT):
		if (resultCode == Activity.RESULT_OK){
		Uri contactData = data.getData();
		Cursor c =getContentResolver().query(contactData, null, null, null, null);

		if (c.moveToFirst()){
		// other data is available for the Contact. I have decided
		// to only get the name of the Contact.
		id =c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
		
		
		String hasPhone =c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

	    if (hasPhone.equalsIgnoreCase("1")) {
					Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, 
				                                                     ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ id, null, null);
				  phones.moveToFirst();
				   number = phones.getString(phones.getColumnIndex("data1"));
	    }
	    else
	    {
	    	number="1234";
	    }
			    name = c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
			   // sd= (TextView)findViewById(R.id.textView1);
			    
			    
			    first_name();
			   
		        if(number=="1234")
		        {
		        	Toast number_not=Toast.makeText(this, "Number Not Found", Toast.LENGTH_SHORT);
			        number_not.show();
		        }
		        else
		        {
		        	String a=Integer.toString(flag);
			        a=Integer.toString(flag);
			        savePreferences(a,name);
			        a=Integer.toString(flag+10);
			        savePreferences(a,number);
			        stored_names[flag-1]=name;
			        stored_number[flag-1]=number;

		        	Toast number_not=Toast.makeText(this, flag-1+" "+name+" "+number, Toast.LENGTH_SHORT);
			        
			        set(flag);
			  }
			   	
		}
		}
		}
	    }
	    
	    public void first_name(){
	    	
	    	int len= name.length();
	    	String[] parts= name.split(" ");
	    	name = parts[0];
	    	
	    	
	    	
	    	
	    }
private void sendMsg(final String thename,final String theNumber, String myMsg) {
			
			String SENT = "Message Sent ";
			String DELIVERED = "Message Delivered";
			
			PendingIntent sentPI = PendingIntent.getBroadcast(this,0,new Intent(SENT), 0);
			PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,new Intent(DELIVERED), 0);
			
			
			registerReceiver(new BroadcastReceiver()
			{
				
				@Override
				public void onReceive(Context context, Intent intent) {
					switch(getResultCode())
					{
			case Activity.RESULT_OK:
			   Toast.makeText(MainActivity.this,"Sent to"+" "+thename.toString(),Toast.LENGTH_SHORT);
			   break;
			case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				Toast.makeText(getBaseContext(),"Failed to"+" "+thename.toString(),Toast.LENGTH_SHORT);
				  break;
			case SmsManager.RESULT_ERROR_NO_SERVICE:
				Toast.makeText(getBaseContext(),"No Service",Toast.LENGTH_SHORT);
				
				
					}
					//Toast.makeText(getBaseContext(),"Nodasasdas Service",Toast.LENGTH_SHORT).show();
				}

				

				
			},new IntentFilter(SENT));
			
			
			registerReceiver(new 
					BroadcastReceiver()
			{

				@Override
				public void onReceive(Context arg0, Intent arg1) {
					{
						
						
							switch(getResultCode())
									{
							case Activity.RESULT_OK:
							  Toast.makeText(getBaseContext(),"Delivered to "+thename.toString(), Toast.LENGTH_LONG);
							   break;
							case Activity.RESULT_CANCELED:
								Toast.makeText(getBaseContext(),"deliver fail to "+thename.toString(),Toast.LENGTH_LONG);
								  break;
							
								
									}
							//Toast.makeText(getBaseContext(),"No Sers",Toast.LENGTH_SHORT).show();
						  }
					
				}

				
				
			},new IntentFilter(DELIVERED));
			
			
			
			
			// TODO Auto-gepnerated method stub
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(theNumber, null, myMsg, sentPI, deliveredPI);
		
	}
 
	    private void set(int flagg)
	    {
	    	if(flagg==1)
	        {
	        ImageButton imageView = (ImageButton) findViewById(R.id.imageButton6);
	        TextView naming=(TextView)findViewById(R.id.textView1);
	        naming.setText(name);
	        
	        //set_image(id);
	        /*if(contactphoto!=null)
	        imageView.setImageURI(contactphoto);
	        else
	        	imageView.setImageResource(R.drawable.users);
	        	*/
	        }
	        if(flagg==2)
	        {
	        ImageButton imageView = (ImageButton) findViewById(R.id.imageButton7);
	        TextView naming=(TextView)findViewById(R.id.textView2);
	        naming.setText(name);
	        /*if(contactphoto!=null)
	        imageView.setImageURI(contactphoto);
	        else
	        	imageView.setImageResource(R.drawable.users);
	        	*/
	        }
	        if(flagg==3)
	        {
	        ImageButton imageView = (ImageButton) findViewById(R.id.imageButton8);
	        TextView naming=(TextView)findViewById(R.id.textView3);
	        naming.setText(name);
	        /*
	        if(contactphoto!=null)
	        imageView.setImageURI(contactphoto);
	        else
	        	imageView.setImageResource(R.drawable.users);
	        	*/
	        }
	        if(flagg==4)
	        {
	        ImageButton imageView = (ImageButton) findViewById(R.id.imageButton9);
	        TextView naming=(TextView)findViewById(R.id.textView4);
	        naming.setText(name);
	        /*
	        if(contactphoto!=null)
	        imageView.setImageURI(contactphoto);
	        else
	        	imageView.setImageResource(R.drawable.users);
	        	*/
	        }
	        if(flagg==5)
	        {
	        ImageButton imageView = (ImageButton) findViewById(R.id.imageButton10);
	        TextView naming=(TextView)findViewById(R.id.textView5);
	        naming.setText(name);
	        /*
	        if(contactphoto!=null)
	        imageView.setImageURI(contactphoto);
	        else
	        	imageView.setImageResource(R.drawable.users);
	        	*/
	        }
	    }

	    
	    
	    
	    
	    
	    private void savePreferences(String user_id,String user_info ) {
	    	
	        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	        Editor editor = sharedPreferences.edit();
	        editor.putString(user_id, user_info);
	        editor.commit();

	    }
	    
	    

		private void loadSavedPreferences() {
			
	        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	        counter=0;
	        for(int i=0;i<5;i++)
	        {
			        String nam = sharedPreferences.getString(Integer.toString(i+1), "");
			        String num = sharedPreferences.getString(Integer.toString(i+1+10), "");
			        id=sharedPreferences.getString(Integer.toString(i+1+20), "");
			        name=nam;
			        number=num;
			        if(number!="")
			        {
			        	stored_number[i]=number;
			        	stored_names[i]=name;
			        	counter++;
			        	//Toast pp=Toast.makeText(this, "no ="+number+"+" , Toast.LENGTH_LONG);
			 	      // pp.show();
			        }
			        //String Pto=  sharedPreferences.getString(Integer.toString(i+1+10), "");
			        set(i+1);
			        //Toast pp=Toast.makeText(this, nam+" || "+num+ " || " , Toast.LENGTH_LONG);
			       //pp.show();
	        
	       
	        }
	  //display stored numbers
	        Toast pp=Toast.makeText(this, "no_of_contact="+" "+counter , Toast.LENGTH_LONG);
		      // pp.show();
	        	/*for(int i=0;i<counter;i++)
	        	{
	        		 pp=Toast.makeText(this, stored_number[i] , Toast.LENGTH_LONG);
	        	      // pp.show();
	        	}
	        	*/
	       
	  }

	
	
	
	private void opencamera(){
    	
    	Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
    	startActivity(intent);
    	
    	
    }
	
	
	private void callme()
	{
		Intent callintent=new Intent(Intent.ACTION_CALL);
		callintent.setData(Uri.parse("tel:181"));
		startActivity(callintent);
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
