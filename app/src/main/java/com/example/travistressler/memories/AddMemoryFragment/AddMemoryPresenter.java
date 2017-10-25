package com.example.travistressler.memories.AddMemoryFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import com.example.travistressler.memories.Util.Database.ImageEntity;
import com.example.travistressler.memories.Util.GoogleApi.GoogleAddress;
import com.example.travistressler.memories.Util.GoogleApi.GoogleGeoApi;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

/**
 * Created by travistressler on 10/24/17.
 */

public class AddMemoryPresenter {

    private AddMemoryView view;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_LOCAL_IMAGES = 2;
    private Bitmap imageBitmap;
    private Location location;
    private String baseUrl = "https://maps.googleapis.com/maps/api/geocode/";
    private String apiKey = "AIzaSyDJPBfg8tgmDSRF-Sl6gdT3KDfs4gL_uhg";
    private Retrofit retrofit;
    private GoogleGeoApi googleGeoApi;


    public void attachView(AddMemoryView view) {
        this.view = view;
        if(view != null) {
            buildApi();
        }
    }

    private void buildApi() {
        googleGeoApi = getRetrofit().create(GoogleGeoApi.class);
    }

    public void takePictureClicked() {
        view.launchCamera(REQUEST_IMAGE_CAPTURE);
    }

    public void retrieveImage(int requestCode, int resultCode, Intent data)  {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            this.imageBitmap = imageBitmap;
            view.displayImagePreview(imageBitmap);
        } else if (requestCode == REQUEST_LOCAL_IMAGES && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            view.getImage(selectedImageUri);
            view.showLocationInput();
        }
    }

    public void savePictureClicked(String memoryComment, String memoryTitle, Location location) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        ImageEntity imageEntity = new ImageEntity(byteArray, memoryComment, new Date(), location.getLatitude(), location.getLongitude(), memoryTitle);
        view.saveImage(imageEntity);
        view.clearComment();
        view.clearImage();
        view.clearTitle();
        view.hideLocationInput();
    }


    public void uploadPictureClicked() {
        view.launchLocalImages(REQUEST_LOCAL_IMAGES);
    }

    public void getImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public void savePictureClickedWithUpload(final String memoryComment, final String memoryTitle, String memoryLocation) {
        googleGeoApi.getAddress(memoryLocation, apiKey).enqueue(new Callback<GoogleAddress>() {
            @Override
            public void onResponse(Call<GoogleAddress> call, Response<GoogleAddress> response) {
                if(response.isSuccessful()) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    double latitude = response.body().getResults().get(0).getGeometry().getLocation().getLatitude();
                    double longitude = response.body().getResults().get(0).getGeometry().getLocation().getLongitude();
                    ImageEntity imageEntity = new ImageEntity(byteArray, memoryComment, new Date(), latitude, longitude, memoryTitle);
                    view.saveImage(imageEntity);
                    view.clearComment();
                    view.clearImage();
                    view.hideLocationInput();
                }
            }

            @Override
            public void onFailure(Call<GoogleAddress> call, Throwable t) {

            }
        });
    }

    public Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
