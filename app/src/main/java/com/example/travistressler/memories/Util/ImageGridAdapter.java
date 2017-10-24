package com.example.travistressler.memories.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.travistressler.memories.R;
import com.example.travistressler.memories.Util.Database.ImageEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by travistressler on 10/24/17.
 */

public class ImageGridAdapter extends RecyclerView.Adapter<ImageGridAdapter.ImageViewHolder> {

    private List<ImageEntity> imageList;

    public ImageGridAdapter(List<ImageEntity> imageList) {
        this.imageList = imageList;
    }

    @Override
    public ImageGridAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saved_image, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageGridAdapter.ImageViewHolder holder, int position) {
        holder.bindMemories(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_image)
        public ImageView memoryImage;


        @BindView(R.id.item_date)
        public TextView memoryDate;


        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindMemories(ImageEntity imageEntity) {
            Bitmap image = BitmapFactory.decodeByteArray(imageEntity.getImage(), 0, imageEntity.getImage().length);
            memoryImage.setImageBitmap(image);
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-YYYY");
            memoryDate.setText(dateFormat.format(imageEntity.getDate()));
        }
    }
}
