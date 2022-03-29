package com.example.orientationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String SCORE = "score";
    SharedPreferences sharedPreferences;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button incrementScoreButton = findViewById(R.id.incrementScoreButton);
        Button showScoreButton = findViewById(R.id.showScoreButton);
        Button saveScoreButton = findViewById(R.id.saveScoreButton);
        score = 0;

        sharedPreferences = getSharedPreferences("com.example.score", MODE_PRIVATE);
        score = sharedPreferences.getInt(SCORE, 0);

        if (savedInstanceState!=null) {
            score = savedInstanceState.getInt(SCORE);
        }

        incrementScoreButton.setOnClickListener(view -> score += 1);

        showScoreButton.setOnClickListener(view -> {
            String scoreString;
            scoreString = "Score: " + score;
            Toast.makeText(MainActivity.this, scoreString, Toast.LENGTH_SHORT).show();
        });

        saveScoreButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(SCORE, score);
            editor.apply();
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle OutState) {
        OutState.putInt(SCORE, score);
        super.onSaveInstanceState(OutState);
    }
}