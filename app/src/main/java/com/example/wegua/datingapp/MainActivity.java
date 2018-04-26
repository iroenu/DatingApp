package com.example.wegua.datingapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText name;
    private EditText age;
    private EditText occuption;
    private EditText description;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        occuption = (EditText) findViewById(R.id.occuption);
        description = (EditText) findViewById(R.id.description);
        button = (Button) findViewById(R.id.signupButton);
        // perform click event on submit button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().trim().isEmpty()){
                    name.setError("Please enter your name");
                    name.requestFocus();
                } else if (age.getText().toString().trim().isEmpty()){
                    age.setError("Please enter your username");
                    age.requestFocus();
                } else if (occuption.getText().toString().trim().isEmpty()){
                    occuption.setError("Please enter your username");
                    occuption.requestFocus();
                } else if (description.getText().toString().trim().isEmpty()){
                    description.setError("Please enter your username");
                    description.requestFocus();
                }else {
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra(Constants.KEY_NAME, name.getText().toString());
                    intent.putExtra(Constants.KEY_AGE,age.getText().toString());
                    intent.putExtra(Constants.KEY_OCCUPTION,occuption.getText().toString());
                    intent.putExtra(Constants.KEY_DESCRIPTION,description.getText().toString());
                    startActivity(intent);
                }
            }
        });
        Log.i(TAG, "onCreate()");
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i(TAG, "onSaveInstanceState()");
        outState.putString(Constants.KEY_NAME, name.getText().toString());
        outState.putString(Constants.KEY_AGE, age.getText().toString());
        outState.putString(Constants.KEY_DESCRIPTION, description.getText().toString());
        outState.putString(Constants.KEY_OCCUPTION, occuption.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.i(TAG, "onRestoreInstanceState()");
        if (savedInstanceState.containsKey(Constants.KEY_NAME)) {
            name.setText((String)savedInstanceState.get(Constants.KEY_NAME));
        }

        if (savedInstanceState.containsKey(Constants.KEY_AGE)) {
            age.setText((String) savedInstanceState.get(Constants.KEY_AGE));
        }

        if (savedInstanceState.containsKey(Constants.KEY_OCCUPTION)) {
            occuption.setText((String) savedInstanceState.get(Constants.KEY_OCCUPTION));
        }

        if (savedInstanceState.containsKey(Constants.KEY_DESCRIPTION)) {
            description.setText((String) savedInstanceState.get(Constants.KEY_DESCRIPTION));
        }
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
