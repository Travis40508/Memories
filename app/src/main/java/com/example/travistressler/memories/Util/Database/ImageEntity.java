package com.example.travistressler.memories.Util.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

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
    private double lat;
    private double lng;
    private String imageTitle;

    public ImageEntity(byte[] image, String imageComment, Date date, double lat, double lng, String imageTitle) {
        this.image = image;
        this.imageComment = imageComment;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
        this.imageTitle = imageTitle;
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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
}
