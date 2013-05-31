package com.weigreen.radioalert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ProgramFuture extends Fragment
{
	private String[][] programList;
	
	private ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
	private ListView programListView;
	private SimpleAdapter simpleAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState)
	{
		return inflater.inflate(R.layout.program_future_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		
		programListView = (ListView)getActivity().findViewById(R.id.programFutureListView);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);

		programList = SQLBridge.getPinewaveFuture(getActivity().getApplicationContext(), cal.getTime());
		
		
		
		
		for(int i = 0; i < programList.length; i++)
		{
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("name", programList[i][4]);
			item.put("time", programList[i][2] + "  " + programList[i][3]);
			arrayList.add(item);
		}
		
		simpleAdapter = new SimpleAdapter(getActivity(), arrayList, R.layout.program, new String[]{"name", "time"}, new int[]{R.id.textView1, R.id.textView2});
		
		programListView.setAdapter(simpleAdapter);
		programListView.setTextFilterEnabled(true);
		programListView.setOnItemClickListener(programListViewOnItemClickListener);
	
		
	}
	
	
	
	
	
	private AdapterView.OnItemClickListener programListViewOnItemClickListener = new AdapterView.OnItemClickListener() 
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Date nowDate = new Date();
			Date chooseDate = new Date();
			try
			{
				chooseDate = simpleDateFormat.parse(programList[position][2] + " " + programList[position][3]);
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
			
			System.out.println("nowDate is " + simpleDateFormat.format(nowDate));
			System.out.println("chooseDate is " + simpleDateFormat.format(chooseDate));
			
			//Long nowDateUnix = nowDate.getTime() + 8 * 60 * 60 * 1000;
			 Long nowDateUnix = nowDate.getTime();// +8 time zone
			Long chooseDateUnix = chooseDate.getTime();
			int timeDelay = (int) (chooseDateUnix - nowDateUnix);
			
			System.out.println("timeDelay is " + timeDelay);

            Log.d("delay", String.valueOf(timeDelay));
			

            Calendar calendar = Calendar.getInstance();
            //calendar.add(Calendar.SECOND, 5);
            calendar.add(Calendar.SECOND, timeDelay / 1000);

            Intent intent = new Intent();
            intent.setClass(getActivity(), PlayReceiver.class);

            intent.putExtra("id", programList[position][1]);
			intent.putExtra("date", programList[position][2]);
			intent.putExtra("time", programList[position][3]);
			intent.putExtra("name", programList[position][4]);
			intent.putExtra("dj", programList[position][5]);
			
			System.out.printf("Send : id is %s, date is %s, time is %s, name is %s, dj is %s\n", programList[position][0], programList[position][1], programList[position][2], programList[position][3], programList[position][4]);
			
			PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 1, intent, PendingIntent.FLAG_ONE_SHOT);
			
			AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
			
			alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

			Toast.makeText(getActivity().getApplicationContext(), "You choose:" + programList[position][4], Toast.LENGTH_SHORT).show();
			
			
		}
	};

}
