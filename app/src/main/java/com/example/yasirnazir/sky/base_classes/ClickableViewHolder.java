package com.example.yasirnazir.sky.base_classes;

/**
 * Created by yasirnazir on 3/20/18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.View;

import rx.subjects.PublishSubject;

/**
 * Base ViewHolder that allows clicks to be observed.
 */
public abstract class ClickableViewHolder<T> extends RecyclerView.ViewHolder {
    private T data;

    public ClickableViewHolder(View itemView, PublishSubject<T> itemClickedSubject) {
        super(itemView);
        itemView.setOnClickListener(v -> itemClickedSubject.onNext(data));
    }

    public void bindView(T data) {
        this.data = data;
        bind(data);
    }

    protected abstract void bind(T data);
}