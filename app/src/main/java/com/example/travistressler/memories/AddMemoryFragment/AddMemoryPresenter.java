package com.example.travistressler.memories.AddMemoryFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.example.travistressler.memories.Util.Database.ImageDatabase;
import com.example.travistressler.memories.Util.Database.ImageEntity;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by travistressler on 10/24/17.
 */

public class AddMemoryPresenter {

    private AddMemoryView view;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap imageBitmap;
    private ImageDatabase database;
    private Context context;

    public AddMemoryPresenter() {

    }

    public void attachView(AddMemoryView view) {
        this.view = view;
    }

    public void takePictureClicked() {
        view.launchCamera(REQUEST_IMAGE_CAPTURE);
    }

    public void retrieveImage(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            this.imageBitmap = imageBitmap;
            view.displayImagePreview(imageBitmap);
        }
    }

    public void savePictureClicked(String memoryComment) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        ImageEntity imageEntity = new ImageEntity(byteArray, memoryComment, new Date(), new Location("lol"));
        database.imageDao().insertImage(imageEntity);
    }

    public void giveContext(Context context) {
        database = ImageDatabase.getDatabase(context);
    }
}
