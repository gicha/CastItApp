package com.example.ge72.castit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.concurrent.ArrayBlockingQueue;


public class MainActivity extends AppCompatActivity {
    Button StartButton;
    Boolean isRunning;
    FlownPlayer rp;
    String hostname = "13.94.99.194";
    ArrayBlockingQueue<byte[]> datas = new ArrayBlockingQueue<>(10);
    ArrayBlockingQueue<Integer> datas_len = new ArrayBlockingQueue<>(10);
    ArrayBlockingQueue<Integer> datas_no = new ArrayBlockingQueue<>(10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rp = new FlownPlayer(hostname);
        rp.start();
//        StartButton = (Button) findViewById(R.id.start);
//        isRunning = false;
//
//        StartButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                if (!isRunning) {
//                    isRunning = true;
//                    StartButton.setText("Stop");
//                    rp = new FlownPlayer(hostname);
//                    rp.start();
//                } else {
//                    StartButton.setText("Start");
//                    isRunning = false;
//                    rp.setFinishFlag();
//                }
//            }
//        });

    }


}
