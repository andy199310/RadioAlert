package com.weigreen.radioalert;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.io.IOException;

/**
 * Created by Green on 2013/5/28.
 */
public class LivePinewaveFragment extends Fragment {

    private MediaPlayer mediaPlayer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource("http://140.115.183.156:8000");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ToggleButton toggle = (ToggleButton) getActivity().findViewById(R.id.live_switch);
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

    public void start(){
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    public void stop(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
}