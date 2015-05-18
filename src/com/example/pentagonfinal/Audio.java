package com.example.pentagonfinal;

import java.io.File;
import java.io.IOException;








import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Audio extends ActionBarActivity {

	
	private static final String LOG_TAG = "Audio Recording";
	ImageButton start,stop;
	private MediaRecorder recorder =null;
	private int opFormats[]={MediaRecorder.OutputFormat.MPEG_4,
			MediaRecorder.OutputFormat.THREE_GPP
	};
	private String fileextn[]={".mp4",".3gp"};
	private int curFormat=0;

	TextView status;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audio);
		
		
		//replace yourActivity.this with your own activity or if you declared a context you can write context.getSystemService(Context.VIBRATOR_SERVICE); 
		
		start=(ImageButton)findViewById(R.id.recordbtn1);
    	stop=(ImageButton)findViewById(R.id.imageButton2);
    	status=(TextView)findViewById(R.id.textView1);
   
    	stop.setEnabled(false);
    	start.setOnClickListener(new btnClick());
    	stop.setOnClickListener(new btnClick());
	}

	
	
	class btnClick implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.recordbtn1:
				startRecording();
				break;
			case R.id.imageButton2:
				stopRecording();
				break;
			
			}
			
		}
	
	}
	
	
	public void onBackPressed()
	{
		super.onBackPressed();
		startActivity(new Intent(this,MainActivity.class));
		finish();
		
		
	}
	
	
	
	
	void startRecording() {
		// TODO Auto-generated method stub
		try{
		recorder =new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		recorder.setOutputFormat(opFormats[curFormat]);
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		recorder.setOutputFile(getFilePath());
		
		status=(TextView)findViewById(R.id.textView1);
		
			recorder.prepare();
			
			recorder.start();
			
			start.setEnabled(false);
			status.setText("Recording Started");
			stop.setEnabled(true);
		}catch (IOException e) {
            Log.e(LOG_TAG, "repare() failed");
		}
		
		
	}
	
	
	private String getFilePath(){
    	String filePath = Environment.getExternalStorageDirectory().getPath();
    	File file =new File(filePath,"Pentagon Log");
    	if(!file.exists())
    		file.mkdir();
    	
    	return (file.getAbsolutePath() + "/" + System.currentTimeMillis() + fileextn[curFormat]);
    	
    }
	
	
	public void stopRecording() {
		// TODO Auto-generated method stub
		status.setText("");
		try
		{
		if(recorder!=null){
			//recorder.stop();
			recorder.reset();
			recorder.release();
			
			recorder=null;
			
			
		}
		else
		{

			Toast tt=Toast.makeText(this, "No Recording Started", Toast.LENGTH_SHORT);
			tt.show();
		}
		start.setEnabled(true);
		stop.setEnabled(false);
		}
		catch(Exception e)
		{
		  Toast ttt =Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT);
		  ttt.show();
		}
		}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.audio, menu);
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
