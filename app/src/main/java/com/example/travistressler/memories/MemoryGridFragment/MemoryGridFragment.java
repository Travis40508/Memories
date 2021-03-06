package com.example.travistressler.memories.MemoryGridFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travistressler.memories.R;
import com.example.travistressler.memories.SelectedMemoryFragment.SelectedMemoryFragment;
import com.example.travistressler.memories.Util.Database.ImageDatabase;
import com.example.travistressler.memories.Util.Database.ImageEntity;
import com.example.travistressler.memories.Util.ImageGridAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by travistressler on 10/24/17.
 */

public class MemoryGridFragment extends Fragment implements MemoryGridView, ImageGridAdapter.Callback {

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    private ImageGridAdapter adapter;
    private ImageDatabase imageDatabase;
    private MemoryGridPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_memory_grid, container, false);
        ButterKnife.bind(this, view);
        presenter = new MemoryGridPresenter();
        presenter.attachView(this);
        presenter.loadImages(imageDatabase.imageDao().getallImages());
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageDatabase = ImageDatabase.getDatabase(getContext());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getView() != null) {
            presenter.loadImages(imageDatabase.imageDao().getallImages());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadImages(imageDatabase.imageDao().getallImages());
    }


    public static MemoryGridFragment newInstance() {
        Bundle args = new Bundle();
        MemoryGridFragment fragment = new MemoryGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void retrieveImages(List<ImageEntity> imageEntityList) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        adapter = new ImageGridAdapter(imageEntityList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showSelectedImage(Bundle bundle) {
        SelectedMemoryFragment selectedMemoryFragment = SelectedMemoryFragment.newInstance();
        selectedMemoryFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragment_holder, selectedMemoryFragment)
                .commit();
    }

    @Override
    public void cellClicked(ImageEntity imageEntity) {
        presenter.cellClicked(imageEntity);
    }
}
