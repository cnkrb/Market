package com.cenk.marketsmi.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cenk.marketsmi.Adapters.BasketAdapter;
import com.cenk.marketsmi.Adapters.OrderAdapter;
import com.cenk.marketsmi.Models.Basket;
import com.cenk.marketsmi.Models.GetSepet;
import com.cenk.marketsmi.Models.Siparis;
import com.cenk.marketsmi.R;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.cenk.marketsmi.Utils.Utils.interfaces;


public class DetailsFragment extends Fragment {

    View view;
    Integer myInt;
    private String android_id="cihaz";
    TextView  number,date,price,address,mobile,statement,totalPrice;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details, container, false);

        number=view.findViewById(R.id.number);
        date=view.findViewById(R.id.date);
        price=view.findViewById(R.id.price);
        address=view.findViewById(R.id.address);
        mobile=view.findViewById(R.id.mobile);
        totalPrice=view.findViewById(R.id.total);
        statement=view.findViewById(R.id.statament);
        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myInt = bundle.getInt("key", -1);
        }
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

                        try {
                            List<GetSepet> basketList = new ArrayList<>();
                            basketList = response.body().get(myInt).getGetSepet();


                            number.setText("Sipariş numaraıs gelevek");
                            date.setText(response.body().get(myInt).getCreatedAt());
                            price.setText(response.body().get(myInt).getOdemeId().toString());
                            address.setText(response.body().get(myInt).getAdres());
                            mobile.setText(response.body().get(myInt).getTelefon());
                            statement.setText(response.body().get(myInt).getSiparisAciklama());

                            BasketAdapter productAdapter = new BasketAdapter(basketList, getContext(),true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.setAdapter(productAdapter);
                            productAdapter.notifyDataSetChanged();
                            double total = 0.0;
                            for (int i = 0; i < basketList.size(); i++) {
                                total = total + Double.parseDouble(basketList.get(i).getAdet()) * Double.parseDouble(basketList.get(i).getSatisFiyat());
                            }
                            totalPrice.setText(round(total,2)+" TL");
                        }catch (Exception e){

                        }


                      /*  OrderAdapter storeAdapter = new OrderAdapter(response.body(),getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(storeAdapter);
                        storeAdapter.setOnItemClickListener(OrderFragment.this);
                        storeAdapter.notifyDataSetChanged();*/
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Siparis>> call, Throwable t) {
            }
        };
        interfaces.getSiparis(sube,device).enqueue(listCallBack);
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}