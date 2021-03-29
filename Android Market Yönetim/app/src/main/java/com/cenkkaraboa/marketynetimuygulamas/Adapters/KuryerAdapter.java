package com.cenkkaraboa.marketynetimuygulamas.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cenkkaraboa.marketynetimuygulamas.Models.Kurye;
import com.cenkkaraboa.marketynetimuygulamas.Models.Odeme;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.User;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;


public class KuryerAdapter extends RecyclerView.Adapter<KuryerAdapter.Holder> {
    public List<Kurye> itemList;
    public List<Odeme> odemeList;
    public Context context;
    Boolean value=false;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public KuryerAdapter(List<Kurye> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }


    public KuryerAdapter(List<Odeme> odemeList, Context context,Boolean value) {
        this.odemeList = odemeList;
        this.context = context;
        this.value = value;
    }

    @Override
    public KuryerAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(value){
            return new KuryerAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_odeme, parent, false));

        }else{
            return new KuryerAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kurye, parent, false));

        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(KuryerAdapter.Holder holder, int position) {
        try {
            if(value){
                holder.odeme.setText(odemeList.get(position).getOdemeTipi());
            }else {
                holder.name.setText(itemList.get(position).getKuryeAdi());
                holder.sube.setText(itemList.get(position).getGetMarket().getSubeAdi());

            }



        }catch (Exception E){

        }
    }

    @Override
    public int getItemCount() {
        if(value){
            return odemeList.size();
        }else {
            return itemList.size();
        }


    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView sube,name,role,mail;
        TextView rol,email,odeme;
        public Holder(final View itemView) {
            super(itemView);
            if (value){
                odeme = (TextView) itemView.findViewById(R.id.odeme);

            }else {
                sube = (TextView) itemView.findViewById(R.id.sube);
                name = (TextView) itemView.findViewById(R.id.name);
            }


        }
    }


}