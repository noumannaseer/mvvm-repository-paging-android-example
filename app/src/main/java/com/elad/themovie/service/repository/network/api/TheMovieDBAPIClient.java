package com.elad.themovie.service.repository.network.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.elad.themovie.Constants.MOVIE_ARRAY_LIST_CLASS_TYPE;
import static com.elad.themovie.Constants.POPULAR_MOVIES_BASE_URL;
//*****************************************************
public class TheMovieDBAPIClient
//*****************************************************
{

    //*****************************************************
    public static MoviesAPIInterface getClient()
    //*****************************************************
    {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        // create OkHttpClient and register an interceptor
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                                                        .build();

        Gson gson = new GsonBuilder()
                // we remove from the response some wrapper tags from our movies array
                .registerTypeAdapter(MOVIE_ARRAY_LIST_CLASS_TYPE, new MoviesJsonDeserializer())
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(POPULAR_MOVIES_BASE_URL);

        return builder.build()
                      .create(MoviesAPIInterface.class);
    }
}
