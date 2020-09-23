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
    TextView bestScore;
    int MAX_COUNT = 4;
    int guess;
    Intent intent;
    //File fos;

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
        File internalStorageDir = getFilesDir();
        File score = new File(internalStorageDir, "score.csv");
    }

    public int getRandom() {
        Random rnd = new Random();
        return rnd.nextInt(4);
    }

    public void setScore()
    {

        bestScore.setText("Best score:"+guess+"/" +MAX_COUNT);
    }

    /*public void write(
    {
        // Create file output stream
        fos = new FileOutputStream(score);
        // Write a line to the file
        fos.write(guess.getBytes());
        // Close the file output stream
        fos.close();
    }*/

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

                break;

            case R.id.star1:
                if(rnd == 1) {
                    star1.setVisibility(View.GONE);
                    star1.setImageResource(R.mipmap.ic_apple);
                    star1.setBackgroundColor(Color.WHITE);
                    star1.startAnimation(animation);
                    guess++;
                    setScore();

                }
                star1.setVisibility(View.VISIBLE);

                break;

            case R.id.star2:
                if(rnd == 2) {
                    star2.setVisibility(View.GONE);
                    star2.setImageResource(R.mipmap.ic_apple);
                    star2.setBackgroundColor(Color.WHITE);
                    star2.startAnimation(animation);
                    guess++;
                    setScore();
                }
                star2.setVisibility(View.VISIBLE);

                break;

            case R.id.star3:
                if(rnd == 3) {
                    star3.setVisibility(View.GONE);
                    star3.setImageResource(R.mipmap.ic_apple);
                    star3.setBackgroundColor(Color.WHITE);
                    star3.startAnimation(animation);
                    guess++;
                    setScore();
                }
                star3.setVisibility(View.VISIBLE);

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