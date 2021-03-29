package com.cenkkaraboa.marketynetimuygulamas.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cenkkaraboa.marketynetimuygulamas.Models.AdminSiparis;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.User;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;


public class AdminSiparisAdapter extends RecyclerView.Adapter<AdminSiparisAdapter.Holder> {
    public List<AdminSiparis> itemList;
    public Context context;
    public  List<Sube> subeList;
    public  Boolean value=false;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public AdminSiparisAdapter(List<AdminSiparis> itemList, List<Sube> subeList,Context context) {
        this.itemList = itemList;
        this.subeList = subeList;
        this.context = context;
    }

    public AdminSiparisAdapter(List<AdminSiparis> itemList, List<Sube> subeList,Context context,Boolean value) {
        this.itemList = itemList;
        this.subeList = subeList;
        this.context = context;
        this.value = value;
    }

    @Override
    public AdminSiparisAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(value){
            return new AdminSiparisAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sube_siparis, parent, false));

        }else {
            return new AdminSiparisAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admin_siparis, parent, false));
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(AdminSiparisAdapter.Holder holder, int position) {
        try {
            holder.user.setText(itemList.get(position).getKullaniciAdi());
          //  holder.adres.setText(itemList.get(position).getAdres());
        //    holder.number.setText(itemList.get(position).getTelefon());
            if (!value){
                if(subeList.size()>0){
                    for(int i=0;i<subeList.size();i++){
                        if(itemList.get(position).getSubeId().toString().equals(subeList.get(i).getId().toString())){
                            holder.sube.setText(subeList.get(i).getSubeAdi());
                        }
                    }
                }else {
                    holder.sube.setText("Geçerli Şube yok");
                }
            }
            holder.kurye.setText(itemList.get(position).getGetKurye().getKuryeAdi());
            holder.kasiyer.setText(itemList.get(position).getGetUser().getName());


        }catch (Exception E){

        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView user,adres,number,kurye,kasiyer,sube;
        LinearLayout detail;
        public Holder(final View itemView) {
            super(itemView);
            if(!value){
                sube = (TextView) itemView.findViewById(R.id.sube);
            }
            user = (TextView) itemView.findViewById(R.id.user);
            detail = itemView.findViewById(R.id.detail);
            kurye = (TextView) itemView.findViewById(R.id.kurye);
            kasiyer = (TextView) itemView.findViewById(R.id.kasiyer);

            detail.setOnClickListener(new View.OnClickListener() {
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