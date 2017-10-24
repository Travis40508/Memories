package com.example.travistressler.memories.Util.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by travistressler on 10/24/17.
 */

@Dao
public interface ImageDao {

    @Insert
    void insertImage(ImageEntity imageEntity);

    @Query("SELECT * FROM imageentity")
    List<ImageEntity> getallImages();

    @Update
    void updateImage(ImageEntity imageEntity);

    @Delete
    void deleteImage(ImageEntity imageEntity);
}
