package com.brim.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brim.OfferDetails;
import com.brim.R;

import java.util.LinkedList;

/**
 * Created by apple on 17/08/17.
 */

public class ListAdapter_Offer extends RecyclerView.Adapter<ListAdapter_Offer.ViewHolder> {
    Context mCintext;
    LinkedList<String>ListData;
    public ListAdapter_Offer(Context baseActivity, LinkedList<String> listData) {
        this.mCintext=baseActivity;
        this.ListData=listData;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return ListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCintext.startActivity(new Intent(mCintext, OfferDetails.class));
                }
            });
        }
    }
}
