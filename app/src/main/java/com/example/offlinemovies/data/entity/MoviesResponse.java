package com.example.offlinemovies.data.entity;

import com.example.offlinemovies.data.entity.MovieEntity;

import java.util.List;

import lombok.Data;

@Data
public class MoviesResponse {
    private List<MovieEntity> results;
}
