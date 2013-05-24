package com.weigreen.radioalert;

import android.content.Context;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Green on 2013/5/23.
 */
public class RadioPinewave {

    //http://www.hrupin.com/2011/02/example-of-streaming-mp3-mediafile-with-android-mediaplayer-class
    private final String PINEWAVE_URL = "http://broadcast.pinewave.tw:8000";

    //The main player
    private MediaPlayer mediaPlayer;

    private Context context;



    public RadioPinewave(Context context){
        this.context = context;
        mediaPlayer = new MediaPlayer();
        //mediaPlayer.setOnBufferingUpdateListener(context);
        //mediaPlayer.setOnCompletionListener(context);
        try {
            mediaPlayer.setDataSource(PINEWAVE_URL);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        mediaPlayer.start();
    }

}
