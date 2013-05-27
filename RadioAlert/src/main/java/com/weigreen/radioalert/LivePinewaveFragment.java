package com.weigreen.radioalert;

import android.app.Fragment;
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

    private MediaPlayer mediaPlayer;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState)
    {
        return inflater.inflate(R.layout.live_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource("http://140.115.183.156:8000");;
        } catch (IOException e) {
            e.printStackTrace();
        }

        Switch toggle = (Switch) getActivity().findViewById(R.id.live_switch);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    start();
                } else {
                    // The toggle is disabled
                    stop();
                }
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

    }


    public void start(){
        if (!mediaPlayer.isPlaying()){
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
}