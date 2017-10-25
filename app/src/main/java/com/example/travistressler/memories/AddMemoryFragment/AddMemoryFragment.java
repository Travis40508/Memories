package com.example.travistressler.memories.AddMemoryFragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.travistressler.memories.R;
import com.example.travistressler.memories.Util.Database.ImageDatabase;
import com.example.travistressler.memories.Util.Database.ImageEntity;
import com.google.android.gms.location.LocationListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by travistressler on 10/24/17.
 */

public class AddMemoryFragment extends Fragment implements AddMemoryView, android.location.LocationListener {

    private AddMemoryPresenter presenter;
    private ImageDatabase database;
    private LocationManager locationManager;
    private String provider;
    private Location location;
    Criteria criteria = new Criteria();

    @BindView(R.id.image_preview)
    public ImageView imagePreview;

    @BindView(R.id.input_memory_comment)
    public EditText memoryComment;

    @BindView(R.id.input_memory_title)
    public EditText memoryTitle;

    @BindView(R.id.input_memory_location)
    public EditText memoryLocation;

    @OnClick(R.id.button_take_picture)
    public void takePictureClicked(View view) {
        if(memoryLocation.getVisibility() == View.VISIBLE) {
            memoryLocation.setVisibility(View.GONE);
        }
        presenter.takePictureClicked();
    }

    @OnClick(R.id.button_upload_picture)
    public void uploadtePictureClicked(View view) {
        presenter.uploadPictureClicked();
    }

    @OnClick(R.id.button_save_picture)
    public void savePictureClicked(View view) {
        if(memoryTitle.getText().length() == 0 || memoryComment.length() == 0 || (memoryLocation.getVisibility() == View.VISIBLE && memoryLocation.length() == 0)) {
            Toast.makeText(getContext(), "Please fill out all fields first!", Toast.LENGTH_SHORT).show();
        } else if(memoryLocation.getVisibility() == View.VISIBLE) {
          presenter.savePictureClickedWithUpload(memoryComment.getText().toString(), memoryTitle.getText().toString(), memoryLocation.getText().toString());
        } else {
            presenter.savePictureClicked(memoryComment.getText().toString(), memoryTitle.getText().toString(), location);
        }
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
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
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
    public void onResume() {
        super.onResume();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        location = locationManager.getLastKnownLocation(provider);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
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
        Bitmap imageBitmap = BitmapFactory.decodeStream(image_stream);
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

    @Override
    public void showLocationInput() {
        memoryLocation.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLocationInput() {
        memoryLocation.setVisibility(View.GONE);
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
