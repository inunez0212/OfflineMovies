package com.example.offlinemovies.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.offlinemovies.common.Constantes;
import com.example.offlinemovies.data.entity.MovieEntity;
import com.example.offlinemovies.databinding.FragmentMovieBinding;

import java.util.List;

/**
 * TODO: Replace the implementation with code for your data type.
 */
public class MyMovieRecyclerViewAdapter
        extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder>{

    private List<MovieEntity> mValues;
    private Context context;

    public MyMovieRecyclerViewAdapter(Context context, List<MovieEntity> items) {
        mValues = items;
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    public void setData(List<MovieEntity> moviesParam){
        this.mValues = moviesParam;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textMovieTitle.setText(holder.mItem.getTitle());
        if(holder.mItem.getPoster_path()!=null){
            Glide.with(context)
                    .load(Constantes.REMOTE_URL_IMAGES_PATH+holder.mItem.getPoster_path())
                    .into(holder.imageViewCover);
        }

    }

    @Override
    public int getItemCount() {
        if(mValues!=null){
            return mValues.size();
        }else{
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public MovieEntity mItem;
        public ImageView imageViewCover;
        public TextView textMovieTitle;

        public ViewHolder(FragmentMovieBinding binding) {
            super(binding.getRoot());
            imageViewCover = binding.imageViewCover;
            textMovieTitle = binding.textMovieTitle;
        }

    }
}