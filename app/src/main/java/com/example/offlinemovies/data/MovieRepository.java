package com.example.offlinemovies.data;

import android.graphics.Movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.offlinemovies.common.Constantes;
import com.example.offlinemovies.common.MyApp;
import com.example.offlinemovies.data.database.MovieDao;
import com.example.offlinemovies.data.database.MovieRoomDatabase;
import com.example.offlinemovies.data.entity.MovieEntity;
import com.example.offlinemovies.data.entity.MoviesResponse;
import com.example.offlinemovies.data.retrofit.MovieApiService;
import com.example.offlinemovies.data.retrofit.RequestInterceptor;
import com.example.offlinemovies.networkbound.NetworkBoundResource;
import com.example.offlinemovies.networkbound.Resource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;
    private final MovieDao movieDao;

    public MovieRepository(){
        movieDao = getMovieDao();
        movieApiService = getMovieApiService();
    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies(){

        return new NetworkBoundResource<List<MovieEntity>, MoviesResponse>(){

            @Override
            protected void saveCallResult(@NonNull @NotNull MoviesResponse item) {
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @NotNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {

                return movieDao.loadMovies();
            }

            @NonNull
            @NotNull
            @Override
            protected Call<MoviesResponse> createCall() {
                return movieApiService.loadPopularMovies();
            }
        }.getAsLiveData();
    }

    private MovieApiService getMovieApiService() {
        final MovieApiService movieApiService;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieApiService = retrofit.create(MovieApiService.class);
        return movieApiService;
    }

    private MovieDao getMovieDao() {
        final MovieDao movieDao;
        MovieRoomDatabase movieRoomDatabase = Room.databaseBuilder(
                MyApp.getContexto(),
                MovieRoomDatabase.class,
                "db_movies"
        ).fallbackToDestructiveMigration().build();

        movieDao = movieRoomDatabase.getMovieDao();
        return movieDao;
    }


}
