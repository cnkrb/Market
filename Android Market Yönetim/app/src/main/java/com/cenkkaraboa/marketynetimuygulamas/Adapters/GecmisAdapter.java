package com.cenkkaraboa.marketynetimuygulamas.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cenkkaraboa.marketynetimuygulamas.Models.Gecmis;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeKategori;
import com.cenkkaraboa.marketynetimuygulamas.Models.Urun;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;


public class GecmisAdapter extends RecyclerView.Adapter<GecmisAdapter.Holder> {
    public List<Gecmis> itemList;
    public Context context;
    public List<Urun> urunList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position,String kat,String altkat,String marka,String urun,String tur,String fiyat,String adet);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public GecmisAdapter(List<Gecmis> itemList , List<Urun> urunList, Context context) {
        this.itemList = itemList;
        this.urunList = urunList;
        this.context = context;
    }

    @Override
    public GecmisAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GecmisAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stok, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(GecmisAdapter.Holder holder, int position) {
        try {
            for(int i=0;i<urunList.size();i++){
                if(urunList.get(i).getUrunId().toString().equals(itemList.get(position).getUrunId().toString())){
                    holder.urun.setText(urunList.get(i).getGetUrun().getUrunAdi().toString());
                    break;
                }
            }
            if(itemList.get(0).getTuru().toString().equals("1")){
                holder.kat.setText("Ekleme");
                holder.altkat.setText(itemList.get(position).getAlisFiyat());
            }else {
                holder.kat.setText("Çıkartma");
            }
            holder.stok.setText(itemList.get(position).getAdet());
        }catch (Exception E){

        }


    }

    @Override
    public int getItemCount() {
        return itemList.size();

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView kat,altkat,urun,stok;
        LinearLayout detail;
        public Holder(final View itemView) {
            super(itemView);
            kat = (TextView) itemView.findViewById(R.id.kat);
            altkat = (TextView) itemView.findViewById(R.id.altkat);
            urun = (TextView) itemView.findViewById(R.id.urun);
            stok = (TextView) itemView.findViewById(R.id.stok);
            detail = itemView.findViewById(R.id.detail);

            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {

                            String urun_adi="";
                            String kat_adi="";
                            String altkat_adi="";
                            String marka_adi="";
                            for(int i=0;i<urunList.size();i++){
                                if(urunList.get(i).getUrunId().toString().equals(itemList.get(position).getUrunId().toString())){
                                    urun_adi=(urunList.get(i).getGetUrun().getUrunAdi().toString());
                                    kat_adi=(urunList.get(i).getGetUrun().getAltkatagori().get(0).getAltkatagoriAdi());
                                    altkat_adi=(urunList.get(i).getGetUrun().getAltkatagori().get(0).getGetKatagori().get(0).getKatagoriAdi());
                                    marka_adi=(urunList.get(i).getGetUrun().getGetMarka().get(0).getMarkaAdi());
                                    break;
                                }
                            }
                            mListener.onItemClick(position,kat_adi,altkat_adi,marka_adi,urun_adi,itemList.get(0).getTuru().toString(),itemList.get(position).getAlisFiyat(),itemList.get(position).getAdet());
                        }
                    }
                }
            });
        }
    }


}