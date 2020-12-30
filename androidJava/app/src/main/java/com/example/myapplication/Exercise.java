package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Exercise extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private static final String TAG = "Exercise activity";
    Button button_xamk;
    Button button_laurea;
    Button button_lab;
    Button button_jamk;
    Button button_tamk;
    Button button_score;
    SeekBar seekBar;
    int progressChangedValue;
    int progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        button_xamk = findViewById(R.id.button_xamk);
        button_xamk.setOnClickListener(this);
        button_laurea = findViewById(R.id.button_laurea);
        button_laurea.setOnClickListener(this);
        button_lab = findViewById(R.id.button_lab);
        button_lab.setOnClickListener(this);
        button_jamk = findViewById(R.id.button_jamk);
        button_jamk.setOnClickListener(this);
        button_tamk = findViewById(R.id.button_tamk);
        button_tamk.setOnClickListener(this);
        button_score = findViewById(R.id.button_score);
        button_score.getVisibility();
        button_score.setVisibility(View.INVISIBLE);
        button_score.setOnClickListener(this);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
    }

    // perform seek bar change listener event used for getting the progress value
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressChangedValue = progress;
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub
        seekBar.setProgress(progressChangedValue);
    }
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Toast.makeText(Exercise.this, "Correct" + progressChangedValue,
                Toast.LENGTH_SHORT).show();
    }

    private void setProgressValue(final int progress) {

        // set the progress
        seekBar.setProgress(progress);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress + 1);
            }
        });
        thread.start();
    }

    public void OpenActivityScore(){
        Intent i = new Intent(this, Score.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_xamk:
                button_xamk.setEnabled(false);
                onStartTrackingTouch(seekBar);
                setProgressValue(progress);
                //setScore();
                break;

            case R.id.button_laurea:
                button_laurea.setEnabled(false);
                onStartTrackingTouch(seekBar);
                setProgressValue(progress);
                //setScore();
                break;

            case R.id.button_lab:
                button_lab.setEnabled(true);
                onStopTrackingTouch(seekBar);
                setProgressValue(progress);
                button_score.setVisibility(View.VISIBLE);
                //setScore();
                break;

            case R.id.button_jamk:
              button_jamk.setEnabled(false);
                onStartTrackingTouch(seekBar);
                setProgressValue(progress);
                //setScore();
                break;

            case R.id.button_tamk:
                button_tamk.setEnabled(false);
                onStartTrackingTouch(seekBar);
                setProgressValue(progress);
                //setScore();
                break;

            case R.id.button_score:
                OpenActivityScore();
                break;
        }
    }
}