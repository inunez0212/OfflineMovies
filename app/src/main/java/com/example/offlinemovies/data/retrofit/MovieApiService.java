package com.example.offlinemovies.data.retrofit;

import com.example.offlinemovies.data.entity.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {

    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMovies();
}
