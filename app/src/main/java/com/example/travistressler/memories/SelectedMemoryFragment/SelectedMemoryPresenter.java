package com.example.travistressler.memories.SelectedMemoryFragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by travistressler on 10/24/17.
 */

public class SelectedMemoryPresenter {

    private SelectedMemoryView view;
    private Bitmap bitmapImage;

    public void attachView(SelectedMemoryView view) {
        this.view = view;
    }

    public void closeClicked(boolean isGridVisible, boolean isMapVisible) {
        if(isGridVisible) {
            view.closeFragment();
        } else if(isMapVisible) {
            view.closeMapFragment();
        }
    }

    public void getImage(byte[] image) {
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(image, 0, image.length);
        this.bitmapImage = bitmapImage;
        view.showImage(bitmapImage);
    }

    public void getComment(String comment) {
        view.showComment(comment);
    }

    public void shareMemoryClicked() {
        view.sendEmailWithMemory(bitmapImage);
    }

    public void getTitle(String title) {
        view.showTitle(title);
    }
}
