package com.simarjot.task.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import io.github.kexanie.library.MathView;

public class BindingAdapters {

    @BindingAdapter("load_image")
    public static void loadImageFromUrl(ImageView imageView, String url) {
        if (url.isEmpty()) {
            imageView.setVisibility(View.GONE);
            return;
        }
        Glide.with(imageView).load(url).into(imageView);
    }

    @BindingAdapter("text_visibility")
    public static void setTextVisibility(View textView, String text){
        int visibility = text == null || text.isEmpty() ? View.GONE : View.VISIBLE;
        textView.setVisibility(visibility);
    }

}
