package com.example.travistressler.memories.AddMemoryFragment;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.travistressler.memories.Util.Database.ImageEntity;

/**
 * Created by travistressler on 10/24/17.
 */

public interface AddMemoryView {
    void launchCamera(int requestImageCapture);

    void displayImagePreview(Bitmap imageBitmap);

    void launchLocalImages(int requestLocalImages);

    void getImage(Uri selectedImageUri);

    void saveImage(ImageEntity imageEntity);

    void clearComment();

    void clearImage();

    void showLocationInput();

    void hideLocationInput();

    void clearTitle();
}
