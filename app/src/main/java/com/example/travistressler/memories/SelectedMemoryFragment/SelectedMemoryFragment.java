package com.example.travistressler.memories.SelectedMemoryFragment;

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

public class SelectedMemoryFragment extends Fragment implements SelectedMemoryView {

    private SelectedMemoryPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_memory, container, false);
        ButterKnife.bind(this, view);
        presenter = new SelectedMemoryPresenter();
        presenter.attachView(this);
        return view;
    }

    public static SelectedMemoryFragment newInstance() {

        Bundle args = new Bundle();

        SelectedMemoryFragment fragment = new SelectedMemoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
