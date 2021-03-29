package com.cenkkaraboa.marketynetimuygulamas.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cenkkaraboa.marketynetimuygulamas.Adapters.AdminSiparisAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Adapters.KategoriAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeKategori;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeSiparis;
import com.cenkkaraboa.marketynetimuygulamas.ProductActivity;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class SubeKategoriFragment extends Fragment implements  KategoriAdapter.OnItemClickListener{

    public SubeKategori itemList;

    View view;
    KategoriAdapter adminSiparisAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_sube_kategori, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        subekatagori();

        EditText search = view.findViewById(R.id.search); // inititate a search view
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                adminSiparisAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return  view;
    }

    public void subekatagori() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer position=preferences.getInt("position",-1);
        Callback<SubeKategori> listCallBack = new Callback<SubeKategori>() {
            @Override
            public void onResponse(Call<SubeKategori> call, Response<SubeKategori> response) {
                if (response.isSuccessful()) {

                    itemList=response.body();
                    adminSiparisAdapter = new KategoriAdapter(response.body().getSubekatagori(),getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adminSiparisAdapter);
                    adminSiparisAdapter.setOnItemClickListener(SubeKategoriFragment.this);
                    adminSiparisAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                }                    progressBar.setVisibility(GONE);

            }

            @Override
            public void onFailure(Call<SubeKategori> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        };
        interfaces.subekatagori(String.valueOf(position)).enqueue(listCallBack);
    }

    @Override
    public void onItemClick(int position,int id,String name) {
        Intent intent = new Intent(getActivity(), ProductActivity.class);
        intent.putExtra("value", "kat");
        intent.putExtra("name",name);
        intent.putExtra("val", id);
        startActivity(intent);
    }
}