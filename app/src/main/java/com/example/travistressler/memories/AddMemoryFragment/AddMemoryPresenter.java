package com.example.travistressler.memories.AddMemoryFragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import static android.app.Activity.RESULT_OK;

/**
 * Created by travistressler on 10/24/17.
 */

public class AddMemoryPresenter {

    private AddMemoryView view;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    public void attachView(AddMemoryView view) {
        this.view = view;
    }

    public void takePictureClicked() {
        view.launchCamera(REQUEST_IMAGE_CAPTURE);
    }

    public void retrieveImage(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            view.displayImagePreview(imageBitmap);
        }
    }
}
