package com.example.yasirnazir.sky.utils.display;

/**
 * Created by yasirnazir on 3/20/18.
 */

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.yasirnazir.sky.R;

public class ImageLoader {
    private final Context mContext;
    private final RequestManager mGlide;

    public ImageLoader(Context context) {
        mContext = context;
        mGlide = Glide.with(mContext);
    }

    public void loadImage(String url, ImageView imageView) {
        Drawable placeHolder = VectorDrawableCompat.create(imageView.getResources(), R.drawable.movies, imageView.getContext().getTheme());
        loadImage(url, imageView, placeHolder, placeHolder);
    }


    private void loadImage(String url, ImageView imageView, Drawable placeholderDrawable, Drawable errorDrawable) {
        mGlide.load(url)
                .placeholder(placeholderDrawable)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontTransform()
                .error(errorDrawable)
                .into(imageView);
    }
}