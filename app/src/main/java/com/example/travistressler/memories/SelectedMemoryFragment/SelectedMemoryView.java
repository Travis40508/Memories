package com.example.travistressler.memories.SelectedMemoryFragment;

import android.graphics.Bitmap;

/**
 * Created by travistressler on 10/24/17.
 */

public interface SelectedMemoryView {
    void closeFragment();

    void showImage(Bitmap bitmapImage);

    void showComment(String comment);

    void sendEmailWithMemory(Bitmap bitmapImage);
}
