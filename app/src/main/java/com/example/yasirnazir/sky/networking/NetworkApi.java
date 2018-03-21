package com.example.yasirnazir.sky.networking;

import com.example.yasirnazir.sky.models.MoviesApi;
import com.example.yasirnazir.sky.models.Response;

import retrofit2.http.GET;
import rx.Observable;


/**
 * Created by yasirnazir on 3/14/18.
 */
public interface NetworkApi {

    @GET(MoviesApi.GET_MOVIES)
    Observable<Response> getMoviesList();
}