package com.cenkkaraboa.marketynetimuygulamas.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cenkkaraboa.marketynetimuygulamas.Models.Product;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeKategori;
import com.cenkkaraboa.marketynetimuygulamas.Models.Subekatagorus;
import com.cenkkaraboa.marketynetimuygulamas.Models.Urun;
import com.cenkkaraboa.marketynetimuygulamas.Models.User;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.ArrayList;
import java.util.List;


public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.Holder>   implements Filterable {
    public Context context;
    List<Subekatagorus> newList;
    List<Subekatagorus> itemList;

    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position,int id,String name);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public KategoriAdapter(List<Subekatagorus> itemList ,Context context) {
        this.itemList = itemList;
        this.newList = new ArrayList<>(itemList);
        this.context = context;
    }

    @Override
    public KategoriAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new KategoriAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kategori, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(KategoriAdapter.Holder holder, int position) {
        try {
            holder.name.setText(itemList.get(position).getGetKatagori().getKatagoriAdi());
        }catch (Exception E){

        }


    }

    @Override
    public int getItemCount() {
        return itemList.size();

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        CardView detail;
        public Holder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            detail = itemView.findViewById(R.id.detail);

            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                                mListener.onItemClick(position,itemList.get(position).getGetKatagori().getId(),itemList.get(position).getGetKatagori().getKatagoriAdi());
                        }
                    }
                }
            });
        }
    }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();


                List<Subekatagorus> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(newList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Subekatagorus item : newList) {
                        if (item.getGetKatagori().getKatagoriAdi().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                results.values = filteredList;

            return results;

        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                if(itemList != null){
                    itemList.clear();
                }
                itemList.addAll((List) results.values);
                notifyDataSetChanged();

        }
    };



}