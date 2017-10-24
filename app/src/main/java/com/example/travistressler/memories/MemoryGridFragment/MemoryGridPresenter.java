package com.example.travistressler.memories.MemoryGridFragment;

import android.content.Context;
import android.os.Bundle;

import com.example.travistressler.memories.Util.Database.ImageDatabase;
import com.example.travistressler.memories.Util.Database.ImageEntity;

import java.util.List;

/**
 * Created by travistressler on 10/24/17.
 */

public class MemoryGridPresenter {

    private MemoryGridView view;
    private ImageDatabase imageDatabase;

    public void attachView(MemoryGridView view) {
        this.view = view;
    }

    public void loadImages() {
        List<ImageEntity> imageEntityList = imageDatabase.imageDao().getallImages();
        view.retrieveImages(imageEntityList);
    }

    public void giveContext(Context context) {
        imageDatabase = ImageDatabase.getDatabase(context);
    }

    public void cellClicked(ImageEntity imageEntity) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("IMAGE", imageEntity.getImage());
        bundle.putString("COMMENT", imageEntity.getImageComment());
        view.showSelectedImage(bundle);
    }
}
