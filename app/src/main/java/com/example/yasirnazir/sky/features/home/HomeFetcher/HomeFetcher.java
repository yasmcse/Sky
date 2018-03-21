package com.example.yasirnazir.sky.features.home.HomeFetcher;

import com.example.yasirnazir.sky.base_classes.Fetcher;
import com.example.yasirnazir.sky.models.ApiError;
import com.example.yasirnazir.sky.models.MoviesApi;
import com.example.yasirnazir.sky.models.Response;
import com.example.yasirnazir.sky.networking.NetworkService;

/**
 * Created by yasirnazir on 3/20/18.
 */

public class HomeFetcher extends Fetcher<Response> {
    public HomeFetcher(NetworkService networkService) {
        super(() -> networkService.getMoviesList()
                .doOnNext(HomeFetcher::checkIfErrorExists), MoviesApi.GET_MOVIES);
    }

    private static void checkIfErrorExists(Response response) {
        if (response == null) {
            throw new ApiError();
        }
    }
}