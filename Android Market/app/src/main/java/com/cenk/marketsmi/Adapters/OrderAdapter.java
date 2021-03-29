package com.cenk.marketsmi.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cenk.marketsmi.Models.Siparis;
import com.cenk.marketsmi.Models.Sube;
import com.cenk.marketsmi.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Holder> {
    public List<Siparis> itemList;
    public Context context;

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public OrderAdapter(List<Siparis> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public OrderAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OrderAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(OrderAdapter.Holder holder, int position) {
        holder.date.setText(itemList.get(position).getCreatedAt());
        if(itemList.get(position).getDurumId().equals("0")){
            holder.status.setText("Sipariş Onaylanıyor");

        }
        if(itemList.get(position).getDurumId().equals("1")){
            holder.status.setText("Sipariş Hazırlanıyor");

        }
        if(itemList.get(position).getDurumId().equals("2")){
            holder.status.setText("Sipariş Yolda");

        }
        if(itemList.get(position).getDurumId().equals("3")){
            holder.status.setText("Sipariş Tamamlandı");

        }
        if(itemList.get(position).getDurumId().equals("4")){
            holder.cancelLaoyut.setVisibility(View.VISIBLE);
            holder.status.setText("Siparişin iptali edildi.");
            holder.cancel.setText(itemList.get(position).getIptalAciklama().toString());
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView total,date,status,cancel;
        Button details;
        LinearLayout cancelLaoyut;

        ListView  listView;
        public Holder(final View itemView) {
            super(itemView);
            total = (TextView) itemView.findViewById(R.id.total);
            cancel = (TextView) itemView.findViewById(R.id.cancel);
            status = (TextView) itemView.findViewById(R.id.status);
            date = (TextView) itemView.findViewById(R.id.date);
            details =itemView.findViewById(R.id.detail);
            cancelLaoyut =itemView.findViewById(R.id.cancelLayout);
         ;   details.setOnClickListener(new View.OnClickListener() {
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