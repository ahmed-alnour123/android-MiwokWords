package com.example.mymiwokapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mymiwokapp.activities.ColorsFragment;
import com.example.mymiwokapp.activities.FamilyFragment;
import com.example.mymiwokapp.activities.NumbersFragment;
import com.example.mymiwokapp.activities.PhrasesFragment;
import com.google.android.material.tabs.TabLayout;

// TODO: 30/12/2020 save user's last page
// FIXME: 30/12/2020 why there is color flickering when swiping fragments
public class MainActivity extends AppCompatActivity {

    private static int lastPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] colors = {R.color.category_numbers, R.color.category_family, R.color.category_colors, R.color.category_phrases};
        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tab = findViewById(R.id.tab);
        getSupportActionBar().setElevation(0);
        viewPager.setAdapter(new myFragmentPagerAdapter(getSupportFragmentManager()));
        tab.setupWithViewPager(viewPager);
        tab.setTabTextColors(0xFFDADADA, getColor(colors[0]));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                lastPage = position;
                Log.v("Ahmed", "position changed it is now: " + position);
                tab.setTabTextColors(0xFFDADADA, getColor(colors[position]));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}

class myFragmentPagerAdapter extends FragmentPagerAdapter {
    public myFragmentPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhrasesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Numbers";
            case 1:
                return "Family";
            case 2:
                return "Colors";
            case 3:
                return "Phrases";
        }
        return "default";
    }
}
