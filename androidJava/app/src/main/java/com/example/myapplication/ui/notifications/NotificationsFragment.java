package com.example.myapplication.ui.notifications;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class NotificationsFragment extends Fragment implements View.OnClickListener, NumberPicker.OnValueChangeListener {

    private NotificationsViewModel notificationsViewModel;
    private NumberPicker picker1;
    private String[] pickerVals;
    CountDownTimer countDownTimer;
    int valuePicker1;
    int time;
    long timeLeft;
    private TextView timeCount;
    private Button startButton;
    private Button stopButton;
    private Button pauseButton;
    boolean isPaused;
    Ringtone defaultRingtone;
    Animation animation;

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
        timeCount = root.findViewById(R.id.timeCount);
        picker1 = root.findViewById(R.id.numberpicker_main_picker);
        picker1.setOnValueChangedListener(this);
        picker1.setMaxValue(60);
        picker1.setMinValue(0);
        pickerVals = new String[61];
        for (int i = 0; i < pickerVals.length; i++) {
            //Log.e("test", i+" s");
            pickerVals[i] = i+" s";
        }
        picker1.setDisplayedValues(pickerVals);
        defaultRingtone = RingtoneManager.getRingtone(getContext(),
                Settings.System.DEFAULT_RINGTONE_URI);
        //animation = AnimationUtils.loadAnimation(getContext(), R.anim.roundanimation);
        return root;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        valuePicker1 = picker1.getValue();
        Log.d("picker value", pickerVals[valuePicker1]);
    }

    private void countDownTimer() {
        time = valuePicker1;
        countDownTimer = new CountDownTimer(time * 1000,1000) {
            public void onTick(long millisUntilFinished) {
                timeCount.setText(millisUntilFinished / 1000 + " s left");

                timeLeft = millisUntilFinished;
            }
            public void onFinish() {
                defaultRingtone.play();
                //timeCount.startAnimation(animation);
                timeCount.setText("END");
            }
        }.start();
    }

    private void pausedTimer() {
        countDownTimer = new CountDownTimer(timeLeft,1000) {
            public void onTick(long millisUntilFinished) {
                timeCount.setText(millisUntilFinished / 1000 + " s left");
            }
            public void onFinish() {
                defaultRingtone.play();
                //timeCount.startAnimation(animation);
                timeCount.setText("END");
            }
        }.start();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.start:
                Log.e("test", "Button has been clicked");
                isPaused = true;
                if(countDownTimer != null) {
                    pausedTimer();
                }
                else{
                    countDownTimer();
                }
                break;

            case R.id.stop:
                countDownTimer.cancel();
                defaultRingtone.stop();
                //animation.cancel();
                break;

            case R.id.pause:
                Log.i("TAG", "pause button");
                countDownTimer.cancel();
                break;
        }
        }
    }
