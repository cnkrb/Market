package com.cenkkaraboa.marketynetimuygulamas.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cenkkaraboa.marketynetimuygulamas.Adapters.KuryerAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Adapters.SubeAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Models.Odeme;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class OdemeFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_odeme, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        odeme();

        return view;
    }

    public void odeme() {
        Callback<List<Odeme>> listCallBack = new Callback<List<Odeme>>() {
            @Override
            public void onResponse(Call<List<Odeme>> call, Response<List<Odeme>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        KuryerAdapter kuryerAdapter = new KuryerAdapter(response.body(),getContext(),true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(kuryerAdapter);
                        kuryerAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Odeme>> call, Throwable t) {
            }
        };
        interfaces.odeme().enqueue(listCallBack);
    }
}