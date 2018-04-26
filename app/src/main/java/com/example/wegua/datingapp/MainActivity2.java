package com.example.wegua.datingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = MainActivity2.class.getSimpleName();
    TextView nameMessage;
    TextView ageMessage;
    TextView occuptionMessage;
    TextView descriptionMessage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();

        nameMessage = findViewById(R.id.nameMessage);
        ageMessage = findViewById(R.id.ageMessage);
        occuptionMessage = findViewById(R.id.occuptionMessage);
        descriptionMessage = findViewById(R.id.descriptionMessage);

        nameMessage.setText(intent.getStringExtra("name"));
        ageMessage.setText(intent.getStringExtra("age"));
        occuptionMessage.setText(intent.getStringExtra("occuption"));
        descriptionMessage.setText(intent.getStringExtra("description"));

        Log.i(TAG, "onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

}
