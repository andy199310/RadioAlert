package com.weigreen.radioalert;

import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import java.io.IOException;

/**
 * Created by Green on 2013/5/28.
 */
public class LivePinewaveFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState)
    {
        return inflater.inflate(R.layout.live_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Switch toggle = (Switch) getActivity().findViewById(R.id.live_switch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Intent service = new Intent(getActivity().getApplication().getApplicationContext(), RadioService.class);
                    service.putExtra(RadioService.SERVICE_TAG, RadioService.SERVICE_START);
                    getActivity().getApplication().getApplicationContext().startService(service);
                } else {
                    // The toggle is disabled
                    Intent service = new Intent(getActivity().getApplication().getApplicationContext(), RadioService.class);
                    service.putExtra(RadioService.SERVICE_TAG, RadioService.SERVICE_STOP);
                    getActivity().getApplication().getApplicationContext().startService(service);
                }
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

    }
}