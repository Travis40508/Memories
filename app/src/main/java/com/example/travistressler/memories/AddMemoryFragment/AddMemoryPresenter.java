package com.example.travistressler.memories.AddMemoryFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import com.example.travistressler.memories.Util.Database.ImageEntity;

import java.io.ByteArrayOutputStream;
import java.util.Date;

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


    public void attachView(AddMemoryView view) {
        this.view = view;
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
        view.hideLocationInput();
    }


    public void uploadPictureClicked() {
        view.launchLocalImages(REQUEST_LOCAL_IMAGES);
    }

    public void getImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public void savePictureClickedWithUpload(String memoryComment, String memoryTitle, String memoryLocation) {

    }
}
