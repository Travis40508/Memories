package com.example.travistressler.memories.SelectedMemoryFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by travistressler on 10/24/17.
 */

public class SelectedMemoryPresenter {

    private SelectedMemoryView view;

    public void attachView(SelectedMemoryView view) {
        this.view = view;
    }

    public void closeClicked() {
        view.closeFragment();
    }

    public void getImage(byte[] image) {
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(image, 0, image.length);
        view.showImage(bitmapImage);
    }

    public void getComment(String comment) {
        view.showComment(comment);
    }
}
