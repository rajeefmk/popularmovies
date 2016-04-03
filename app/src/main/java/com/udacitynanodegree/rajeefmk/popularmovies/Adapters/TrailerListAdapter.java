package com.udacitynanodegree.rajeefmk.popularmovies.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.udacitynanodegree.rajeefmk.popularmovies.Models.Trailer;
import com.udacitynanodegree.rajeefmk.popularmovies.R;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.AppUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrailerListAdapter extends RecyclerView.Adapter<TrailerListAdapter.ViewHolder> {

    private final Context mContext;
    private List<Trailer> mDataset = new ArrayList<>();

    public TrailerListAdapter(Context context) {
        mContext = context;
    }

    public void setDataset(List<Trailer> movieList) {
        mDataset = movieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_trailer_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Trailer trailer = mDataset.get(position);
        holder.trailerName.setText(trailer.getName());
        holder.trailerDetails.setText(trailer.getSize() + " | " + trailer.getSite());
        holder.watchNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer.getKey()));
                    mContext.startActivity(intent);
                } catch (ActivityNotFoundException ex) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            AppUtils.getYoutubeUrl(trailer.getKey()));
                    mContext.startActivity(intent);
                }
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, mContext.getString(R.string.trailer_share_title));
                share.putExtra(Intent.EXTRA_TEXT, AppUtils.getYoutubeUrl(trailer.getKey()).toString());
                mContext.startActivity(Intent.createChooser(share, mContext.getString(R.string.trailer_choose_string)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.trailer_name)
        TextView trailerName;

        @Bind(R.id.detail)
        TextView trailerDetails;

        @Bind(R.id.watch_now)
        TextView watchNow;

        @Bind(R.id.share)
        TextView share;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
