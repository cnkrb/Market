package com.cenk.marketsmi.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cenk.marketsmi.Models.Basket;
import com.cenk.marketsmi.Models.InfoModel;
import com.cenk.marketsmi.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.Holder> {
    public List<InfoModel> itemList;
    public Context context;
    public Boolean value = false;
    public Integer pos = -1;

    private OnItemClickListener mListener;
    private OnDeleteItemClickListener deleteItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnDeleteItemClickListener {
        void onDeleteItemClick(int position);
    }

    public void setOnDeleteItemClickListener(OnDeleteItemClickListener listener) {
        deleteItemClickListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;
    }

    public InfoAdapter(List<InfoModel> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public InfoAdapter(List<InfoModel> itemList, Context context, Boolean value) {
        this.itemList = itemList;
        this.context = context;
        this.value = value;
    }

    @Override
    public InfoAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (value) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info_pay, parent, false);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int devicewidth = (int) (displayMetrics.widthPixels * 0.75);
            view.getLayoutParams().width = devicewidth;
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false);

        }
        return new InfoAdapter.Holder(view);

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(InfoAdapter.Holder holder, int position) {
        if (value) {
            if (position == pos) {
                holder.edit.setText("Seçildi");
                holder.edit.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_check_24, 0,R.drawable.ic_baseline_check_24_black, 0);
            } else {
                holder.edit.setText("Seç");
                holder.edit.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

            }
        }
        holder.name.setText(itemList.get(position).getUsername());
        holder.address.setText(itemList.get(position).getAddress());
        holder.number.setText(itemList.get(position).getNumber());


    }

    @Override
    public int getItemCount() {
        return itemList.size();

    }

    void deneme(Integer positon) {
        pos = positon;
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name, address, number;
        Button edit,delete;

        public Holder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            address = (TextView) itemView.findViewById(R.id.address);
            number = (TextView) itemView.findViewById(R.id.number);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        deleteItemClickListener.onDeleteItemClick(position);
                    }
                }
            });
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                            deneme(position);
                        }
                    }
                }
            });
        }
    }

}