package com.example.offlinemovies.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@Entity(tableName = "movies")
public class MovieEntity {

    @PrimaryKey
    private int id;

    private double popularity;
    private int voteCount;
    private boolean video;
    private String posterPath;
    private boolean adult;
    private String backdropPath;
    private String originalLanguage;
    private String originalTitle;
    private String title;
    private double voteAverage;
    private String overview;
    private String releaseDate;
}
