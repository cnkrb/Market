package com.cenkkaraboa.marketynetimuygulamas.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

import com.cenkkaraboa.marketynetimuygulamas.Adapters.ProductAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Models.Urun;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class StokFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ProductAdapter adminSiparisAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_stok, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        subeurun();

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

    public void subeurun() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        Integer position=preferences.getInt("position",-1);
        Callback<List<Urun>> listCallBack = new Callback<List<Urun>>() {
            @Override
            public void onResponse(Call<List<Urun>> call, Response<List<Urun>> response) {
                if (response.isSuccessful()) {
                    adminSiparisAdapter = new ProductAdapter(response.body(),getContext(),"aa");
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adminSiparisAdapter);
                    adminSiparisAdapter.notifyDataSetChanged();

                    recyclerView.setVisibility(View.VISIBLE);
                }                    progressBar.setVisibility(GONE);

            }

            @Override
            public void onFailure(Call<List<Urun>> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        };
        interfaces.subeurun(String.valueOf(position)).enqueue(listCallBack);
    }
}