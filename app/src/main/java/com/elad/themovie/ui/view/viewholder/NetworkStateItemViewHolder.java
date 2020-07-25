package com.elad.themovie.ui.view.viewholder;


import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.elad.themovie.R;
import com.elad.themovie.service.repository.storge.model.NetworkState;

import androidx.recyclerview.widget.RecyclerView;

//*****************************************************
public class NetworkStateItemViewHolder
        extends RecyclerView.ViewHolder
//*****************************************************
{

    private final ProgressBar progressBar;
    private final TextView errorMsg;

    public NetworkStateItemViewHolder(View itemView)
    {
        super(itemView);
        progressBar = itemView.findViewById(R.id.progress_bar);
        errorMsg = itemView.findViewById(R.id.error_msg);
    }


    public void bindView(NetworkState networkState)
    {
        if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else
        {
            progressBar.setVisibility(View.GONE);
        }

        if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED)
        {
            errorMsg.setVisibility(View.VISIBLE);
            errorMsg.setText(networkState.getMsg());
        }
        else
        {
            errorMsg.setVisibility(View.GONE);
        }
    }
}