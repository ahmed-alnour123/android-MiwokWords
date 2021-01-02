package com.example.mymiwokapp.word;

import android.app.Application;
import android.content.Context;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

public class Word {
    @StringRes private int englishWord;
    @StringRes private int miwokWord;
    @DrawableRes private int image;
    @RawRes private int sound;

    public Word(int englishWord, int miwokWord, @DrawableRes int image, @RawRes int sound){
        this.englishWord = englishWord;
        this.miwokWord = miwokWord;
        this.image = image;
        this.sound = sound;
    }
    public Word(int englishWord, int miwokWord, @RawRes int sound){
        this.englishWord = englishWord;
        this.miwokWord = miwokWord;
        this.image = 0;
        this.sound = sound;
    }

    public int getEnglishWord() {
        return englishWord;
    }

    public int getMiwokWord() {
        return miwokWord;
    }

    public int getImage() {
        return image;
    }

    public int getSound() {
        return sound;
    }

    @Override
    public String toString() {
        return "Word{" +
                "englishWord=" + englishWord +
                ", miwokWord=" + miwokWord +
                ", image=" + image +
                ", sound=" + sound +
                '}';
    }
}
