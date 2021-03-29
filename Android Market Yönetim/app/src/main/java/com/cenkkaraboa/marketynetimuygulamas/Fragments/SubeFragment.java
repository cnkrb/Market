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

import com.cenkkaraboa.marketynetimuygulamas.Adapters.SubeAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.R;
import com.cenkkaraboa.marketynetimuygulamas.SubeActivity;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class SubeFragment extends Fragment implements SubeAdapter.OnItemClickListener{

    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Sube> subes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_sube, container, false);
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
                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        subes=response.body();
                        SubeAdapter subeAdapter = new SubeAdapter(response.body(),getContext());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(subeAdapter);
                        subeAdapter.setOnItemClickListener(SubeFragment.this);
                        subeAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Sube>> call, Throwable t) {
            }
        };
        interfaces.sube().enqueue(listCallBack);
    }

    @Override
    public void onItemClick(int position) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("store", subes.get(position).getSubeAdi());
        editor.putInt("position", subes.get(position).getId());
        editor.apply();
        Intent intent=new Intent(getContext(), SubeActivity.class);
        startActivity(intent);
    }
}