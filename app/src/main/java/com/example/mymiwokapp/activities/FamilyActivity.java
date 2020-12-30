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

public class FamilyActivity extends AppCompatActivity {

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
        WordAdapter adapter = new WordAdapter(this, R.layout.list_item, words, R.color.category_family);
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
        words.add(new Word(R.string.english_family_father, R.string.miwok_family_father, R.drawable.family_father, R.raw.family_father));
        words.add(new Word(R.string.english_family_mother, R.string.miwok_family_mother, R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word(R.string.english_family_son, R.string.miwok_family_son, R.drawable.family_son, R.raw.family_son));
        words.add(new Word(R.string.english_family_daughter, R.string.miwok_family_daughter, R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word(R.string.english_family_older_brother, R.string.miwok_family_older_brother, R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word(R.string.english_family_younger_brother, R.string.miwok_family_younger_brother, R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word(R.string.english_family_older_sister, R.string.miwok_family_older_sister, R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word(R.string.english_family_younger_sister, R.string.miwok_family_younger_sister, R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word(R.string.english_family_grandmother, R.string.miwok_family_grandmother, R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word(R.string.english_family_grandfather, R.string.miwok_family_grandfather, R.drawable.family_grandfather, R.raw.family_grandfather));
        return words;
    }
}