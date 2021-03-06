package com.yasinhajilou.dileit.view.adapter.viewpager;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.yasinhajilou.dileit.R;
import com.yasinhajilou.dileit.view.fragment.leitnermanager.LearnedWordsManagerFragment;
import com.yasinhajilou.dileit.view.fragment.leitnermanager.NewWordsManagerFragment;
import com.yasinhajilou.dileit.view.fragment.leitnermanager.ReviewWordsManagerFragment;

public class LeitnerManagerViewPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    public LeitnerManagerViewPagerAdapter(@NonNull FragmentManager fm , Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new ReviewWordsManagerFragment();
            case 2:
                return new LearnedWordsManagerFragment();
            default:
                return new NewWordsManagerFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return mContext.getString(R.string.new_);
            case 1:
                return mContext.getString(R.string.review);
            case 2:
                return  mContext.getString(R.string.learned);
            default:
                return  "null";
        }
    }
}
