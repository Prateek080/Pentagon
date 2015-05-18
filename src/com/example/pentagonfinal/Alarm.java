package com.example.pentagonfinal;

import java.io.IOException;






import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Alarm extends ActionBarActivity {
	
	MediaPlayer mpButtonClick1=null;
	MediaPlayer mpButtonClick2=null;
	MediaPlayer mpButtonClick3=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		
		final Vibrator vibe = (Vibrator) Alarm.this.getSystemService(Context.VIBRATOR_SERVICE);
		//replace yourActivity.this with your own activity or if you declared a context you can write context.getSystemService(Context.VIBRATOR_SERVICE); 
		
		mpButtonClick1 = MediaPlayer.create(this, R.raw.button_click);
		mpButtonClick2 = MediaPlayer.create(this, R.raw.button_click);
		mpButtonClick3 = MediaPlayer.create(this, R.raw.button_click);
		
		Button bt1= (Button) findViewById(R.id.button1);
		Button bt2= (Button) findViewById(R.id.button2);
		Button bt3= (Button) findViewById(R.id.button3);
		ImageButton stop=(ImageButton)findViewById(R.id.recordbtn1);
		
		
		

	
	bt1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			vibe.vibrate(80);
			if(mpButtonClick2.isPlaying())
			{
				mpButtonClick2.stop();
				
				try {
					mpButtonClick2.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mpButtonClick2.seekTo(0);
				
			}
			if(mpButtonClick3.isPlaying())
			{
				
				mpButtonClick3.stop();
				
				try {
					mpButtonClick3.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mpButtonClick3.seekTo(0);
				
			}
			mpButtonClick1.start();
			
			
	}
		
	});
	
	
	bt2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			vibe.vibrate(80);
			if(mpButtonClick1.isPlaying())
			{
				mpButtonClick1.stop();
				
				try {
					mpButtonClick1.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mpButtonClick1.seekTo(0);
				
			}
			if(mpButtonClick3.isPlaying())
			{
				mpButtonClick3.stop();
				
				try {
					mpButtonClick3.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mpButtonClick3.seekTo(0);
				
			}
			mpButtonClick2.start();
			
			
		}
	});

	bt3.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			vibe.vibrate(80);
			if(mpButtonClick1.isPlaying())
			{
				mpButtonClick1.stop();
				
				try {
					mpButtonClick1.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mpButtonClick1.seekTo(0);
				
			}
			if(mpButtonClick2.isPlaying())
			{
				mpButtonClick2.stop();
				
				try {
					mpButtonClick2.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mpButtonClick2.seekTo(0);
				
			}
			
			mpButtonClick3.start();
			
			
		}
	});
	
	stop.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			vibe.vibrate(80);
			if(mpButtonClick1.isPlaying())
			{
				mpButtonClick1.stop();
				
				try {
					mpButtonClick1.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mpButtonClick1.seekTo(0);
				
			}
			
			if(mpButtonClick2.isPlaying())
			{
				mpButtonClick2.stop();
				
				try {
					mpButtonClick2.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mpButtonClick2.seekTo(0);
				
			}
			
			if(mpButtonClick3.isPlaying())
			{
				mpButtonClick3.stop();
				
				try {
					mpButtonClick3.prepare();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mpButtonClick3.seekTo(0);
				
			}
			
		}
	});
	
	}
	
	
	public void onBackPressed()
	{
		if(mpButtonClick1.isPlaying())
		{
			mpButtonClick1.stop();
			
			try {
				mpButtonClick1.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mpButtonClick1.seekTo(0);
			
		}
		
		if(mpButtonClick2.isPlaying())
		{
			mpButtonClick2.stop();
			
			try {
				mpButtonClick2.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mpButtonClick2.seekTo(0);
			
		}
		
		if(mpButtonClick3.isPlaying())
		{
			mpButtonClick3.stop();
			
			try {
				mpButtonClick3.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mpButtonClick3.seekTo(0);
			
		}
		
		
		super.onBackPressed();
		startActivity(new Intent(this,MainActivity.class));
		finish();
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alarm, menu);
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
