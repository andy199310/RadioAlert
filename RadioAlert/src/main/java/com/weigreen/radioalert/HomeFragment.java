package com.weigreen.radioalert;


import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


public class HomeFragment extends Fragment
{
    /*
    private LinearLayout linearLayout;

    private static final int MENU_PLAY_MUSIC = Menu.FIRST;
    private static final int MENU_ABOUT = Menu.FIRST + 1;
    private static final int MENU_EXIT = Menu.FIRST + 2;
    */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState)
	{



        /*   context menu  By:wind
        Log.d("HomeFragment.java", "try to registerForContextMenu()");
        View root = inflater.inflate(R.layout.home, container, false); // find on internet
        registerForContextMenu(root.findViewById(R.id.home));
        // linearLayout = (LinearLayout)getActivity().findViewById(R.id.home);
        // registerForContextMenu(linearLayout);
        Log.d("HomeFragment.java", "registerForContextMenu success");
        */

		return inflater.inflate(R.layout.home, container, false);
	}

    /*
    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo)
    {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);

        Log.d("HomeFragment.java", "onCreateContextMenu()");


        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.linear_layout_context_menu, contextMenu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem)
    {
        Log.d("HomeFragment.java", "onContextItemSelected()");

        Resources resources = getActivity().getResources();


        switch(menuItem.getItemId())
        {
            case R.id.menuItemPlayMusic:
                Toast.makeText(getActivity(), resources.getString(R.string.play_music_message), Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuItemAbout:
                Toast.makeText(getActivity(), resources.getString(R.string.about_message), Toast.LENGTH_SHORT).show();
                break;

            case R.id.menuItemExit:
                getActivity().finish();
                break;
        }

        return super.onOptionsItemSelected(menuItem);
    }
    */
}
