package com.example.travistressler.memories.Util.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by travistressler on 10/24/17.
 */

@Database(version = 3, entities = ImageEntity.class)
@TypeConverters(Converters.class)
public abstract class ImageDatabase extends RoomDatabase {

    private static ImageDatabase INSTANCE;

    abstract public ImageDao imageDao();

    public static ImageDatabase getDatabase(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ImageDatabase.class, "image-database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
