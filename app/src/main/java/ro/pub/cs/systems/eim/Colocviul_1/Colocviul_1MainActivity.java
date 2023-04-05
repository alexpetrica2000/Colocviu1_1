package ro.pub.cs.systems.eim.Colocviul_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviul_1MainActivity extends AppCompatActivity {

    private Button north;
    private Button south;
    private Button east;
    private Button west;
    private Button next_activity;
    private EditText text_field;

    private int sum;

    private ButtonListener buttonListener = new ButtonListener();

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
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
                default:
                    break;
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
}