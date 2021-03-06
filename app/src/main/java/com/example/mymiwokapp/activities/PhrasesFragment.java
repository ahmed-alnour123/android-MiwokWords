package com.example.mymiwokapp.activities;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mymiwokapp.R;
import com.example.mymiwokapp.word.Word;
import com.example.mymiwokapp.word.WordAdapter;

import java.util.ArrayList;

public class PhrasesFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);

        audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);

        ArrayList<Word> words = fillArrayList();
        WordAdapter adapter = new WordAdapter(getContext(), R.layout.list_item, words, R.color.category_phrases);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            if (audioManager.requestAudioFocus(myAudioFocusChangeListener,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(getContext(), words.get(i).getSound());
                mediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());
                mediaPlayer.start();
            }
        });

        return rootView;
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
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private ArrayList<Word> fillArrayList() {
        ArrayList<Word> words = new ArrayList<Word>(10);
        words.add(new Word(R.string.english_phrase_where_are_you_going, R.string.miwok_phrase_where_are_you_going, R.raw.phrase_where_are_you_going));
        words.add(new Word(R.string.english_phrase_what_is_your_name, R.string.miwok_phrase_what_is_your_name, R.raw.phrase_what_is_your_name));
        words.add(new Word(R.string.english_phrase_my_name_is, R.string.miwok_phrase_my_name_is, R.raw.phrase_my_name_is));
        words.add(new Word(R.string.english_phrase_how_are_you_feeling, R.string.miwok_phrase_how_are_you_feeling, R.raw.phrase_how_are_you_feeling));
        words.add(new Word(R.string.english_phrase_im_feeling_good, R.string.miwok_phrase_im_feeling_good, R.raw.phrase_im_feeling_good));
        words.add(new Word(R.string.english_phrase_are_you_coming, R.string.miwok_phrase_are_you_coming, R.raw.phrase_are_you_coming));
        words.add(new Word(R.string.english_phrase_yes_im_coming, R.string.miwok_phrase_yes_im_coming, R.raw.phrase_yes_im_coming));
        words.add(new Word(R.string.english_phrase_im_coming, R.string.miwok_phrase_im_coming, R.raw.phrase_im_coming));
        words.add(new Word(R.string.english_phrase_lets_go, R.string.miwok_phrase_lets_go, R.raw.phrase_lets_go));
        words.add(new Word(R.string.english_phrase_come_here, R.string.miwok_phrase_come_here, R.raw.phrase_come_here));
        return words;
    }
}