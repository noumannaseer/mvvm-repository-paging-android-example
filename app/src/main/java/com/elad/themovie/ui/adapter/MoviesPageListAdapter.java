package com.elad.themovie.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elad.themovie.R;
import com.elad.themovie.service.repository.storge.model.Movie;
import com.elad.themovie.service.repository.storge.model.NetworkState;
import com.elad.themovie.ui.listeners.ItemClickListener;
import com.elad.themovie.ui.view.viewholder.MovieViewHolder;
import com.elad.themovie.ui.view.viewholder.NetworkStateItemViewHolder;

import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

//*****************************************************
public class MoviesPageListAdapter extends PagedListAdapter<Movie, RecyclerView.ViewHolder>
//*****************************************************
{

    private NetworkState networkState;
    private ItemClickListener itemClickListener;

    //*****************************************************
    public MoviesPageListAdapter(ItemClickListener itemClickListener)
    //*****************************************************
    {
        super(Movie.DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
    }

    //*****************************************************
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    //*****************************************************
    {
        RecyclerView.ViewHolder viewHolder = null;
        View baseView;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType)
        {
        case R.layout.movie_item:
            baseView = layoutInflater.inflate(R.layout.movie_item, parent, false);
            viewHolder = new MovieViewHolder(baseView, itemClickListener);
            break;
        case R.layout.network_state_item:
            baseView = layoutInflater.inflate(R.layout.network_state_item, parent, false);
            viewHolder = new NetworkStateItemViewHolder(baseView);
        }

        return viewHolder;

    }

    //*****************************************************
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    //*****************************************************
    {
        switch (getItemViewType(position))
        {
        case R.layout.movie_item:
            ((MovieViewHolder)holder).bindTo(getItem(position));
            break;
        case R.layout.network_state_item:
            ((NetworkStateItemViewHolder)holder).bindView(networkState);
            break;
        }
    }

    //*****************************************************
    private boolean hasExtraRow()
    //*****************************************************
    {
        return (networkState != null && networkState != NetworkState.LOADED);
    }

    //*****************************************************
    @Override
    public int getItemViewType(int position)
    //*****************************************************
    {
        if (hasExtraRow() && position == getItemCount() - 1)
            return R.layout.network_state_item;

        return R.layout.movie_item;

    }

    //*****************************************************
    public void setNetworkState(NetworkState newNetworkState)
    //*****************************************************
    {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow)
        {
            if (previousExtraRow)
            {
                notifyItemRemoved(getItemCount());
                return;
            }
            notifyItemInserted(getItemCount());
            return;
        }
        if (newExtraRow && previousState != newNetworkState)
        {
            notifyItemChanged(getItemCount() - 1);
            return;
        }
    }
}
