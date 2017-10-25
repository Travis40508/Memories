package com.example.travistressler.memories;

import android.*;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

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
        checkForLocationPermisisons();
        populateListOfFragments();
        viewPagerAdapter = new MemoryViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
    }

    private void checkForLocationPermisisons() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(this, "No permission granted.", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void populateListOfFragments() {
        fragments.add(AddMemoryFragment.newInstance());
        fragments.add(MemoryGridFragment.newInstance());
        fragments.add(MemoryMapFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().findFragmentById(R.id.fragment_holder) != null) {
            getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.fragment_holder)).commit();
        } else {
            super.onBackPressed();
        }
    }
}
