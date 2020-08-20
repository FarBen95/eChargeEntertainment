package com.farben.echargeentertainment.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farben.echargeentertainment.R;
import com.farben.echargeentertainment.db.entity.WebsiteEntity;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WebsiteListAdapter extends RecyclerView.Adapter<WebsiteListAdapter.WebsiteViewHolder> {

    public class WebsiteViewHolder extends RecyclerView.ViewHolder {
        public final TextView websiteItemView;

        public WebsiteViewHolder(View itemView) {
            super(itemView);
            websiteItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<WebsiteEntity> mWebsites; // Cached copy of words

    public WebsiteListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WebsiteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WebsiteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WebsiteViewHolder holder, int position) {
        if (mWebsites != null) {
            WebsiteEntity current = mWebsites.get(position);
            holder.websiteItemView.setText(current.getLink());
        } else {
            // Covers the case of data not being ready yet.
            holder.websiteItemView.setText("No Website Yet");
        }
    }

    public void setWebsites(List<WebsiteEntity> websites) {
        mWebsites = websites;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWebsites has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWebsites != null)
            return mWebsites.size();
        else return 0;
    }
}
