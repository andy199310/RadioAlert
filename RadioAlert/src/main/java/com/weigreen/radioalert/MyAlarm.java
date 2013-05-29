package com.weigreen.radioalert;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyAlarm extends Activity 
{
	private String id;
	private String date;
	private String time;
	private String name;
	private String dj;
	
	private TextView notificationTextView;
	private Button listenButton;
	private Button waitButton;
	
    public static final int NOTIFICATION_ID=1; 
    
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        
        Intent newIntent = getIntent();
        
        id = newIntent.getStringExtra("id");
        date = newIntent.getStringExtra("date");
        time = newIntent.getStringExtra("time");
        name = newIntent.getStringExtra("name");
        dj = newIntent.getStringExtra("dj");
        
        System.out.printf("Alarm Receive : id is %s, date is %s, time is %s, name is %s, dj is %s", id, date, time, name, dj);
        
        notificationTextView = (TextView)findViewById(R.id.notificationTextView);
		listenButton = (Button)findViewById(R.id.listenButton);
		waitButton = (Button)findViewById(R.id.waitButton);
        
		notificationTextView.setText("您預定的 \"" + name + "\" 時間到了\n請問您要收聽嗎?");
		
		
        final NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        
        Notification notification=new Notification();
        
        // notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "20");
        
        notificationManager.notify(NOTIFICATION_ID, notification);
        
        
        listenButton.setOnClickListener(new Button.OnClickListener() 
        {
            @Override
            public void onClick(View view) 
            {
            	// listen!!
            	Log.d("listenButton", "click listen button");
            	
            	Intent listenIntent = new Intent();
            	listenIntent.putExtra("id", id);
            	listenIntent.putExtra("date", date);
            	listenIntent.putExtra("time", time);
            	listenIntent.putExtra("name", name);
            	listenIntent.putExtra("dj", dj);
            	
            	listenIntent.setClass(MyAlarm.this, RadioActivity.class);
    			startActivity(listenIntent);
            	
            	notificationManager.cancel(NOTIFICATION_ID);
                finish();
            }
        });
        
        
        waitButton.setOnClickListener(new Button.OnClickListener()
        {
        	@Override
        	public void onClick(View view)
        	{
        		notificationManager.cancel(NOTIFICATION_ID);
                finish();
        	}
        });
    }
    
}