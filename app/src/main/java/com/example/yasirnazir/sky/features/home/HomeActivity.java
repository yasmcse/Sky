package com.example.yasirnazir.sky.features.home;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;



import com.example.yasirnazir.sky.BaseActivity;
import com.example.yasirnazir.sky.R;
import com.example.yasirnazir.sky.features.home.adpaters.SearchRecyclerAdapter;
import com.example.yasirnazir.sky.models.ApiError;
import com.example.yasirnazir.sky.models.Data;
import com.example.yasirnazir.sky.networking.NetworkService;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT;
import static com.example.yasirnazir.sky.utils.display.BindingExtensions.searchButtonClicks;

public class HomeActivity extends BaseActivity implements HomePresenter.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private Toolbar toolbar;
    private EditText searchInputView;


    @Inject
    NetworkService networkService;
    private HomePresenter homePresenter;
    private SearchRecyclerAdapter searchRecyclerAdapter = new SearchRecyclerAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initSearchBar();
        setupRecyclerView();
        homePresenter = new HomePresenter(networkService);
        homePresenter.attach(this);

    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(searchRecyclerAdapter);
        recyclerView.setBackgroundResource(R.color.white);
        int padding = getResources().getDimensionPixelSize(R.dimen.grid_0_25x);
        recyclerView.setPadding(padding, padding, padding, padding);
    }

    private void initSearchBar() {
        setTitle(getString(R.string.app_name));
        toolbar = findViewById(R.id.toolbar);
        searchInputView = (EditText) findViewById(R.id.search_edit_text);
        searchInputView.setOnFocusChangeListener((view, focused) -> {
            if (focused) {
                searchInputView.post(() -> {
                    InputMethodManager imm = (InputMethodManager) toolbar.getContext().getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(searchInputView, SHOW_IMPLICIT);
                });
            }
        });

        searchInputView.setOnTouchListener((v, event) -> {
            searchInputView.requestFocus();
            return true;
        });

    }


    @Override
    public Observable<String> onSearchQuerySubmitIntent() {
        return searchButtonClicks(searchInputView)
                .map(event -> searchInputView.getText().toString())
                .filter(query -> query.length() > 0);
    }

    @Override
    public void performSearch(String keywords,List<Data> dataList) {
        List<Data> filteredList = new ArrayList<>();

        if(dataList != null){
            for(Data item: dataList){
                if(keywords.equalsIgnoreCase(item.getGenre()) || keywords.equalsIgnoreCase(item.getTitle())){
                    filteredList.add(item);
                }
            }
            searchRecyclerAdapter.setItems(filteredList);
            searchRecyclerAdapter.notifyDataSetChanged();
        }

    }



    @Override
    public void showLoading(boolean show) {
        progressBar.setVisibility(show ? VISIBLE : GONE);
    }

    @Override
    public void showError(ApiError apiError) {
        showErrorMessage(apiError.getMessage());
    }

    private void showErrorMessage(String message) {
        View view = this.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(view.getContext().getResources().getColor(R.color.red));
        TextView snackbartextView = snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        snackbartextView.setMaxLines(3);

        snackbar.show();
    }

    @Override
    public void displayMoviesData(List<Data> dataList) {
        searchRecyclerAdapter.setItems(dataList);
    }


}
