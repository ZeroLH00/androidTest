package com.project.zero.activitylife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("ActivityLife", "onCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("ActivityLife", "onPause");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ActivityLife", "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("ActivityLife", "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("ActivityLife", "onDestroy");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ActivityLife", "onResume");
    }
}
