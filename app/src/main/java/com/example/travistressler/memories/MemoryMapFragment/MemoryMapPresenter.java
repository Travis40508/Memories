package com.example.travistressler.memories.MemoryMapFragment;

/**
 * Created by travistressler on 10/24/17.
 */

public class MemoryMapPresenter {
    private MemoryMapView view;

    public void attachView(MemoryMapView view) {
        this.view = view;
        if(view != null) {
            view.loadMap();
        }
    }
}
