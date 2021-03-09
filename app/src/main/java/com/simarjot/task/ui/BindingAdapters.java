package com.simarjot.task.ui;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingAdapters {

    @BindingAdapter("load_image")
    public static void loadImageFromUrl(ImageView imageView, String url) {
        if (url.isEmpty()) {
            imageView.setVisibility(View.GONE);
            return;
        }
        Glide.with(imageView).load(url).into(imageView);
    }
}
