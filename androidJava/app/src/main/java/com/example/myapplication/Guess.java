package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class Guess extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "Guess activity";
    ImageButton star;
    ImageButton star1;
    ImageButton star2;
    ImageButton star3;
    FloatingActionButton floatingActionButton;
    Animation animation;
    TextView bestScore;
    int MAX_COUNT = 4;
    int guess;
    int score;
    Intent intent;
    SharedPreferences myPreferences;

    @SuppressLint("SetTextI18n")
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
        bestScore = findViewById(R.id.bestScore);
        intent = new Intent(this, Guess.class);
        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        score = myPreferences.getInt("Score", score);
        bestScore.setText("Best score:"+score+"/" +MAX_COUNT);
    }

    public int getRandom() {
        Random rnd = new Random();
        return rnd.nextInt(4);
    }


    @SuppressLint("SetTextI18n")
    public void setScore()
    {       if(guess > 0) {
            SharedPreferences.Editor myEditor = myPreferences.edit();
            myEditor.putInt("Score", guess);
            myEditor.commit();
            bestScore.setText("Best score:"+guess+"/" +MAX_COUNT);
        }else
            {
            bestScore.setText("Best score:"+score+"/" +MAX_COUNT);
        }
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
                    guess++;
                    setScore();
                }
                star.setVisibility(View.VISIBLE);
                star.setEnabled(false);
                break;

            case R.id.star1:
                if(rnd == 1) {
                    star1.setVisibility(View.GONE);
                    star1.setImageResource(R.mipmap.ic_apple);
                    star1.setBackgroundColor(Color.TRANSPARENT);
                    star1.startAnimation(animation);
                    guess++;
                    setScore();
                }
                star1.setVisibility(View.VISIBLE);
                star1.setEnabled(false);
                break;

            case R.id.star2:
                if(rnd == 2) {
                    star2.setVisibility(View.GONE);
                    star2.setImageResource(R.mipmap.ic_apple);
                    star2.setBackgroundColor(Color.TRANSPARENT);
                    star2.startAnimation(animation);
                    guess++;
                    setScore();
                }
                star2.setVisibility(View.VISIBLE);
                star2.setEnabled(false);
                break;

            case R.id.star3:
                if(rnd == 3) {
                    star3.setVisibility(View.GONE);
                    star3.setImageResource(R.mipmap.ic_apple);
                    star3.setBackgroundColor(Color.TRANSPARENT);
                    star3.startAnimation(animation);
                    guess++;
                    setScore();
                }
                star3.setVisibility(View.VISIBLE);
                star3.setEnabled(false);
                break;

            default:
                break;
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(getIntent());
            }
        });
    }
}