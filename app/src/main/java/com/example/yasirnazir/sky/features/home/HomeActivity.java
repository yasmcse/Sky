package com.example.yasirnazir.sky.features.home;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.yasirnazir.sky.BaseActivity;
import com.example.yasirnazir.sky.R;
import com.example.yasirnazir.sky.features.home.adpaters.SearchRecyclerAdapter;
import com.example.yasirnazir.sky.models.ApiError;
import com.example.yasirnazir.sky.models.Data;
import com.example.yasirnazir.sky.networking.NetworkService;


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
    private final PublishSubject<Object> initiateSearchIntentEmitter = PublishSubject.create();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.app_name));
        getDeps().inject(this);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
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
