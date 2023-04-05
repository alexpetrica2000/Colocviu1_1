
package ro.pub.cs.systems.eim.Colocviul_1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

public class ProcessingThread extends Thread {

    private Context context;

    public ProcessingThread(Context context) {
        this.context = context;

    }

    @Override
    public void run() {
        Log.d(String.valueOf(0), "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        sleep();
        sendMessage();
    }

    private void sendMessage() {
        Intent intent = new Intent();


        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        String value = "" + year+ "/" + month + "/"+ day+  " " + hour + ":"+minute;

        intent.setAction("LOG FROM SERVICE");
        intent.putExtra("DateHour", value);

        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            Log.e("0", interruptedException.getMessage());
            interruptedException.printStackTrace();
        }
    }

}