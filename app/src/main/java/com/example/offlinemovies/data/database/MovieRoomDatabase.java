package com.example.offlinemovies.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.offlinemovies.data.entity.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 2, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();

}
