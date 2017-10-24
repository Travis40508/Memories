package com.example.travistressler.memories.MemoryGridFragment;

import android.os.Bundle;

import com.example.travistressler.memories.Util.Database.ImageEntity;

import java.util.List;

/**
 * Created by travistressler on 10/24/17.
 */

public interface MemoryGridView {
    void retrieveImages(List<ImageEntity> imageEntityList);

    void showSelectedImage(Bundle bundle);
}
