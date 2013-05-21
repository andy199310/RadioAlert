package com.weigreen.radioalert;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectFragment extends ListFragment {
	
	private String[] selection = {"預約節目", "隨選即聽", "分享好友", "更新節目表", "回到首頁"};
	private ArrayAdapter<String> selectionAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);

        selectionAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, selection);
        setListAdapter(selectionAdapter);
    }

    public void onListItemClick(ListView listView, View view, int position, long id) {
        
    	Toast.makeText(getActivity(), "您選擇項目是 : " + selection[position], Toast.LENGTH_SHORT).show();
    	
    	if (position == 0) {
			
    		ProgramFuture programFuture = new ProgramFuture();
    		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    		fragmentTransaction.replace(R.id.functionFragment, programFuture);
    		fragmentTransaction.commit();
		}
    	
    	else if (position == 1) {
    	
    		ProgramPast programPast = new ProgramPast();
    		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    		fragmentTransaction.replace(R.id.functionFragment, programPast);
    		fragmentTransaction.commit();
		}
    	
    	else if (position == 2) {
    		
    		ShareFragment shareFragment = new ShareFragment();
    		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    		fragmentTransaction.replace(R.id.functionFragment, shareFragment);
    		fragmentTransaction.commit();
		}
    	else if (position == 3) {
    		
    		SQLBridge.updatePinewave(getActivity().getApplicationContext());
		}
    	else if (position == 4) {
    		
    		HomeFragment homeFragment = new HomeFragment();
    		FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    		fragmentTransaction.replace(R.id.functionFragment, homeFragment);
    		fragmentTransaction.commit();
		}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	return inflater.inflate(R.layout.select_fragment, container, false);
    }
}
