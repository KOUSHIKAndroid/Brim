package com.brim.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.brim.Font.AxiformaBook;
import com.brim.Font.AxiformaRegular;
import com.brim.Pojo.TransactionListData;
import com.brim.R;
import com.brim.Utils.ConvertLocal;

import org.json.JSONException;

import java.util.LinkedList;

/**
 * Created by apple on 22/08/17.
 */

public class RecentTransactionAdapter extends RecyclerView.Adapter<RecentTransactionAdapter.RCViewHolder> {

    Context mContext;
    LinkedList<TransactionListData> ListData;

    public RecentTransactionAdapter(Context activity, LinkedList<TransactionListData> recentTransactionList) {
        this.mContext=activity;
        this.ListData=recentTransactionList;

    }


    @Override
    public RecentTransactionAdapter.RCViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout, parent, false);
        return new RCViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecentTransactionAdapter.RCViewHolder holder, int position) {
        TransactionListData item=ListData.get(position);
        holder.icon.setImageResource(item.getImage());

        try {
            holder.TXT_Name.setText(item.getItemObject().getString("description"));
            holder.TXT_PRICE1.setText("$"+ ConvertLocal.priceWithDecimal(Double.parseDouble(item.getItemObject().getString("amount"))));
            holder.TXT_Type.setText(item.getItemObject().getJSONObject("category").getString("name"));


            if(item.getItemObject().getString("status").equals("posted"))
            {
                if(item.getItemObject().getString("transaction_date").equals(item.getItemObject().getString("posted_date")))
                    holder.TXT_Status.setText("Today");
                else
                    holder.TXT_Status.setText(item.getItemObject().getString("posted_date"));

            }else
            holder.TXT_Status.setText(item.getItemObject().getString("status"));


            if(item.getItemObject().getJSONObject("forex").getDouble("foreign_amount")>0)
                holder.LL_R_1.setVisibility(View.VISIBLE);
            else
                holder.LL_R_1.setVisibility(View.GONE);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return ListData.size();
    }

    public class RCViewHolder extends RecyclerView.ViewHolder {
        ImageView icon,LL_R_1;
        AxiformaBook TXT_Name,TXT_PRICE1;
        AxiformaRegular TXT_Type,TXT_Status;
        public RCViewHolder(View itemView) {
            super(itemView);
            icon=(ImageView)itemView.findViewById(R.id.IMG1);
            LL_R_1=(ImageView)itemView.findViewById(R.id.LL_R_1);
            TXT_Name=(AxiformaBook)itemView.findViewById(R.id.TXT_Name);
            TXT_PRICE1=(AxiformaBook)itemView.findViewById(R.id.TXT_PRICE1);
            TXT_Type=(AxiformaRegular)itemView.findViewById(R.id.TXT_Type);
            TXT_Status=(AxiformaRegular)itemView.findViewById(R.id.TXT_Status);

        }
    }
}
