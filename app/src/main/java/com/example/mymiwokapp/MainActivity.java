package com.example.mymiwokapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymiwokapp.activities.ColorsActivity;
import com.example.mymiwokapp.activities.FamilyActivity;
import com.example.mymiwokapp.activities.NumbersActivity;
import com.example.mymiwokapp.activities.PhrasesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numbersTextView = findViewById(R.id.numbers);
        TextView familyTextView = findViewById(R.id.family);
        TextView colorsTextView = findViewById(R.id.colors);
        TextView phrasesTextView = findViewById(R.id.phrases);

        numbersTextView.setOnClickListener(v -> {
            startActivity(new Intent(this, NumbersActivity.class));
        });

        familyTextView.setOnClickListener(v -> {
            startActivity(new Intent(this, FamilyActivity.class));
        });

        colorsTextView.setOnClickListener(v -> {
            startActivity(new Intent(this, ColorsActivity.class));
        });

        phrasesTextView.setOnClickListener(v -> {
            startActivity(new Intent(this, PhrasesActivity.class));
        });
    }

}