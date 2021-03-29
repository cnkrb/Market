package com.cenkkaraboa.marketynetimuygulamas.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cenkkaraboa.marketynetimuygulamas.Adapters.GecmisAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Adapters.ProductAdapter;
import com.cenkkaraboa.marketynetimuygulamas.GecmisDetailsActivity;
import com.cenkkaraboa.marketynetimuygulamas.Models.Gecmis;
import com.cenkkaraboa.marketynetimuygulamas.Models.Urun;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class GecmisFragment extends Fragment  implements  GecmisAdapter.OnItemClickListener{


    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Urun> urunList;
    List<Gecmis> gecmisList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_gecmis, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        subeurun();
        return view;
    }


    public void urungecmisi(List<Urun> urunList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer position = preferences.getInt("position", -1);
        Callback<List<Gecmis>> listCallBack = new Callback<List<Gecmis>>() {
            @Override
            public void onResponse(Call<List<Gecmis>> call, Response<List<Gecmis>> response) {
                if (response.isSuccessful()) {
                    gecmisList=response.body();
                    GecmisAdapter adminSiparisAdapter = new GecmisAdapter(response.body(), urunList, getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adminSiparisAdapter);
                    adminSiparisAdapter.setOnItemClickListener(GecmisFragment.this);
                    adminSiparisAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(GONE);

            }

            @Override
            public void onFailure(Call<List<Gecmis>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        interfaces.urungecmisi(String.valueOf(position)).enqueue(listCallBack);
    }

    public void subeurun() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer position = preferences.getInt("position", -1);
        Callback<List<Urun>> listCallBack = new Callback<List<Urun>>() {
            @Override
            public void onResponse(Call<List<Urun>> call, Response<List<Urun>> response) {
                if (response.isSuccessful()) {
                    urunList=response.body();
                    urungecmisi(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Urun>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        interfaces.subeurun(String.valueOf(position)).enqueue(listCallBack);

    }


    @Override
    public void onItemClick(int position, String kat, String altkat, String marka, String urun, String tur, String fiyat, String adet) {
        Intent  intent=new Intent(getContext(), GecmisDetailsActivity.class);

        intent.putExtra("kat", kat);
        intent.putExtra("altkat", altkat);
        intent.putExtra("marka", marka);
        intent.putExtra("urun", urun);
        intent.putExtra("adet", adet);
        intent.putExtra("fiyat", adet);
        intent.putExtra("tur", tur);
        startActivity(intent);
    }
}