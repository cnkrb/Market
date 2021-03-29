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

import com.cenkkaraboa.marketynetimuygulamas.Adapters.AdminSiparisAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Adapters.AltKategoriAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Adapters.KategoriAdapter;
import com.cenkkaraboa.marketynetimuygulamas.DetailsActivity;
import com.cenkkaraboa.marketynetimuygulamas.Models.AdminSiparis;
import com.cenkkaraboa.marketynetimuygulamas.Models.Product;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.SubeAltKategori;
import com.cenkkaraboa.marketynetimuygulamas.ProductActivity;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class SubeAltKategoriFragment extends Fragment  implements  AltKategoriAdapter.OnItemClickListener{

    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    public List<SubeAltKategori> itemList;
    AltKategoriAdapter adminSiparisAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_sube_alt_kategori, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        subealtkatagori();

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

    public void subealtkatagori() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer position=preferences.getInt("position",-1);
        Callback<List<SubeAltKategori>> listCallBack = new Callback<List<SubeAltKategori>>() {
            @Override
            public void onResponse(Call<List<SubeAltKategori>> call, Response<List<SubeAltKategori>> response) {
                if (response.isSuccessful()) {

                    itemList=response.body();
                    adminSiparisAdapter = new AltKategoriAdapter(response.body(),getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adminSiparisAdapter);
                    adminSiparisAdapter.setOnItemClickListener(SubeAltKategoriFragment.this);
                    adminSiparisAdapter.notifyDataSetChanged();

                    recyclerView.setVisibility(View.VISIBLE);
                }   progressBar.setVisibility(GONE);
            }

            @Override
            public void onFailure(Call<List<SubeAltKategori>> call, Throwable t) {
            }
        };
        interfaces.subealtkatagori(String.valueOf(position)).enqueue(listCallBack);
    }

    @Override
    public void onItemClick(int position,int id,String name) {
        Intent intent = new Intent(getActivity(), ProductActivity.class);
        intent.putExtra("value", "altkat");
        intent.putExtra("name", name);
        intent.putExtra("val", id);
        startActivity(intent);
    }
}