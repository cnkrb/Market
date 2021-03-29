package com.cenk.marketsmi.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cenk.marketsmi.Models.Sube;
import com.cenk.marketsmi.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.Holder> {
    public List<Sube> itemList;
    public Context context;

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public StoreAdapter(List<Sube> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public StoreAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StoreAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(StoreAdapter.Holder holder, int position) {
       try {
           holder.ad.setText(itemList.get(position).getSubeAdi());
           Picasso.with(context)
                   .load("https://market.cenkkaraboa.com/public/images/hakkimda/"+itemList.get(position).getGorselUrl())
                   .into(holder.market);
           holder.address.setText(itemList.get(position).getAdres().toString());
           holder.timer.setText(itemList.get(position).getServis().toString()+" dk");
       }catch (Exception E){

       }


   //     holder.date.setText(itemList.get(itemList.size()-(position+1)).getDate().toString());
    }

    @Override
    public int getItemCount() {
        return itemList.size();

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView ad, timer,address;
        ImageView market;
        public Holder(final View itemView) {
            super(itemView);
            ad = (TextView) itemView.findViewById(R.id.name);
            timer = (TextView) itemView.findViewById(R.id.time);
            address = (TextView) itemView.findViewById(R.id.address);
            market =itemView.findViewById(R.id.market);
         ;   itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}