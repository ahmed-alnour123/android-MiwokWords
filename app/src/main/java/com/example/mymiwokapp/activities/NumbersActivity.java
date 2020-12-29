package com.example.mymiwokapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.mymiwokapp.R;
import com.example.mymiwokapp.word.Word;
import com.example.mymiwokapp.word.WordAdapter;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<Word> words = fillArrayList();
        WordAdapter adapter = new WordAdapter(this, R.layout.list_item, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

    }

    private ArrayList<Word> fillArrayList() {
        ArrayList<Word> words = new ArrayList<Word>(10);
        words.add(new Word(R.string.english_number_one, R.string.miwok_number_one, R.drawable.number_one, R.raw.number_one));
        words.add(new Word(R.string.english_number_two, R.string.miwok_number_two, R.drawable.number_two, R.raw.number_two));
        words.add(new Word(R.string.english_number_three, R.string.miwok_number_three, R.drawable.number_three, R.raw.number_three));
        words.add(new Word(R.string.english_number_four, R.string.miwok_number_four, R.drawable.number_four, R.raw.number_four));
        words.add(new Word(R.string.english_number_five, R.string.miwok_number_five, R.drawable.number_five, R.raw.number_five));
        words.add(new Word(R.string.english_number_six, R.string.miwok_number_six, R.drawable.number_six, R.raw.number_six));
        words.add(new Word(R.string.english_number_seven, R.string.miwok_number_seven, R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word(R.string.english_number_eight, R.string.miwok_number_eight, R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word(R.string.english_number_nine, R.string.miwok_number_nine, R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word(R.string.english_number_ten, R.string.miwok_number_ten, R.drawable.number_ten, R.raw.number_ten));
        return words;
    }
}