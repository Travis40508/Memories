package com.example.travistressler.memories.AddMemoryFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.travistressler.memories.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by travistressler on 10/24/17.
 */

public class AddMemoryFragment extends Fragment implements AddMemoryView {

    private AddMemoryPresenter presenter;

    @BindView(R.id.image_preview)
    public ImageView imagePreview;

    @OnClick(R.id.button_take_picture)
    public void takePictureClicked(View view) {
        presenter.takePictureClicked();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.retrieveImage(requestCode, resultCode, data);
    }

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

    @Override
    public void launchCamera(int requestImageCapture) {
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraintent, requestImageCapture);
    }

    @Override
    public void displayImagePreview(Bitmap imageBitmap) {
        imagePreview.setImageBitmap(imageBitmap);
    }
}
