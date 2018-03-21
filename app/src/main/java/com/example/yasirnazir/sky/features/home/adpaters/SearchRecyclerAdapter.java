package com.example.yasirnazir.sky.features.home.adpaters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yasirnazir.sky.R;
import com.example.yasirnazir.sky.base_classes.ClickableAdapter;
import com.example.yasirnazir.sky.features.home.view_holders.SearchViewHolder;
import com.example.yasirnazir.sky.models.Data;

import rx.subjects.PublishSubject;

/**
 * Created by yasirnazir on 3/20/18.
 */

public class SearchRecyclerAdapter extends ClickableAdapter<Data, SearchViewHolder> {

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType, PublishSubject<Data> itemClickedSubject) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_search, parent, false);
        return new SearchViewHolder(view, itemClickedSubject,parent.getContext());
    }


}