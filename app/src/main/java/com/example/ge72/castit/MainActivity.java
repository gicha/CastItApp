package com.example.ge72.castit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button StartButton;
    Boolean isRunning;
    ReceiverPlayer rp;
    String hostname = "13.94.99.194";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StartButton = (Button) findViewById(R.id.start);
        isRunning = false;

        StartButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isRunning) {
                    isRunning = true;
                    StartButton.setText("Stop");
                    rp = new ReceiverPlayer(hostname);
                    rp.start();
                } else {
                    StartButton.setText("Start");
                    isRunning = false;
                    rp.setFinishFlag();
                }
            }
        });

    }


}
