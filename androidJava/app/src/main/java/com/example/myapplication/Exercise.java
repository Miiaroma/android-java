package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Exercise extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Excercise activity";
    Button button_xamk;
    Button button_laurea;
    Button button_lab;
    Button button_jamk;
    Button button_tamk;
    Button button_score;
    int guess;


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
        //score = findViewById(R.id.bestScore);
    }

    /*public void setScore()
    {       if(guess > 0)
    {
        score.setText("Score:"score")
    }*/

    /*public void OpenActivityScore(){
        Intent i = new Intent(getActivity(), Score.class);
        startActivity(i);
    }*/



    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_xamk:
                button_xamk.setEnabled(false);
                guess++;
                //setScore();
                break;

            case R.id.button_laurea:
                button_laurea.setEnabled(false);
                guess++;
                //setScore();
                break;

            case R.id.button_lab:
                button_lab.setEnabled(true);
                button_score.setVisibility(View.VISIBLE);
                guess++;
                //setScore();
                break;

            case R.id.button_jamk:
              button_jamk.setEnabled(false);
                guess++;
                //etScore();
                break;

            case R.id.button_tamk:
                button_tamk.setEnabled(false);
                guess++;
                //setScore();
                break;

            case R.id.button_score:
                //OpenActivityScore();
                break;
        }
    }










}