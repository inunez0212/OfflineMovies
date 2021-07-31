package com.example.offlinemovies.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.offlinemovies.R;
import com.example.offlinemovies.data.entity.MovieEntity;
import com.example.offlinemovies.viewmodel.MovieViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class MovieFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private List<MovieEntity> movieEntityList;
    private MyMovieRecyclerViewAdapter adapter;
    private MovieViewModel movieViewModel;

    public MovieFragment() {
    }

    public static MovieFragment newInstance(int columnCount) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        movieViewModel = new ViewModelProvider(requireActivity()).get(MovieViewModel.class);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new MyMovieRecyclerViewAdapter(getActivity(), movieEntityList);
            recyclerView.setAdapter(adapter);
            loadPopularMovies();
        }
        return view;
    }

    private void loadPopularMovies() {
        movieViewModel.getPopularMovies().observe(requireActivity(),
            listResource -> {
                this.movieEntityList = listResource.data;
                adapter.setData(this.movieEntityList);
            });
    }
}