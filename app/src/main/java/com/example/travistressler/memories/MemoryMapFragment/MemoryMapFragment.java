package com.example.travistressler.memories.MemoryMapFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travistressler.memories.R;

import butterknife.ButterKnife;

/**
 * Created by travistressler on 10/24/17.
 */

public class MemoryMapFragment extends Fragment implements MemoryMapView {

    private MemoryMapPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memory_map, container, false);
        ButterKnife.bind(this, view);
        presenter = new MemoryMapPresenter();
        presenter.attachView(this);
        return view;
    }

    public static MemoryMapFragment newInstance() {

        Bundle args = new Bundle();

        MemoryMapFragment fragment = new MemoryMapFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
