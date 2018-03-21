package com.example.yasirnazir.sky.features.home.view_holders;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yasirnazir.sky.R;
import com.example.yasirnazir.sky.base_classes.ClickableViewHolder;
import com.example.yasirnazir.sky.models.Data;
import com.example.yasirnazir.sky.utils.display.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subjects.PublishSubject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by yasirnazir on 3/20/18.
 */
public class SearchViewHolder extends ClickableViewHolder<Data> {
    Context context;
    private final ImageLoader mImageLoader;

    @BindView(R.id.icon_image_view)
    ImageView moviePosterView;

    @BindView(R.id.genre_text_view)
    TextView genreTextView;

    public SearchViewHolder(View itemView, PublishSubject<Data> positionClickedSubject, Context context) {
        super(itemView, positionClickedSubject);
        mImageLoader = new ImageLoader(context);
        ButterKnife.bind(this, itemView);
    }

    @Override
    protected void bind(Data data) {
        mImageLoader.loadImage(data.getPoster(), moviePosterView);
        genreTextView.setText(data.getGenre());
    }
}

