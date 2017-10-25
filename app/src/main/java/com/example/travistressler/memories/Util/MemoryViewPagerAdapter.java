package com.example.travistressler.memories.Util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.travistressler.memories.AddMemoryFragment.AddMemoryFragment;
import com.example.travistressler.memories.MemoryGridFragment.MemoryGridFragment;
import com.example.travistressler.memories.MemoryMapFragment.MemoryMapFragment;

import java.util.List;

/**
 * Created by travistressler on 10/24/17.
 */

public class MemoryViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public MemoryViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return AddMemoryFragment.newInstance();
            case 1:
                return MemoryGridFragment.newInstance();
            case 2:
                return MemoryMapFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
