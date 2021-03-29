package com.cenkkaraboa.marketynetimuygulamas.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.cenkkaraboa.marketynetimuygulamas.Models.Basket;
import com.cenkkaraboa.marketynetimuygulamas.Models.GetSepet;
import com.cenkkaraboa.marketynetimuygulamas.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.Holder> {
    public List<Basket> itemList;
    public List<GetSepet> orderList;
    public Context context;
    public  Boolean aBoolean=false;

    private OnItemClickListener mListener;
    private OnItemAddClickListener onItemAddClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemAddClickListener {
        void onItemAddClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public void setOnItemAddClickListener(OnItemAddClickListener onItemAddClickListener) {
        this.onItemAddClickListener = onItemAddClickListener;
    }

    public BasketAdapter(List<Basket> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public BasketAdapter(List<GetSepet> orderList, Context context, Boolean aBoolean) {
        this.orderList = orderList;
        this.context = context;
        this.aBoolean = aBoolean;
    }

    @Override
    public BasketAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BasketAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_basket, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(Holder holder, int position) {
      if(aBoolean){
          holder.name.setText(orderList.get(position).getUrunAdi());

             holder.adet.setText(orderList.get(position).getSatisFiyat()+" TL");

             holder.price.setText(String.valueOf(round(Double.parseDouble(orderList.get(position).getSatisFiyat())* Double.parseDouble(orderList.get(position).getAdet()),2))+" TL");


          holder.x.setText(orderList.get(position).getAdet()+" Adet");
          holder.patch.setVisibility(View.GONE);
          holder.delete.setVisibility(View.GONE);
          holder.add.setVisibility(View.GONE);
      }else {


          holder.name.setText(itemList.get(position).getName());
          holder.adet.setText(itemList.get(position).getPrice()+" TL");
          holder.x.setText(itemList.get(position).getX()+"x");
          holder.price.setText(String.valueOf(round(Double.parseDouble(itemList.get(position).getPrice())* Double.parseDouble(itemList.get(position).getX()),2))+" TL");
          Picasso.with(context)
                  .load("http://market.cenkkaraboa.com/public/images/urun/"+itemList.get(position).getPatch())
                  .into(holder.patch);
      }

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }


    @Override
    public int getItemCount() {
        if(aBoolean){
            return orderList.size();

        }else {
            return itemList.size();

        }

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name,price,x,adet;
        ImageView patch;
        ImageButton delete,add;
        public Holder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            x = (TextView) itemView.findViewById(R.id.x);
            patch =itemView.findViewById(R.id.patch);
            adet =itemView.findViewById(R.id.adet);
            delete =itemView.findViewById(R.id.delete);
            add =itemView.findViewById(R.id.add);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemAddClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemAddClickListener.onItemAddClick(position);
                        }
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
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