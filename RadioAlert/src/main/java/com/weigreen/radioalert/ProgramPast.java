package com.weigreen.radioalert;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ProgramPast extends Fragment
{
	private String[][] programList;
	
	private ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
	private ListView programListView;
	private SimpleAdapter simpleAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState)
	{
		return inflater.inflate(R.layout.program_past_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		
		programListView = (ListView)getView().findViewById(R.id.programPastListView);
		
		programList = SQLBridge.getPinewavePast(getActivity().getApplicationContext(), new Date());
		
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
			Toast.makeText(getActivity().getApplicationContext(), "立即收聽 " + programList[position][4], Toast.LENGTH_SHORT).show();
			
			Intent intent = new Intent();
			intent.setClass(getActivity(), RadioActivity.class);
			
			intent.putExtra("id", programList[position][1]);
			intent.putExtra("date", programList[position][2]);
			intent.putExtra("time", programList[position][3]);
			intent.putExtra("name", programList[position][4]);
			intent.putExtra("dj", programList[position][5]);
			
			System.out.printf("Send : id is %s, date is %s, time is %s, name is %s, dj is %s\n", programList[position][0], programList[position][1], programList[position][2], programList[position][3], programList[position][4]);
			
			startActivity(intent);
		}
	};

}
