package com.example.travistressler.memories.Util.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.location.Location;

import java.util.Date;

/**
 * Created by travistressler on 10/24/17.
 */

@Entity
public class ImageEntity {

    @PrimaryKey(autoGenerate = true)
    int id;

    private byte[] image;
    private String imageComment;
    private Date date;
    private Location location;

    public ImageEntity(byte[] image, String imageComment, Date date, Location location) {
        this.image = image;
        this.imageComment = imageComment;
        this.date = date;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getTest() {
        return image;
    }

    public void setTest(byte[] test) {
        this.image = test;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageComment() {
        return imageComment;
    }

    public void setImageComment(String imageComment) {
        this.imageComment = imageComment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
