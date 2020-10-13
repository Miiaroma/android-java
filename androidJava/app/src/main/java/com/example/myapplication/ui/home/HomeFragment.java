package com.example.myapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Guess;
import com.example.myapplication.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    public static final String TAG ="MyAppMessage";
    Button button;
    TextView textV;
    Button buttonGame;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        button = root.findViewById(R.id.button);
        button.setOnClickListener(this);
        buttonGame = root.findViewById(R.id.buttonGame);
        buttonGame.setOnClickListener(this);
        Log.i(TAG, "activating play view");

        textV = root.findViewById(R.id.textV);
        textV.getVisibility();

        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    public void setTextVisibility(){
        if(textV.getVisibility() == View.VISIBLE) {
            textV.setVisibility(View.INVISIBLE);
        } else {
            textV.setVisibility(View.VISIBLE);
        }
    }

    public void OpenActivityGuess(){
        Intent i = new Intent(getActivity(), Guess.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button:
                Log.e("test", "Button has been clicked");
                setTextVisibility();
                break;

            case R.id.buttonGame:
                OpenActivityGuess();
                break;
        }
    }
}