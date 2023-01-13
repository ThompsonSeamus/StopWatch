package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stopwatch.databinding.ActivityMainBinding;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    //binding to help me not write .setViewID or whatever
    private ActivityMainBinding binding;
    //global variables
    private WatchTime watchTime;
    private volatile long timeInMilliSeconds = 0L;
    private boolean stopped = true;
    ScheduledFuture<?> repeatedFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set action bar to grey
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.orange)));

        //watchtime
        watchTime = new WatchTime();

        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });

        binding.ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

    }


    //methods for the buttons
    private void startStop() {
        if(stopped){
            long delay = 0L;//change to make time more accurate
            stopped = false;
            binding.startButton.setText(R.string.real_stop);
            binding.ResetButton.setEnabled(false);

            watchTime.setStartTime(SystemClock.uptimeMillis() + delay);
            repeatedFuture = Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(
                    updateTimerRunnable, delay, 1, TimeUnit.MILLISECONDS);
        }
        else{
            stopped = true;
            binding.startButton.setText(R.string.start);

            watchTime.addStoredTime(timeInMilliSeconds);

            repeatedFuture.cancel(true);
            binding.ResetButton.setEnabled(true);
        }
    }

    private void reset() {
        watchTime.reset();
        timeInMilliSeconds = 0L;
        binding.timeText.setText(R.string.stop_watch_start_time);
    }

    //runnable
    private Runnable updateTimerRunnable = new Runnable() {
        @Override
        public void run() {
            timeInMilliSeconds = SystemClock.uptimeMillis() - watchTime.getStartTime();
            watchTime.setTimeUpdate(watchTime.getStoredTime() + timeInMilliSeconds);
            int time = (int)(watchTime.getTimeUpdate() / 1000);
            int minutes = time/60;
            int seconds = time%60;
            int hundreths = (int)(watchTime.getTimeUpdate() % 1000)/10;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binding.timeText.setText(
                            String.format(
                                    Locale.US, "%02d : %02d : %02d", minutes, seconds, hundreths));
                }
            });
        }
    };
}