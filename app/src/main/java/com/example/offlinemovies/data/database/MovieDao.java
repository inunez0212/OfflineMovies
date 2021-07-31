package com.example.offlinemovies.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.offlinemovies.data.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("select * from movies")
    LiveData<List<MovieEntity>> loadMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntity> movieEntityList);

}
