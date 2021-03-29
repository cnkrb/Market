package com.cenkkaraboa.marketynetimuygulamas.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cenkkaraboa.marketynetimuygulamas.Adapters.AdminSiparisAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Adapters.UserAdapter;
import com.cenkkaraboa.marketynetimuygulamas.DetailsActivity;
import com.cenkkaraboa.marketynetimuygulamas.Models.AdminSiparis;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.User;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class AdminSiparisFragment extends Fragment implements AdminSiparisAdapter.OnItemClickListener {


    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    public List<AdminSiparis> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_admin_siparis, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
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
                        adminsiparis(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sube>> call, Throwable t) {
            }
        };
        interfaces.sube().enqueue(listCallBack);
    }

    public void adminsiparis(List<Sube> subeList) {
        Callback<List<AdminSiparis>> listCallBack = new Callback<List<AdminSiparis>>() {
            @Override
            public void onResponse(Call<List<AdminSiparis>> call, Response<List<AdminSiparis>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {

                        itemList=response.body();
                        AdminSiparisAdapter adminSiparisAdapter = new AdminSiparisAdapter(itemList,subeList,getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(adminSiparisAdapter);
                        adminSiparisAdapter.setOnItemClickListener(AdminSiparisFragment.this);
                        adminSiparisAdapter.notifyDataSetChanged();
                    }
                    progressBar.setVisibility(GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<AdminSiparis>> call, Throwable t) {
            }
        };
        interfaces.adminsiparis().enqueue(listCallBack);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}