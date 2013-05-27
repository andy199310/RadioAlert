package com.weigreen.radioalert;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

import java.io.IOException;

/**
 * Created by Green on 2013/5/28.
 */
public class LivePinewave extends Activity {


    private MediaPlayer mediaPlayer;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource("http://140.115.183.156:8000");
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}