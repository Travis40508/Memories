package com.example.travistressler.memories.MemoryGridFragment;

import android.os.Bundle;

import com.example.travistressler.memories.Util.Database.ImageEntity;

import java.util.List;

/**
 * Created by travistressler on 10/24/17.
 */

public class MemoryGridPresenter {

    private MemoryGridView view;

    public void attachView(MemoryGridView view) {
        this.view = view;
    }

    public void loadImages(List<ImageEntity> imageEntities) {
        view.retrieveImages(imageEntities);
    }


    public void cellClicked(ImageEntity imageEntity) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("IMAGE", imageEntity.getImage());
        bundle.putString("COMMENT", imageEntity.getImageComment());
        bundle.putString("TITLE", imageEntity.getImageTitle());
        view.showSelectedImage(bundle);
    }
}
