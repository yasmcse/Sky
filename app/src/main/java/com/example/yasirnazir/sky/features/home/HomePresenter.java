package com.example.yasirnazir.sky.features.home;

import com.example.yasirnazir.sky.base_classes.Presenter;
import com.example.yasirnazir.sky.features.home.HomeFetcher.HomeFetcher;
import com.example.yasirnazir.sky.models.ApiError;
import com.example.yasirnazir.sky.models.Data;
import com.example.yasirnazir.sky.networking.NetworkService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by yasirnazir on 3/20/18.
 */

public class HomePresenter extends Presenter<HomePresenter.View> {
    @Inject
    NetworkService networkService;

    private HomeFetcher homeFetcher;

    public HomePresenter(NetworkService networkService) {
        this.networkService = networkService;
        homeFetcher = new HomeFetcher(networkService);
    }

    @Override
    protected void onViewAttached(View view) {
        super.onViewAttached(view);
        addSubscription(homeFetcher.observeLoading().subscribe(view::showLoading));
        addSubscription(homeFetcher.observeErrors().subscribe(it -> {
            view.showError(it);
        }));
        addSubscription(homeFetcher.observeData().subscribe(it -> view.displayMoviesData(it.getData())));
    }


    @Override
    protected void onViewDetached(View view) {
        super.onViewDetached(view);
    }

    public interface View extends Presenter.View {

        void showLoading(boolean show);

        void showError(ApiError apiError);

        void displayMoviesData(List<Data> dataList);

    }
}