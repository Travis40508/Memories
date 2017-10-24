package com.example.travistressler.memories.AddMemoryFragment;

import android.graphics.Bitmap;

/**
 * Created by travistressler on 10/24/17.
 */

public interface AddMemoryView {
    void launchCamera(int requestImageCapture);

    void displayImagePreview(Bitmap imageBitmap);
}
