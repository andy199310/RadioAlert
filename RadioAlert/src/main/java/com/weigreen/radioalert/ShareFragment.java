package com.weigreen.radioalert;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

@SuppressLint("NewApi") public class ShareFragment extends Fragment {

	private Spinner shareSpinner;
	private String[] share = {"�п�d" ,"email����", "²�T����", "FB����"};
	private ArrayAdapter<String> shareAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
		
		return inflater.inflate(R.layout.share_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);

        shareSpinner = (Spinner)getActivity().findViewById(R.id.shareSpinner);
        shareAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, share);
        shareAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shareSpinner.setAdapter(shareAdapter);
        
        shareSpinner.setOnItemSelectedListener(onItemSelectedListener);
	}
	
	private Spinner.OnItemSelectedListener onItemSelectedListener = new Spinner.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> adapterView, View view, int position,
				long id) {
			
			if (position == 1) {
				
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("text/html");
				intent.putExtra(Intent.EXTRA_SUBJECT, "�֨Ӹ�ڤ@�_ť�Q�ܹq�x");
				intent.putExtra(Intent.EXTRA_TEXT, "�֨Ӹ�ڤ@�_ť�Q�ܹq�x");

				startActivity(Intent.createChooser(intent, "Send Email"));
			}
			
			else if (position == 2) {
				
				Uri uri = Uri.parse("smsto:"); 
				Intent it = new Intent(Intent.ACTION_SENDTO, uri); 
				it.putExtra("sms_body", "�֨Ӹ�ڤ@�_ť�Q�ܹq�x"); 
				startActivity(it);
			}
			
			else if (position == 3) {
				//not yet~XD
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {
			
			
		}
	};
}
