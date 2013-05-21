package com.weigreen.radioalert;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RadioActivity extends Activity {
	
	private MediaPlayer mMediaPlayer;

	private String id;
	private String date;
	private String time;
	private String name;
	private String dj;
	
	private CountDownTimer timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio);
		
		Intent intent = getIntent();
		
		id = intent.getStringExtra("id");
		date = intent.getStringExtra("date");
		time = intent.getStringExtra("time");
		name = intent.getStringExtra("name");
		dj = intent.getStringExtra("dj");
		
		TextView name = (TextView) findViewById(R.id.textView_name);
		name.setText(this.name);
		
		TextView dj = (TextView) findViewById(R.id.textView_DJ);
		dj.setText(this.dj);
		
		try { 
			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mMediaPlayer.setDataSource("http://radio.pinewave.tw/file/radio/"+id+".mp3");
			mMediaPlayer.prepare(); 
		}catch (Exception e) {
			Log.e("err", e.toString());
		}
		timer = new CountDownTimer(10000, 500) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
				int hi = (int) (progressBar.getMax() - (10000 - millisUntilFinished)*progressBar.getMax());
				progressBar.setProgress(hi);
			}
			
			@Override
			public void onFinish() {
				ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
				progressBar.setVisibility(View.INVISIBLE);
				
				ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
				progressBar2.setVisibility(View.INVISIBLE);
				
				TextView loading = (TextView) findViewById(R.id.textView_loading);
				loading.setVisibility(View.INVISIBLE);
				
				Button button = (Button) findViewById(R.id.button_start);
				button.setClickable(true);
				
			}
		};
		timer.start();
		
		TextView loading = (TextView) findViewById(R.id.textView_loading);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.main);
		loading.startAnimation(animation);
	}
	
	
	public void pause(View view){
		mMediaPlayer.pause();
	}
	
	public void start(View view){
		mMediaPlayer.start();
		Button button = (Button) findViewById(R.id.button_pause);
		button.setClickable(true);
		
		button = (Button) findViewById(R.id.button_stop);
		button.setClickable(true);
	}
	
	public void stop(View view){
		mMediaPlayer.stop();
		Button button = (Button) findViewById(R.id.button_stop);
		button.setClickable(false);
	}
	
	
	public void update(View view) {
		SQLBridge.updatePinewave(getApplicationContext());
	}

	public void look(View view) {
		String[][] data = SQLBridge.getPinewave(getApplicationContext());
	}

}
