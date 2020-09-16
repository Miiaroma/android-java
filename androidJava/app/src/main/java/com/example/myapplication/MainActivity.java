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

        textView = findViewById(R.id.textView);
        textView.getVisibility();
    }

    public void setTextVisibility(){
       if(textView.getVisibility() == View.VISIBLE) {
           textView.setVisibility(View.INVISIBLE);
       } else {
           textView.setVisibility(View.VISIBLE);
       }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                Log.e("test", "Button has been clicked");
                setTextVisibility();
                break;
        }
    }
}
