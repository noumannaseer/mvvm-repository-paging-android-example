package com.elad.themovie.service.repository.network.api;

import com.elad.themovie.service.repository.storge.model.Movie;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.elad.themovie.Constants.API_KEY_REQUEST_PARAM;
import static com.elad.themovie.Constants.LANGUAGE_REQUEST_PARAM;
import static com.elad.themovie.Constants.PAGE_REQUEST_PARAM;

//*****************************************************
public interface MoviesAPIInterface
//*****************************************************
{

    @GET(".")
    Call<ArrayList<Movie>> getMovies(@Query(API_KEY_REQUEST_PARAM) String apiKey,
                                     @Query(LANGUAGE_REQUEST_PARAM) String language,
                                     @Query(PAGE_REQUEST_PARAM) int page);
}
