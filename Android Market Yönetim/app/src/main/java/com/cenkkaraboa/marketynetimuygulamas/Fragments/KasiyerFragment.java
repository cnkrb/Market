package com.cenkkaraboa.marketynetimuygulamas.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cenkkaraboa.marketynetimuygulamas.Adapters.UserAdapter;
import com.cenkkaraboa.marketynetimuygulamas.Models.Sube;
import com.cenkkaraboa.marketynetimuygulamas.Models.User;
import com.cenkkaraboa.marketynetimuygulamas.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;
import static com.cenkkaraboa.marketynetimuygulamas.Utils.Utils.interfaces;


public class KasiyerFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_kasiyer, container, false);
        recyclerView=view.findViewById(R.id.recyclerView);
        progressBar=view.findViewById(R.id.progressBar);
        recyclerView.setVisibility(GONE);
        sube();
        return view;
    }

    public void user(List<Sube> subeList) {
        Callback<List<User>> listCallBack = new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        progressBar.setVisibility(GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        UserAdapter userAdapter = new UserAdapter(response.body(),subeList,getContext(),true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setAdapter(userAdapter);
                        userAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        };
        interfaces.kasiyer().enqueue(listCallBack);
    }

    public void sube() {
        Callback<List<Sube>> listCallBack = new Callback<List<Sube>>() {
            @Override
            public void onResponse(Call<List<Sube>> call, Response<List<Sube>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        user(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sube>> call, Throwable t) {
            }
        };
        interfaces.sube().enqueue(listCallBack);
    }
}