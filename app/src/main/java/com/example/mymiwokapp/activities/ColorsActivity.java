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

public class ColorsActivity extends AppCompatActivity {

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
        WordAdapter adapter = new WordAdapter(this, R.layout.list_item, words, R.color.category_colors);
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
        ArrayList<Word> words = new ArrayList<Word>(8);
        words.add(new Word(R.string.english_color_red, R.string.miwok_color_red, R.drawable.color_red, R.raw.color_red));
        words.add(new Word(R.string.english_color_green, R.string.miwok_color_green, R.drawable.color_green, R.raw.color_green));
        words.add(new Word(R.string.english_color_brown, R.string.miwok_color_brown, R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word(R.string.english_color_gray, R.string.miwok_color_gray, R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word(R.string.english_color_black, R.string.miwok_color_black, R.drawable.color_black, R.raw.color_black));
        words.add(new Word(R.string.english_color_white, R.string.miwok_color_white, R.drawable.color_white, R.raw.color_white));
        words.add(new Word(R.string.english_color_dusty_yellow, R.string.miwok_color_dusty_yellow, R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word(R.string.english_color_mustard_yellow, R.string.miwok_color_mustard_yellow, R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        return words;
    }
}