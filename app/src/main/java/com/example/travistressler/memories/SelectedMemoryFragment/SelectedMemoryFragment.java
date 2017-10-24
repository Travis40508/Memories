package com.example.travistressler.memories.SelectedMemoryFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travistressler.memories.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by travistressler on 10/24/17.
 */

public class SelectedMemoryFragment extends Fragment implements SelectedMemoryView {

    private SelectedMemoryPresenter presenter;

    @BindView(R.id.selected_image_image)
    public ImageView selectedImage;

    @BindView(R.id.selected_image_comment)
    public TextView selectedImageComment;


    @OnClick(R.id.button_close_fragment)
    public void closeFragmentClicked(View view) {
        presenter.closeClicked();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_memory, container, false);
        ButterKnife.bind(this, view);
        presenter = new SelectedMemoryPresenter();
        presenter.attachView(this);
        presenter.getImage(getArguments().getByteArray("IMAGE"));
        presenter.getComment(getArguments().getString("COMMENT"));
        return view;
    }


    public static SelectedMemoryFragment newInstance() {
        Bundle args = new Bundle();
        SelectedMemoryFragment fragment = new SelectedMemoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void closeFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_holder))
                .commit();
    }

    @Override
    public void showImage(Bitmap bitmapImage) {
        selectedImage.setImageBitmap(bitmapImage);
    }

    @Override
    public void showComment(String comment) {
        selectedImageComment.setText(comment);
    }
}
