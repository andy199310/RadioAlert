package com.weigreen.radioalert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PlayReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		System.out.println("onReceive");
		
		String id = intent.getStringExtra("id");
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String name = intent.getStringExtra("name");
        String dj = intent.getStringExtra("dj");
        
        System.out.printf("Receive : id is %s, date is %s, time is %s, name is %s, dj is %s\n", id, date, time, name, dj);
		
        
        System.out.println("test");
        
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
        intent.setClass(context, MyAlarm.class);  
        context.startActivity(intent);
	}

}
