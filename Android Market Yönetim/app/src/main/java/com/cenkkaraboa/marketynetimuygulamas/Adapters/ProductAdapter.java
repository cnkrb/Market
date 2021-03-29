package com.cenkkaraboa.marketynetimuygulamas.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cenkkaraboa.marketynetimuygulamas.Models.Product;
import com.cenkkaraboa.marketynetimuygulamas.Models.Urun;
import com.cenkkaraboa.marketynetimuygulamas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Holder>  implements Filterable {
    public List<Urun> itemList;
    public List<Urun> newItem;
    public List<Product> newList;
    public List<Product> products;
    public List<Product> secu;
    Boolean value=false;
    public Context context;
    String aa="0";

    private OnItemClickListener mListener;




    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ProductAdapter(List<Urun> itemList, Context context) {
        this.itemList = itemList;
        this.newItem = new ArrayList<>(itemList);
        this.context = context;
    }

    public ProductAdapter(List<Urun> itemList, Context context,String aa) {
        this.itemList = itemList;
        this.newItem = new ArrayList<>(itemList);
        this.context = context;
        this.aa = aa;
    }

    public ProductAdapter(List<Product> products, Context context,Boolean value) {
        this.products = products;
        this.secu = products;
        this.newList = new ArrayList<>(products);
        this.context = context;
        this.value = value;
    }


    @Override
    public ProductAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(aa.length()>1){
            return new ProductAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stok, parent, false));

        }else {
            return new ProductAdapter.Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false));

        }
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(ProductAdapter.Holder holder, int position) {

        if(aa.length()>1){

            try {
                holder.kat.setText(itemList.get(position).getGetUrun().getAltkatagori().get(0).getGetKatagori().get(0).getKatagoriAdi());
                holder.urun.setText(itemList.get(position).getGetUrun().getUrunAdi());
                holder.altkat.setText(itemList.get(position).getGetUrun().getAltkatagori().get(0).getAltkatagoriAdi());
                holder.stok.setText(itemList.get(position).getStok());

            }catch (Exception e){

            }


        }else {
            try {
                if(value){
                    holder.productName.setText(products.get(position).getUrunAdi());
                    holder.stok.setText(products.get(position).getStok());

            /*        Picasso.with(context)
                            .load("https://market.cenkkaraboa.com/public/images/urun/" + itemList.get(position).getGetUrun().getGorselUrl())
                            .into(holder.productImage);
*/

                    if (products.get(position).getUrunTuru().equals("1")) {
                        holder.productPrice.setText(products.get(position).getIndirimFiyat() + " TL");

                    } else {
                        holder.productPrice.setText(products.get(position).getSatisFiyat() + " TL");
                    }
                }else {
                    holder.productName.setText(itemList.get(position).getGetUrun().getUrunAdi());
                    holder.stok.setText(itemList.get(position).getStok());

            /*        Picasso.with(context)
                            .load("https://market.cenkkaraboa.com/public/images/urun/" + itemList.get(position).getGetUrun().getGorselUrl())
                            .into(holder.productImage);
*/

                    if (itemList.get(position).getUrunTuru().toString().equals("1")) {
                        holder.productPrice.setText(itemList.get(position).getIndirimFiyat() + " TL");

                    } else {
                        holder.productPrice.setText(itemList.get(position).getSatisFiyat() + " TL");
                    }
                }


            } catch (Exception e) {

            }
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

            if(aa.length()>1){
                List<Urun> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(newItem);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Urun item : newItem) {
                        if (item.getGetUrun().getUrunAdi().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                results.values = filteredList;
            }else {
                if(value){
                    List<Product> filteredList = new ArrayList<>();
                    if (constraint == null || constraint.length() == 0) {
                        filteredList.addAll(newList);
                    } else {
                        String filterPattern = constraint.toString().toLowerCase().trim();
                        for (Product item : newList) {
                            if (item.getUrunAdi().toLowerCase().contains(filterPattern)) {
                                filteredList.add(item);
                            }
                        }
                    }
                    results.values = filteredList;
                }else {
                    List<Urun> filteredList = new ArrayList<>();
                    if (constraint == null || constraint.length() == 0) {
                        filteredList.addAll(newItem);
                    } else {
                        String filterPattern = constraint.toString().toLowerCase().trim();
                        for (Urun item : newItem) {
                            if (item.getGetUrun().getUrunAdi().toLowerCase().contains(filterPattern)) {
                                filteredList.add(item);
                            }
                        }
                    }
                    results.values = filteredList;
                }
            }
            return results;

        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(aa.length()>1){
                if(itemList != null){
                    itemList.clear();
                }
                itemList.addAll((List) results.values);
                notifyDataSetChanged();
            }else {
                if(value){
                    if(products != null){
                        products.clear();
                    }
                    products.addAll((List) results.values);
                    notifyDataSetChanged();
                }else {
                    if(itemList != null){
                        itemList.clear();
                    }
                    itemList.addAll((List) results.values);
                    notifyDataSetChanged();
                }
            }


        }
    };


    @Override
    public int getItemCount() {
        if(value){
            return products.size();

        }else {
            return itemList.size();

        }

    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView productName, productPrice,stok;
        TextView kat, altkat,marka,urun;
        ImageView productImage;
        Button addStore;

        public Holder(final View itemView) {
            super(itemView);

            if(aa.length()>1){
                urun = (TextView) itemView.findViewById(R.id.urun);
                stok = (TextView) itemView.findViewById(R.id.stok);
                altkat = (TextView) itemView.findViewById(R.id.altkat);
                kat = (TextView) itemView.findViewById(R.id.kat);
            }else {
                productName = (TextView) itemView.findViewById(R.id.productName);
                stok = (TextView) itemView.findViewById(R.id.stok);
                productPrice = (TextView) itemView.findViewById(R.id.productPrice);

            }
        }
    }

}