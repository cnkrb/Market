package com.cenk.marketsmi.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cenk.marketsmi.Adapters.OrderAdapter;
import com.cenk.marketsmi.Adapters.ProductAdapter;
import com.cenk.marketsmi.Adapters.StoreAdapter;
import com.cenk.marketsmi.AddressActivity;
import com.cenk.marketsmi.HomeFragment;
import com.cenk.marketsmi.Models.Product;
import com.cenk.marketsmi.Models.Siparis;
import com.cenk.marketsmi.R;
import com.cenk.marketsmi.fragments.SelectMarketFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenk.marketsmi.Utils.Utils.interfaces;


public class OrderFragment extends Fragment  implements OrderAdapter.OnItemClickListener{
    private String android_id="cihaz";

    RecyclerView recyclerView;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer position=preferences.getInt("position",0);
        getAsc(position,android_id);
        return view;
    }

    public void getAsc(Integer sube,String device) {
        Callback<List<Siparis>> listCallBack = new Callback<List<Siparis>>() {
            @Override
            public void onResponse(Call<List<Siparis>> call, Response<List<Siparis>> response) {
                if (response.isSuccessful()) {
                    if(response.body().size()>0){
                        OrderAdapter storeAdapter = new OrderAdapter(response.body(),getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(storeAdapter);
                        storeAdapter.setOnItemClickListener(OrderFragment.this);
                        storeAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Siparis>> call, Throwable t) {
            }
        };
        interfaces.getSiparis(sube,device).enqueue(listCallBack);
    }

    @Override
    public void onItemClick(int position) {
        ((OrderActivity)requireActivity()).changeScreen(position);
    }
}