package com.example.mymiwokapp.word;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mymiwokapp.R;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    private int color;
    public WordAdapter(@NonNull Context context, int resource, @NonNull List<Word> wordList, int color) {
        super(context, 0, wordList);
        this.color = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rootView = convertView;
        if (rootView == null){
            rootView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        rootView.setBackgroundColor(getContext().getColor(color));
        Word currentWord = getItem(position);
        ImageView image = rootView.findViewById(R.id.image);
        TextView englishWord = rootView.findViewById(R.id.english_text);
        TextView miwokWord = rootView.findViewById(R.id.miwok_text);

        if (currentWord.getImage() != 0) {
            image.setImageResource(currentWord.getImage());
        } else {
            image.setVisibility(View.GONE);
        }
        englishWord.setText(currentWord.getEnglishWord());
        miwokWord.setText(currentWord.getMiwokWord());

        return rootView;
    }
}
