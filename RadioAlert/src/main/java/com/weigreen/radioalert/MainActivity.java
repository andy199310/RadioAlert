package com.weigreen.radioalert;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.view.Menu;

public class MainActivity extends Activity {

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_main);
		
		
		HomeFragment homeFragment = new HomeFragment();
		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.functionFragment, homeFragment);
		fragmentTransaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

}
