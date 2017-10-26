package com.example.travistressler.memories.SelectedMemoryFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travistressler.memories.BuildConfig;
import com.example.travistressler.memories.MainActivity;
import com.example.travistressler.memories.R;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.provider.MediaStore.AUTHORITY;

/**
 * Created by travistressler on 10/24/17.
 */

public class SelectedMemoryFragment extends Fragment implements SelectedMemoryView {

    private SelectedMemoryPresenter presenter;

    @BindView(R.id.selected_image_image)
    public ImageView selectedImage;

    @BindView(R.id.selected_image_comment)
    public TextView selectedImageComment;

    @BindView(R.id.selected_image_title)
    public TextView selectedImageTitle;

    @OnClick(R.id.button_share)
    public void shareMemoryClicked(View view) {
        presenter.shareMemoryClicked();
    }

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
        presenter.getTitle(getArguments().getString("TITLE"));
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
        if(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_holder) != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_holder))
                    .commit();
        } else if(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_holder_map) != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_holder_map))
                    .commit();
        }
    }

    @Override
    public void showImage(Bitmap bitmapImage) {
        selectedImage.setImageBitmap(bitmapImage);
    }

    @Override
    public void showComment(String comment) {
        selectedImageComment.setText(comment);
    }

    @Override
    public void sendEmailWithMemory(Bitmap bitmapImage) {
        if (Build.VERSION.SDK_INT < 24) {
            try {
                File file = new File(getContext().getCacheDir(), "placeholder" + ".png");
                FileOutputStream fOut = new FileOutputStream(file);
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                fOut.flush();
                fOut.close();
                file.setReadable(true, false);
                final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
                intent.setType("image/png");
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getContext(), "Not available on > SDK 24", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void showTitle(String title) {
        selectedImageTitle.setText(title);
    }
}
