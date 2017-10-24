package com.example.travistressler.memories;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.travistressler.memories.AddMemoryFragment.AddMemoryFragment;
import com.example.travistressler.memories.MemoryGridFragment.MemoryGridFragment;
import com.example.travistressler.memories.MemoryMapFragment.MemoryMapFragment;
import com.example.travistressler.memories.Util.MemoryViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager_holder)
    public ViewPager viewPager;

    private MemoryViewPagerAdapter viewPagerAdapter;

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        populateListOfFragments();
        viewPagerAdapter = new MemoryViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
    }

    private void populateListOfFragments() {
        fragments.add(AddMemoryFragment.newInstance());
        fragments.add(MemoryMapFragment.newInstance());
        fragments.add(MemoryGridFragment.newInstance());
    }

}
