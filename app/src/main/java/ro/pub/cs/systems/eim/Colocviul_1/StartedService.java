package ro.pub.cs.systems.eim.Colocviul_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class StartedService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(String.valueOf(0), "onCreate() method was invoked");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(String.valueOf(0), "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(String.valueOf(0), "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(String.valueOf(0), "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(String.valueOf(0), "onDestroy() method was invoked");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(String.valueOf(0), "onStartCommand() method was invoked");
        ProcessingThread processingThread = new ProcessingThread(this);
        processingThread.start();

        return START_REDELIVER_INTENT;
    }



}
