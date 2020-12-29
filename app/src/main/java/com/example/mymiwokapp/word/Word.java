package com.example.mymiwokapp.word;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;
import androidx.annotation.StringRes;

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
        new Word(englishWord, miwokWord, 0, sound);
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
}
