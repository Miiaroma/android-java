package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.Random;

public class Guess extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Guess activity";
    ImageButton star;
    ImageButton star1;
    ImageButton star2;
    ImageButton star3;
    FloatingActionButton floatingActionButton;
    Animation animation;
    TextView totalWins;
    TextView scoreView;
    int currentWins;
    int highScore;
    private String best = "HighScore";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        Log.i(TAG, "Guess Activity launched!");
        star = findViewById(R.id.star);
        star.setOnClickListener(this);
        star1 = findViewById(R.id.star1);
        star1.setOnClickListener(this);
        star2 = findViewById(R.id.star2);
        star2.setOnClickListener(this);
        star3 = findViewById(R.id.star3);
        star3.setOnClickListener(this);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(this);
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.roundanimation);
        totalWins = findViewById(R.id.win);
        scoreView = findViewById(R.id.viewScore);
        Intent intent = new Intent(this, Guess.class);
        File internalStorageDir = getFilesDir();
        File score = new File(internalStorageDir, "acore.csv");
        /*intent.putExtra(best, highScore);

        Bundle extras = getIntent().getExtras();
        if( extras == null) {
            highScore = 0;
        } else {
            highScore = extras.getInt(best);
            scoreView.setText(Integer.toString(highScore));
        }*/
    }
    public void setHighScore(int score) {
        Log.i("score", Integer.toString(score));
        if(score > highScore) {
            highScore = score;
            scoreView.setText(Integer.toString(score));
        }
    }
    public void setWinnings(){
        String winnings = Integer.toString(currentWins);
        totalWins.setText(winnings);

    }
    public void zeroWins() {
        currentWins = 0;
        String lost = Integer.toString(currentWins);
        totalWins.setText(lost);
    }

    public int getRandom() {
        Random rnd = new Random();
        return rnd.nextInt(4);
    }

    public void onClick(View view) {
        int rnd = getRandom();

        switch (view.getId()){
            case R.id.star:
                if(rnd == 0) {
                    star.setVisibility(View.GONE);
                    star.setImageResource(R.mipmap.ic_apple);
                    star.setBackgroundColor(Color.WHITE);
                    star.startAnimation(animation);
                    currentWins++;
                    setWinnings();
                    setHighScore(currentWins);
                }
                star.setVisibility(View.VISIBLE);
                currentWins = 0;
                zeroWins();
                break;

            case R.id.star1:
                if(rnd == 1) {
                    star1.setVisibility(View.GONE);
                    star1.setImageResource(R.mipmap.ic_apple);
                    star1.setBackgroundColor(Color.WHITE);
                    star1.startAnimation(animation);
                    currentWins++;
                    setWinnings();
                    setHighScore(currentWins);
                }
                star1.setVisibility(View.VISIBLE);
                currentWins = 0;
                zeroWins();
                break;

            case R.id.star2:
                if(rnd == 2) {
                    star2.setVisibility(View.GONE);
                    star2.setImageResource(R.mipmap.ic_apple);
                    star2.setBackgroundColor(Color.WHITE);
                    star2.startAnimation(animation);
                    currentWins++;
                    setWinnings();
                    setHighScore(currentWins);
                }
                star2.setVisibility(View.VISIBLE);
                currentWins = 0;
                zeroWins();
                break;

            case R.id.star3:
                if(rnd == 3) {
                    star3.setVisibility(View.GONE);
                    star3.setImageResource(R.mipmap.ic_apple);
                    star3.setBackgroundColor(Color.WHITE);
                    star3.startAnimation(animation);
                    currentWins++;
                    setWinnings();
                    setHighScore(currentWins);
                }
                star3.setVisibility(View.VISIBLE);
                currentWins = 0;
                zeroWins();
                break;
            default:
                break;
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getIntent());
                //intent.putExtra(best, highScore);
            }
        });
    }
}