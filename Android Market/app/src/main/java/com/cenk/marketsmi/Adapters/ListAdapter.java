package com.cenk.marketsmi.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cenk.marketsmi.Models.GetSepet;
import com.cenk.marketsmi.R;

import java.util.List;

public class ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    public List<GetSepet> itemList;


    public ListAdapter(Activity context,  List<GetSepet> itemList) {
        super(context, R.layout.item_list);
        this.context=context;
        this.itemList=itemList;

    }

    @Override
    public int getCount() {
        return itemList.size();
    }



    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();

        view = inflater.inflate(R.layout.item_list, null);



        TextView product = (TextView) view.findViewById(R.id.product);
        TextView price = (TextView) view.findViewById(R.id.price);

        product.setText(itemList.get(position).getUrunAdi());
        price.setText(itemList.get(position).getAdet());

        return view;

    };
}
