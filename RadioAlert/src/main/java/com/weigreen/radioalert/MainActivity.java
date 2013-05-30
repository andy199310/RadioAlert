package com.weigreen.radioalert;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LinearLayout linearLayout;

    private static final int MENU_PLAY_MUSIC = Menu.FIRST;
    private static final int MENU_ABOUT = Menu.FIRST + 1;
    private static final int MENU_EXIT = Menu.FIRST + 2;

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


        /*  old version of context menu in MainActivtiy   By:wind
        Log.d("MainActivity.java", "try to registerForContextMenu()");
        linearLayout = (LinearLayout)homeFragment.getActivity().findViewById(R.id.home);
        registerForContextMenu(linearLayout);
        Log.d("MainActivity.java", "registerForContextMenu success");
        */


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
        Log.d("MainActivity.java", "onCreateOptionsMenu()");

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.linear_layout_context_menu, menu);

        return super.onCreateOptionsMenu(menu);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        Log.d("MainActivity.java", "onOptionsItemSelected()");

        Resources resources = getResources();


        switch(menuItem.getItemId())
        {
            case R.id.menuItemPlayMusic:
                Toast.makeText(this, resources.getString(R.string.play_music_message), Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuItemAbout:
                Toast.makeText(this, resources.getString(R.string.about_message), Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuItemExit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(menuItem);
    }




    /*   old version of context menu in MainActivtiy   By:wind
    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo)
    {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);

        Log.d("MainActivity.java", "onCreateContextMenu()");

        if(view == linearLayout)
        {
            if(contextMenu.size() == 0)
            {
                MenuInflater menuInflater = getMenuInflater();
                menuInflater.inflate(R.menu.linear_layout_context_menu, contextMenu);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem)
    {
        Log.d("MainActivity.java", "onContextItemSelected()");

        Resources resources = getResources();


        switch(menuItem.getItemId())
        {
            case R.id.menuItemPlayMusic:
                Toast.makeText(this, resources.getString(R.string.play_music_message), Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuItemAbout:
                Toast.makeText(this, resources.getString(R.string.about_message), Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuItemExit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(menuItem);
    }*/



}
