package com.example.myapplication.ui.notifications;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class NotificationsFragment extends Fragment implements  NumberPicker.OnValueChangeListener, View.OnClickListener{

    private NotificationsViewModel notificationsViewModel;
    private NumberPicker picker1;
    private String[] pickerVals;
    CountDownTimer countDownTimer;
    String startTime;
    long timeLeft;
    private TextView timeCount;
    private Button startButton;
    private Button stopButton;
    private Button pauseButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        /*final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        */
        startButton = root.findViewById(R.id.start);
        startButton.setOnClickListener(this);
        stopButton = root.findViewById(R.id.stop);
        stopButton.setOnClickListener(this);
        pauseButton = root.findViewById(R.id.pause);
        pauseButton.setOnClickListener(this);
        picker1 = root.findViewById(R.id.numberpicker_main_picker);
        picker1.setMaxValue(60);
        picker1.setMinValue(0);
        pickerVals = new String[61];
        for (int i = 0; i < pickerVals.length; i++) {
            pickerVals[i] = String.format(getContext().getResources().getQuantityString(R.plurals.numberpicker_main_picker, i), i);
        }
        picker1.setDisplayedValues(pickerVals);

        return root;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        int valuePicker1 = picker1.getValue();
        Log.d("picker value", pickerVals[valuePicker1]);
    }

    public void countDownTimer() {
        long time = Long.parseLong(startTime);
        countDownTimer = new CountDownTimer(time * 1000,1000) {
            public void onTick(long millisUntilFinished) {
                timeCount.setText("seconds remaining: " + millisUntilFinished / 1000);

                timeLeft = millisUntilFinished;
            }
            public void onFinish() {
                //defaultRingtone.play();
                //timerImage.startAnimation(timerAnimation);
                timeCount.setText("Done!");
            }
        }.start();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.start:
                Log.e("test", "Button has been clicked");
                break;
        }
    }


}