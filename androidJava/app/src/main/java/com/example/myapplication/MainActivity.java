package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG ="MyAppMessage";
    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        Log.i(TAG, "activating play view");

        // Get a string resource from your app's Resources
        String hello = getResources().getString(R.string.text_name);

        // Or supply a string resource to a method that requires a string
        TextView textView = new TextView(this);
        textView.setText(R.string.text_name);
        //greetingsText.getVisibility();
        //greetingsText.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                Log.e("test", "Button has been clicked");
                break;
        }
    }
    } 
