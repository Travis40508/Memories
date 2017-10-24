package com.example.travistressler.memories.AddMemoryFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.travistressler.memories.R;
import com.example.travistressler.memories.Util.Database.ImageDatabase;
import com.example.travistressler.memories.Util.Database.ImageEntity;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by travistressler on 10/24/17.
 */

public class AddMemoryFragment extends Fragment implements AddMemoryView {

    private AddMemoryPresenter presenter;
    private ImageDatabase database;

    @BindView(R.id.image_preview)
    public ImageView imagePreview;

    @BindView(R.id.input_memory_comment)
    public EditText memoryComment;

    @OnClick(R.id.button_take_picture)
    public void takePictureClicked(View view) {
        presenter.takePictureClicked();
    }

    @OnClick(R.id.button_upload_picture)
    public void uploadtePictureClicked(View view) {
        presenter.uploadPictureClicked();
    }

    @OnClick(R.id.button_save_picture)
    public void savePictureClicked(View view) {
        presenter.savePictureClicked(memoryComment.getText().toString());
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
        database = ImageDatabase.getDatabase(getContext());
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

    @Override
    public void launchLocalImages(int requestLocalImages) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent,
                "Complete action using"), requestLocalImages);
    }

    @Override
    public void getImage(Uri selectedImageUri) {
        InputStream image_stream = null;
        try {
            image_stream = getActivity().getContentResolver().openInputStream(selectedImageUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap imageBitmap= BitmapFactory.decodeStream(image_stream );
        presenter.getImageBitmap(imageBitmap);
        displayImagePreview(imageBitmap);
    }

    @Override
    public void saveImage(ImageEntity imageEntity) {
        database.imageDao().insertImage(imageEntity);
        Toast.makeText(getContext(), "Memory saved!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearComment() {
        memoryComment.setText("");
    }

    @Override
    public void clearImage() {
        imagePreview.setImageBitmap(null);
    }

}
