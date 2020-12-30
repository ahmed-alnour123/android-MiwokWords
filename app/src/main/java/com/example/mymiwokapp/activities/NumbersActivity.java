package com.example.mymiwokapp.activities;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymiwokapp.R;
import com.example.mymiwokapp.word.Word;
import com.example.mymiwokapp.word.WordAdapter;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener myAudioFocusChangeListener = focusChange -> {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT:
            case AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK:
                mediaPlayer.start();
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                mediaPlayer.pause();
                break;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Word> words = fillArrayList();
        WordAdapter adapter = new WordAdapter(this, R.layout.list_item, words);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            if (audioManager.requestAudioFocus(myAudioFocusChangeListener,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(this, words.get(i).getSound());
                mediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());
                mediaPlayer.start();
            }
        });
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(myAudioFocusChangeListener);
        }
    }

    void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
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
        words.add(new Word(R.string.english_number_ten, R.string.miwok_number_ten, R.drawable.number_ten, R.raw.giorno_theme));
        return words;
    }
}
