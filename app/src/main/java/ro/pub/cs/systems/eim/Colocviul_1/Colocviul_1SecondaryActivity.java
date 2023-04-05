package ro.pub.cs.systems.eim.Colocviul_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Colocviul_1SecondaryActivity extends AppCompatActivity {

    private Button register;
    private Button cancel;
    private EditText secondary_field;


    private ButtonListener nextActivityButtonListener = new ButtonListener();

    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.register_button) {
                setResult(0, new Intent());
                finish();
            }
            if (view.getId() == R.id.cancel_button) {
                setResult(1, new Intent());
                finish();
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colocviul1_secondary);

        register = (Button) findViewById(R.id.register_button);
        cancel = (Button) findViewById(R.id.cancel_button);

        secondary_field = (EditText) findViewById(R.id.text_field_secondary);

        Intent intent = getIntent();
        if (intent != null) {
            String text = intent.getStringExtra("TEXT");
            if (secondary_field != null) {
                secondary_field.setText(text);
            }
        }

        register.setOnClickListener(nextActivityButtonListener);
        cancel.setOnClickListener(nextActivityButtonListener);
    }
}