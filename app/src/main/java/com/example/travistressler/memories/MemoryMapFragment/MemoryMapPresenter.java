package com.example.travistressler.memories.MemoryMapFragment;

import android.os.Bundle;

import com.example.travistressler.memories.Util.Database.ImageEntity;

import java.util.List;

/**
 * Created by travistressler on 10/24/17.
 */

public class MemoryMapPresenter {
    private MemoryMapView view;

    public void attachView(MemoryMapView view) {
        this.view = view;
    }


    public void onResume() {
        view.loadMap();
    }

    public void markerInfoWindowClicked(ImageEntity imageEntity) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("IMAGE", imageEntity.getImage());
        bundle.putString("COMMENT", imageEntity.getImageComment());
        bundle.putString("TITLE", imageEntity.getImageTitle());
        view.showSelectedImage(bundle);
    }
}
