package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    Button startButton, resetButton;
    Boolean started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        timeText = findViewById(R.id.timeText);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.ResetButton);
        started = false;

        //onClickListeners
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update UI thread (I think)
                if(!started){startButton.setText(R.string.real_stop);}
                else{startButton.setText(R.string.start);}

                //Executive services
                ExecutorService service = Executors.newSingleThreadExecutor();
                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        int milSecond = 100;
                        int second = milSecond/1000;


                    }
                });

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}