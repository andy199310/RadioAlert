package com.weigreen.radioalert;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFragment extends Fragment
{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState)
	{
		return inflater.inflate(R.layout.home, container, false);
	}
}
