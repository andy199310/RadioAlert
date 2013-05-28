package com.weigreen.radioalert;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

/**
 * Created by Green on 2013/5/28.
 */
public class RadioService extends Service {

    public static final String SERVICE_TAG = "kind";

    public static final int SERVICE_START = 0;
    public static final int SERVICE_PAUSE = 1;
    public static final int SERVICE_STOP = 2;

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource("http://140.115.183.156:8000");;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int command = intent.getIntExtra(SERVICE_TAG, 404);

        switch(command){
            case 0:
                try {
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                mediaPlayer.pause();
                break;
            case 2:
                mediaPlayer.stop();
                break;
        }
        return START_STICKY;
    }
}
