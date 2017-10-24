package com.example.travistressler.memories.AddMemoryFragment;

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

public class AddMemoryFragment extends Fragment implements AddMemoryView {

    private AddMemoryPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_memory, container, false);
        ButterKnife.bind(this, view);
        presenter = new AddMemoryPresenter();
        presenter.attachView(this);
        return view;
    }

    public static AddMemoryFragment newInstance() {

        Bundle args = new Bundle();

        AddMemoryFragment fragment = new AddMemoryFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
