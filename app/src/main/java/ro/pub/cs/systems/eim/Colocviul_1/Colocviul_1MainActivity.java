package ro.pub.cs.systems.eim.Colocviul_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Colocviul_1MainActivity extends AppCompatActivity {

    private Button north;
    private Button south;
    private Button east;
    private Button west;
    private Button next_activity;
    private EditText text_field;
    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;
    private IntentFilter startedServiceIntentFilter;
    private int sum;

    private ButtonListener buttonListener = new ButtonListener();

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.east_button:
                    text_field.setText(text_field.getText().toString() + " ," + "East");
                    sum++;
                    break;
                case R.id.west_button:
                    text_field.setText(text_field.getText().toString() + " ," + "West");
                    sum++;
                    break;

                case R.id.south_button:
                    text_field.setText(text_field.getText().toString() + " ," + "South");
                    sum++;
                    break;
                case R.id.north_button:
                    text_field.setText(text_field.getText().toString() + " ," + "North");
                    sum++;
                    break;
                case R.id.navigate_button:
                    Intent intent = new Intent(getApplicationContext(), Colocviul_1SecondaryActivity.class);
                    intent.putExtra("TEXT", text_field.getText().toString());
                    text_field.setText("");
                    sum = 0;
                    startActivityForResult(intent, 2022);

                default:
                    break;
            }

            if (sum == 4) {
                Intent serviceStart = new Intent(getApplicationContext(), StartedService.class);

                serviceStart.putExtra("SERVICE_TEXT", text_field.getText().toString());
                startService(serviceStart);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviul_1_main);

        if (savedInstanceState == null)
            sum = 0;

        north = (Button) findViewById(R.id.north_button);
        north.setOnClickListener(buttonListener);
        south = (Button) findViewById(R.id.south_button);
        south.setOnClickListener(buttonListener);
        east = (Button) findViewById(R.id.east_button);
        east.setOnClickListener(buttonListener);
        west = (Button) findViewById(R.id.west_button);
        west.setOnClickListener(buttonListener);
        next_activity = (Button) findViewById(R.id.navigate_button);
        next_activity.setOnClickListener(buttonListener);

        text_field = (EditText) findViewById(R.id.text_field);

        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver();
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction("LOG FROM SERVICE");

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("value", sum);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


        if (savedInstanceState.containsKey("value")) {
            sum = savedInstanceState.getInt("value");
            Log.d("VALUE", String.valueOf(sum));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == 2022) {
            if (resultCode == 0) {
                Toast.makeText(this, "The activity returned with result OK", Toast.LENGTH_LONG).show();
            }
            if (resultCode == 1) {
                Toast.makeText(this, "The activity returned with result Cancel", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.registerReceiver(startedServiceBroadcastReceiver,startedServiceIntentFilter);

    }

    @Override
    protected void onPause() {
        this.unregisterReceiver(startedServiceBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent stopService = new Intent(getApplicationContext(), StartedService.class);
        stopService(stopService);


        super.onDestroy();
    }
}