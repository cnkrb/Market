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

import com.cenkkaraboa.marketynetimuygulamas.Adapters.AdminSiparisAdapter;
import com.cenkkaraboa.marketynetimuygulamas.DetailsActivity;
import com.cenkkaraboa.marketynetimuygulamas.Models.AdminSiparis;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeSiparis;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class SubeSiparisFragment extends Fragment implements AdminSiparisAdapter.OnItemClickListener {


    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sube_siparis, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        sube();
        return view;
    }


    public void sube() {
        Callback<List<Sube>> listCallBack = new Callback<List<Sube>>() {
            @Override
            public void onResponse(Call<List<Sube>> call, Response<List<Sube>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        subesiparis(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sube>> call, Throwable t) {
            }
        };
        interfaces.sube().enqueue(listCallBack);
    }


    public void subesiparis(List<Sube> subeList) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer position = preferences.getInt("position", -1);
        Callback<SubeSiparis> listCallBack = new Callback<SubeSiparis>() {
            @Override
            public void onResponse(Call<SubeSiparis> call, Response<SubeSiparis> response) {
                if (response.isSuccessful()) {


                    AdminSiparisAdapter adminSiparisAdapter = new AdminSiparisAdapter(response.body().getSiparis(), subeList, getContext(), true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adminSiparisAdapter);
                    adminSiparisAdapter.setOnItemClickListener(SubeSiparisFragment.this);
                    adminSiparisAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(GONE);

            }

            @Override
            public void onFailure(Call<SubeSiparis> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        interfaces.subesiparis(String.valueOf(position)).enqueue(listCallBack);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}