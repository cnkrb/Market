package com.cenkkaraboa.marketynetimuygulamas.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cenkkaraboa.marketynetimuygulamas.Models.Marka;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeAltKategori;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeKategori;
import com.cenkkaraboa.marketynetimuygulamas.Models.Subekatagorus;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.ArrayList;
import java.util.List;


public class AltKategoriAdapter extends RecyclerView.Adapter<AltKategoriAdapter.Holder>   implements Filterable {
    public List<SubeAltKategori> itemList;
    public List<SubeAltKategori> newList;
    public List<Marka> markas;
    public List<Marka> newMarkas;
    public Context context;
    public Boolean value=false;

    private OnItemClickListener mListener;



    public interface OnItemClickListener {
        void onItemClick(int position,int id,String name);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public AltKategoriAdapter(List<SubeAltKategori> itemList , Context context) {
        this.itemList = itemList;
        this.newList = new ArrayList<>(itemList);
        this.context = context;
    }


    public AltKategoriAdapter(List<Marka> markas , Context context,Boolean value) {
        this.markas = markas;
        this.newMarkas = new ArrayList<>(markas);
        this.context = context;
        this.value = value;
    }

    @Override
    public AltKategoriAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(value){
            return new AltKategoriAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.marka, parent, false));

        }else  {
            return new AltKategoriAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alt_kategori, parent, false));

        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(AltKategoriAdapter.Holder holder, int position) {
        try {
            if(value){
                holder.category.setText(markas.get(position).getGetMarka().getAltkatagori().get(0).getGetKatagori().get(0).getKatagoriAdi());
                holder.alt_category.setText(markas.get(position).getGetMarka().getAltkatagori().get(0).getAltkatagoriAdi());
                holder.marka.setText(markas.get(position).getGetMarka().getMarkaAdi());
            }else  {
                holder.category.setText(itemList.get(position).getGetAltkatagori().getGetKatagori().get(0).getKatagoriAdi());
                holder.alt_category.setText(itemList.get(position).getGetAltkatagori().getAltkatagoriAdi());
            }

        }catch (Exception E){

        }




    }

    @Override
    public int getItemCount() {
        if(value){
            return markas.size();
        }else  {
            return itemList.size();
        }


    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView category,alt_category,marka;
        LinearLayout detail;
        public Holder(final View itemView) {
            super(itemView);

            if(value){
                marka = (TextView) itemView.findViewById(R.id.marka);
            }
            category = (TextView) itemView.findViewById(R.id.category);
            detail = itemView.findViewById(R.id.detail);
            alt_category = (TextView) itemView.findViewById(R.id.alt_category);

            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            if(value){
                                mListener.onItemClick(position,markas.get(position).getGetMarka().getId(),markas.get(position).getGetMarka().getMarkaAdi());

                            }else {

                            mListener.onItemClick(position,itemList.get(position).getGetAltkatagori().getId(),itemList.get(position).getGetAltkatagori().getAltkatagoriAdi());

                            }
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

            if(value){

                List<Marka> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(newMarkas);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Marka item : newMarkas) {
                        if (item.getGetMarka().getMarkaAdi().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                results.values = filteredList;

                return results;
            }else  {


                List<SubeAltKategori> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(newList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (SubeAltKategori item : newList) {
                        if (item.getGetAltkatagori().getAltkatagoriAdi().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                results.values = filteredList;

                return results;
            }



        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if(value){
                if(markas != null){
                    markas.clear();
                }
                markas.addAll((List) results.values);
                notifyDataSetChanged();
            }else  {
                if(itemList != null){
                    itemList.clear();
                }
                itemList.addAll((List) results.values);
                notifyDataSetChanged();
            }






        }
    };


}