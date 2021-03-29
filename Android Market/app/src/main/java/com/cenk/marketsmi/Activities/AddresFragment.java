package com.cenk.marketsmi.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cenk.marketsmi.Adapters.BasketAdapter;
import com.cenk.marketsmi.Adapters.InfoAdapter;
import com.cenk.marketsmi.AddressActivity;
import com.cenk.marketsmi.Models.Basket;
import com.cenk.marketsmi.Models.InfoModel;
import com.cenk.marketsmi.R;
import com.cenk.marketsmi.database.BasketDatabase;
import com.cenk.marketsmi.database.InfoDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AddresFragment extends Fragment implements InfoAdapter.OnItemClickListener ,InfoAdapter.OnDeleteItemClickListener{
    List<InfoModel> infoModels=new ArrayList<>();
    RecyclerView recyclerView;
    View view;
    InfoAdapter infoAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_addres, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        infoModels=getProducts();


        infoAdapter = new InfoAdapter(infoModels, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(infoAdapter);
        infoAdapter.setOnItemClickListener(AddresFragment.this);
        infoAdapter.setOnDeleteItemClickListener(AddresFragment.this);
        infoAdapter.notifyDataSetChanged();

        return  view;
    }


    public List<InfoModel> getProducts(){
        List<InfoModel> basketList=new ArrayList<>();
        ArrayList<HashMap<String, String>> products;
        InfoDatabase  db = new InfoDatabase(getContext());
        products = db.getAddress();
        if (products.size() != 0) {
            for(int i=0;i<products.size();i++){
                InfoModel infoModel=new InfoModel();
                infoModel.setUsername(products.get(i).get("name"));
                infoModel.setId(products.get(i).get("id"));
                infoModel.setAddress(products.get(i).get("address"));
                infoModel.setNumber(products.get(i).get("number"));
                basketList.add(infoModel);
            }
        }
        db.close();
        return basketList;
    }

    @Override
    public void onItemClick(int position) {
        ((AddressActivity)requireActivity()).changeScreen(position);

    }

    @Override
    public void onDeleteItemClick(int position) {
        InfoDatabase  db = new InfoDatabase(getContext());
        db.addressDelete(Integer.parseInt(infoModels.get(position).getId()));
        db.close();
        infoModels=getProducts();
        infoAdapter = new InfoAdapter(infoModels, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(infoAdapter);
        infoAdapter.setOnItemClickListener(AddresFragment.this);
        infoAdapter.setOnDeleteItemClickListener(AddresFragment.this);
        infoAdapter.notifyDataSetChanged();
    }
}