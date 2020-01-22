package com.example.counterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static long TIME_IN_MILLI_SECONDS = 60000;

    EditText etTime;
    TextView tvTime;
    Button btnStartPause, btnReset;
    CountDownTimer countDownTimer;
    Boolean Running;

    long Time_Left_In_Milli_Seconds = TIME_IN_MILLI_SECONDS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTime = findViewById(R.id.etTime);
        tvTime = findViewById(R.id.tvTime);
        btnStartPause = findViewById(R.id.btnStartPause);
        btnReset = findViewById(R.id.btnReset);
        //     countDownTimer=findViewById(R.id.countDownTimer);
        //   Editable time=etTime.getText();

        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Running) {
                    pauseTimer();
                } else if (!Running) {
                    startTimer();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    public void pauseTimer() {
        countDownTimer.cancel();
        Running = false;
        btnReset.setVisibility(View.INVISIBLE);
    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(Time_Left_In_Milli_Seconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Time_Left_In_Milli_Seconds = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
               Running=false;
               btnStartPause.setText("Start");
               btnStartPause.setText(View.INVISIBLE);
               btnReset.setVisibility(View.VISIBLE);
            }
        }.start();
        Running = true;
        btnStartPause.setText("pause");
        btnReset.setVisibility(View.INVISIBLE);
    }

    public void resetTimer() {
        Time_Left_In_Milli_Seconds = TIME_IN_MILLI_SECONDS;
        updateCountDownText();
        btnReset.setVisibility(View.INVISIBLE);
        btnStartPause.setVisibility(View.VISIBLE);
    }

    public void updateCountDownText() {
        int minutes = (int) (Time_Left_In_Milli_Seconds / 60 / 1000);
        int seconds = (int) ((Time_Left_In_Milli_Seconds / 1000) % 60);

        String TimeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvTime.setText(TimeLeftFormatted);
    }
}
