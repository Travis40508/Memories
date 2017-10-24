package com.example.travistressler.memories.MemoryGridFragment;

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

public class MemoryGridFragment extends Fragment implements MemoryGridView {

    private MemoryGridPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memory_grid, container, false);
        ButterKnife.bind(this, view);
        presenter = new MemoryGridPresenter();
        presenter.attachView(this);
        return view;
    }

    public static MemoryGridFragment newInstance() {

        Bundle args = new Bundle();

        MemoryGridFragment fragment = new MemoryGridFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
