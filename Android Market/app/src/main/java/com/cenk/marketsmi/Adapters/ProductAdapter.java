package com.cenk.marketsmi.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cenk.marketsmi.Models.Product;
import com.cenk.marketsmi.Models.SearchAltCategori;
import com.cenk.marketsmi.Models.SearchCategori;
import com.cenk.marketsmi.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder> {
    public List<SearchCategori> itemList;
    public List<SearchAltCategori> itemListt;
    public List<Product>  productList;
    public Context context;
    Boolean value=false;
    Integer a=0;

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ProductAdapter(List<SearchCategori> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public ProductAdapter(List<SearchAltCategori> itemListt, Context context,Boolean value) {
        this.itemListt = itemListt;
        this.context = context;
        this.value=value;
    }

    public ProductAdapter(List<Product> productList, Context context,Integer a) {
        this.productList = productList;
        this.context = context;
        this.a = a;

    }

    @Override
    public ProductAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false));
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ProductAdapter.Holder holder, int position) {


       try {
           if(value){

               holder.productName.setText(itemListt.get(position).getUrunAdi());
               Picasso.with(context)
                       .load("https://market.cenkkaraboa.com/public/images/urun/"+itemListt.get(position).getGorselUrl())
                       .into(holder.productImage);

               System.out.println(itemListt.get(position).getStok());
               if(itemListt.get(position).getUrunTuru().equals("1")){
                   holder.productPrice.setText(itemListt.get(position).getIndirimFiyat()+" TL");

               }else {
                   holder.productPrice.setText(itemListt.get(position).getSatisFiyat()+" TL");
               }


           }else {
               if(a==0){
                   holder.productName.setText(itemList.get(position).getUrunAdi());

                   System.out.println(itemList.get(position).getStok());
                   Picasso.with(context)
                           .load("https://market.cenkkaraboa.com/public/images/urun/"+itemList.get(position).getGorselUrl())
                           .into(holder.productImage);


                   if(itemList.get(position).getUrunTuru().equals("1")){
                       holder.productPrice.setText(itemList.get(position).getIndirimFiyat()+" TL");

                   }else {
                       holder.productPrice.setText(itemList.get(position).getSatisFiyat()+" TL");
                   }
               }else {
                   if(productList.get(position).getGorselUrl() !=null){
                       Picasso.with(context)
                               .load("https://market.cenkkaraboa.com/public/images/urun/"+productList.get(position).getGorselUrl())
                               .into(holder.productImage);
                   }

                   try {

                       System.out.println(productList.get(position).getStok());
                       holder.productName.setText(productList.get(position).getUrunAdi());


                       if(productList.get(position).getUrunTuru().equals("1")){
                           holder.productPrice.setText(productList.get(position).getIndirimFiyat()+" TL");

                       }else {
                           holder.productPrice.setText(productList.get(position).getSatisFiyat()+" TL");
                       }
                   }catch (Exception e){

                   }
               }

           }
       }catch (Exception e){

       }



    }

    @Override
    public int getItemCount() {
        if(value){
            return itemListt.size();
        }else{
            if(a==0){
                return itemList.size();

            }else {
                return productList.size();
            }
        }

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView productName,productPrice;
        ImageView productImage;
        Button addStore;
        public Holder(final View itemView) {
            super(itemView);
            productName = (TextView) itemView.findViewById(R.id.productName);
            productPrice = (TextView) itemView.findViewById(R.id.productPrice);
            productImage =itemView.findViewById(R.id.productImage);
            addStore =itemView.findViewById(R.id.addStore);
         ;   addStore.setOnClickListener(new View.OnClickListener() {
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