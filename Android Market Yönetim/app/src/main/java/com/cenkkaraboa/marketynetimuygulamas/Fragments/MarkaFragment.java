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

import com.cenkkaraboa.marketynetimuygulamas.Adapters.AltKategoriAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Models.Marka;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeAltKategori;
import com.cenkkaraboa.marketynetimuygulamas.ProductActivity;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;

public class MarkaFragment extends Fragment implements AltKategoriAdapter.OnItemClickListener {

    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    public List<Marka> markas;

    AltKategoriAdapter adminSiparisAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_marka, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        subemarka();


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

        return view;
    }


    public void subemarka() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer position=preferences.getInt("position",-1);
        Callback<List<Marka>> listCallBack = new Callback<List<Marka>>() {
            @Override
            public void onResponse(Call<List<Marka>> call, Response<List<Marka>> response) {
                if (response.isSuccessful()) {

                    markas=response.body();

                    adminSiparisAdapter = new AltKategoriAdapter(response.body(),getContext(),true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adminSiparisAdapter);
                    adminSiparisAdapter.setOnItemClickListener(MarkaFragment.this);
                    adminSiparisAdapter.notifyDataSetChanged();

                    recyclerView.setVisibility(View.VISIBLE);
                }                    progressBar.setVisibility(GONE);
            }

            @Override
            public void onFailure(Call<List<Marka>> call, Throwable t) {
            }
        };
        interfaces.subemarka(String.valueOf(position)).enqueue(listCallBack);
    }


    @Override
    public void onItemClick(int position,int id,String name) {
        Intent intent = new Intent(getActivity(), ProductActivity.class);
        intent.putExtra("value", "marka");
        intent.putExtra("name", name);
        intent.putExtra("val", id);
        startActivity(intent);
    }
}