package com.rizhi.windowanimation;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tangtang on 15/5/22.
 */
public class MyServer extends Service {

    private Handler handler=new Handler();
    private Timer timer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer =new Timer();
        timer.schedule(new MyTimeTask(),0,500);
        return super.onStartCommand(intent, flags, startId);
    }

    private class MyTimeTask extends TimerTask {

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    MyWindowManager.showViewOnWindow(getApplicationContext());
                }
            });
        }
    }
}
