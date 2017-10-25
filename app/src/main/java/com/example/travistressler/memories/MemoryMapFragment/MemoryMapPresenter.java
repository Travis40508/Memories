package com.example.travistressler.memories.MemoryMapFragment;

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
}
